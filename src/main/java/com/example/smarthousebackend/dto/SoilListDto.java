package com.example.smarthousebackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SoilListDto {

    private Double flow;

    private Double moisture;

    private String timestamp;

    private Double totalFlow;

    private Date updatedAt;

}
