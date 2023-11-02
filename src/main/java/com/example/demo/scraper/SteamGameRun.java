package com.example.demo.scraper;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SteamGameRun {
    @Value("${steam.username}")
    String steamUser;
    @Value("${steam.password}")
    String steamPassword;
    @Value("${steam.link.your.profile}")
    String steamLink;
    @Value("${steam.link.your.profile.gamelist}")
    String yourProfileGameList;

    @Autowired
    SteamGameFind steamGameFind;
    @PostConstruct
    public void run() {
        WebDriver start = SteamWebDriverFactory.getInstance();
        start.get(steamLink);
        start.manage().window().maximize();
        start.get("https://steamcommunity.com/login/home/?goto=");
        SteamWebWait.wait("[type=\"text\"].newlogindialog_TextInput_2eKVn", start, 10);

        SteamWebWait.wait(".newlogindialog_SubmitButton_2QgFE", start, 10);
        WebElement login = start.findElement(By.cssSelector("[type=\"text\"].newlogindialog_TextInput_2eKVn"));
        login.sendKeys(steamUser);
        WebElement password = start.findElement(By.cssSelector(("[type=\"password\"].newlogindialog_TextInput_2eKVn")));
        password.sendKeys(steamPassword);
        WebElement button = start.findElement(By.cssSelector(".newlogindialog_SubmitButton_2QgFE"));
        button.click();

        SteamWebWait.wait(".profile_count_link_total", start, 10);

        start.get(yourProfileGameList);
        SteamWebWait.wait(".sectionTabs.item.responsive_hidden.gameslisttabs_tabList_1sHAC", start, 20);
        steamGameFind.findGame();
        start.close();
    }
}
