package com.example.demo.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SteamWebDriverFactory {
    static WebDriver start;
    static WebDriver getInstance(){
        if(start==null){
            start = new SafariDriver();
        }
        return start;
    }
}
