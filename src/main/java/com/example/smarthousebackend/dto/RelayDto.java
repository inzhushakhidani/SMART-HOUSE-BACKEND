package com.example.smarthousebackend.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelayDto {

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
