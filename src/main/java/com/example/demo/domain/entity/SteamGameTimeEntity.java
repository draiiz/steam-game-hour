package com.example.demo.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SteamGameTimeEntity {

    @Id
    @GeneratedValue
    private int id;

    public String gameName;
    public double gameTime;

}
