package com.example.demo.scraper;

import com.example.demo.domain.entity.SteamGameTimeEntity;
import com.example.demo.repository.SteamGameTimeRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SteamGameFind {

    @Autowired
    SteamGameTimeRepository steamGameTimeRepository;

    public void findGame(){

        WebDriver start = SteamWebDriverFactory.getInstance();
        List<WebElement> gameboxes = start.findElements(By.cssSelector(".gameslistitems_GamesListItemContainer_29H3o"));
        WebElement maxGameSize= start.findElement(By.cssSelector("[href=\"https://steamcommunity.com/id/draiiz/games/?tab=all\"]"));
        String[] mgsArray=maxGameSize.getText().split("\\(");
        mgsArray = mgsArray[1].split("\\)");
        int gamesize = Integer.parseInt(mgsArray[0]);
        while(gameboxes.size()<gamesize){
            gameboxes = start.findElements(By.cssSelector(".gameslistitems_GamesListItemContainer_29H3o"));
        }
        double gametime=0;
        for(int i=0; i< gameboxes.size();i++){
            List<WebElement> gameName = gameboxes.get(i).findElements(By.cssSelector(".gameslistitems_GameName_22awl"));
            List<WebElement> gameTime = gameboxes.get(i).findElements(By.cssSelector(".gameslistitems_Hours_26nl3"));

            Optional<SteamGameTimeEntity> tempGameName = steamGameTimeRepository.findByGameName(gameName.get(0).getText());
            if(tempGameName.isEmpty()){
                SteamGameTimeEntity steamGameTimeEntity= new SteamGameTimeEntity();
                steamGameTimeEntity.setGameName(gameName.get(0).getText());
                if(gameTime.isEmpty())
                    steamGameTimeEntity.setGameTime(0);

                else
                    steamGameTimeEntity.setGameTime((cleanHours(gameTime.get(0).getText())));

                steamGameTimeRepository.save(steamGameTimeEntity);
            }
            else{
                if(gameTime.isEmpty())
                    tempGameName.get().setGameTime(0);

                else
                    tempGameName.get().setGameTime(cleanHours(gameTime.get(0).getText()));

                steamGameTimeRepository.save(tempGameName.get());
            }
        }
    }
    public static Double cleanHours(String str){ //Összesen Játszva2 862,7 Óra
        str = str.substring(16); // 2 862,7 Óra
        str = str.replace(" ", " ");
        str = str.replace(",", ".");
        String[] strArray = str.split(" ");
        double result=0;
        if(strArray.length==3){
            result = (Integer.parseInt(strArray[0])*1000)+Double.parseDouble(strArray[1]);
        }
        else if(strArray[1].equals("óra")){
            result = Double.parseDouble(strArray[0]);
        }
        else if(strArray[1].equals("perc")){
            result = (Double.parseDouble(strArray[0]))/60;
        }
        return result;
    }
}
