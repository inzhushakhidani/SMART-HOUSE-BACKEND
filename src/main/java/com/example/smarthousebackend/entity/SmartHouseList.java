package com.example.smarthousebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "smart_house_list")
public class SmartHouseList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
