package com.example.smarthousebackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double maxTemperature;
    private Double avgTemperature;
    private Double minTemperature;

    private Double maxHumidity;
    private Double avgHumidity;
    private Double minHumidity;

    private String startTime;
    private String stopTime;

    private Double avgSoilMoisture;
    private Double minSoilMoisture;

    private Integer mode;
    private Integer security;

    @OneToOne(mappedBy = "settings")
    private SmartHouse smartHouse;


}
