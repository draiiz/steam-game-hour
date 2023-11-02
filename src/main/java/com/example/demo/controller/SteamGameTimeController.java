package com.example.demo.controller;

import com.example.demo.domain.response.SteamAllHour;
import com.example.demo.domain.projection.SteamGameHours;
import com.example.demo.service.SteamGameTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SteamGameTimeController {

    @Autowired
    SteamGameTimeService steamGameTimeService;

    @GetMapping("/sum")
    public SteamGameHours sumHours(SteamGameHours steamGameHours){
        return steamGameTimeService.returnPlayedHours();
    }

    @GetMapping("/find-by-name/{name}")
    public SteamAllHour byName(@PathVariable("name")String gameName) {
        return steamGameTimeService.findByGameName(gameName);
    }
}
