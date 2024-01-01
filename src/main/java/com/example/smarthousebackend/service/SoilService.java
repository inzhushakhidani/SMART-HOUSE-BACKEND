package com.example.smarthousebackend.service;

import com.example.smarthousebackend.dto.SoilDto;
import com.example.smarthousebackend.dto.SoilListDto;

import java.util.List;

public interface SoilService {

    void save(SoilDto soilDto);

    SoilDto get();

    List<SoilListDto> getAllList();
}
