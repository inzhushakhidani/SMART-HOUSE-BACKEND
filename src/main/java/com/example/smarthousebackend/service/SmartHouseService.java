package com.example.smarthousebackend.service;

import com.example.smarthousebackend.dto.SettingsDto;
import com.example.smarthousebackend.dto.SmartHouseDto;
import com.example.smarthousebackend.dto.SmartHouseListDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;


public interface SmartHouseService {

    SmartHouseDto getSmartHouse(Integer id);

    void editSmartHouse(Integer id, SmartHouseDto smartHouseDto, SettingsDto settingsDto);

    void editSmartHouseInFirebase(Integer id, SmartHouseDto smartHouseDto);

    void addSmartHouse(SmartHouseDto smartHouseDto);

    void deleteSmartHouse(Integer id);

    List<SmartHouseListDto> getAllSmartHouse();

//    void checkHydroponic();
}
