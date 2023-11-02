package com.example.demo.service;

import com.example.demo.domain.exception.GameNotFoundException;
import com.example.demo.domain.response.SteamAllHour;
import com.example.demo.domain.projection.SteamGameHours;
import com.example.demo.domain.entity.SteamGameTimeEntity;
import com.example.demo.repository.SteamGameTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SteamGameTimeService {

    @Autowired
    SteamGameTimeRepository steamGameTimeRepository;
    public SteamGameHours returnPlayedHours(){
        return steamGameTimeRepository.returnPlayedHours();
    }

    public SteamAllHour findByGameName(String gameName){
        SteamAllHour steamAllHour= new SteamAllHour();
        Optional<SteamGameTimeEntity> result = steamGameTimeRepository.findByGameName(gameName);
        if(result.isEmpty()){
            throw new GameNotFoundException();
        }
        else{
            steamAllHour.setAllHour(result.get().getGameTime());
            return steamAllHour;
        }
    }
}
