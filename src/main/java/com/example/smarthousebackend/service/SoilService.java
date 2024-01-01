package com.example.smarthousebackend.service;

import com.example.smarthousebackend.dto.SoilDto;

public interface SoilService {

    void save(SoilDto soilDto);

    SoilDto get();
}
