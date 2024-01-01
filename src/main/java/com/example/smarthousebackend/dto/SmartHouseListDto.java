package com.example.smarthousebackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SmartHouseListDto {

    private Integer smartHouse_id;

    private Double humidity;

    private Double pressure;

    private Double temperature;

    private String macAddress;

    private Date updatedAt;

    private Double co2;

    private Integer door;

    private Integer motion;
}
