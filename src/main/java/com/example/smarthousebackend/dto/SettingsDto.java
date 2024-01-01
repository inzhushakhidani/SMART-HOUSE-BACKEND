package com.example.smarthousebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SettingsDto {

    private Integer hydroponic_id;

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

}
