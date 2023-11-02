package com.example.demo.repository;

import com.example.demo.domain.projection.SteamGameHours;
import com.example.demo.domain.entity.SteamGameTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SteamGameTimeRepository extends JpaRepository<SteamGameTimeEntity, Integer> {

    Optional<SteamGameTimeEntity> findByGameName(String gameName);

    @Query("SELECT sum(steamGameTimeEntity.gameTime) as allHours FROM SteamGameTimeEntity steamGameTimeEntity")
    SteamGameHours returnPlayedHours();
}
