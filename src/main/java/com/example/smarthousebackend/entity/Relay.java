package com.example.smarthousebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "relay")
public class Relay{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer lamp;

    private Integer switch1;

    private Integer switch2;

    private Integer alarm;

    private Integer humidifier;

    private Integer heating;

    private Integer airflow;

    private Integer pump;

    private Date localDate;

    private Integer mode;
}
