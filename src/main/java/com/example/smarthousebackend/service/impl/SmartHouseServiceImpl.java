package com.example.smarthousebackend.service.impl;

import com.example.smarthousebackend.dto.SettingsDto;
import com.example.smarthousebackend.dto.SmartHouseDto;
import com.example.smarthousebackend.dto.SmartHouseListDto;
import com.example.smarthousebackend.entity.Settings;
import com.example.smarthousebackend.entity.SmartHouse;
import com.example.smarthousebackend.entity.SmartHouseList;
import com.example.smarthousebackend.exceptions.EntityNotFoundException;
import com.example.smarthousebackend.mapper.SettingsMapper;
import com.example.smarthousebackend.mapper.SmartHouseListMapper;
import com.example.smarthousebackend.mapper.SmartHouseMapper;
import com.example.smarthousebackend.repository.SettingsRepository;
import com.example.smarthousebackend.repository.SmartHouseListRepository;
import com.example.smarthousebackend.repository.SmartHouseRepository;
import com.example.smarthousebackend.service.SmartHouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmartHouseServiceImpl implements SmartHouseService {

    private final SmartHouseRepository smartHouseRepository;
    private final SmartHouseMapper smartHouseMapper;
    private final SettingsMapper settingsMapper;
    private final SettingsRepository settingsRepository;
    private final SmartHouseListMapper smartHouseListMapper;
    private final SmartHouseListRepository smartHouseListRepository;

    @Override
    public SmartHouseDto getSmartHouse(Integer id) {
        SmartHouse smartHouse = smartHouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hydroponic data not fount"));
        return smartHouseMapper.toDto(smartHouse);

    }

    @Override
    @Transactional
    public void editSmartHouse(Integer id, SmartHouseDto smartHouseDto, SettingsDto settingsDto) {
        SmartHouse smartHouse = smartHouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hydroponic data not fount"));

        Settings settings = settingsMapper.toEntity(settingsDto);
        settings.setId(1);
        settingsRepository.save(settings);

        smartHouse.setMacAddress(smartHouseDto.getMacAddress());
        smartHouse.setTemperature(smartHouseDto.getTemperature());
        smartHouse.setHumidity(smartHouseDto.getHumidity());
        smartHouse.setPressure(smartHouseDto.getPressure());
        smartHouse.setUpdatedAt(new Date());
        smartHouse.setSettings(settings);
        smartHouse.setCo2(smartHouseDto.getCo2());
        smartHouse.setDoor(smartHouseDto.getDoor());
        smartHouse.setMotion(smartHouseDto.getMotion());
        smartHouseRepository.save(smartHouse);
    }

    @Override
    public void editSmartHouseInFirebase(Integer id, SmartHouseDto smartHouseDto) {

    }

    @Override
    @Transactional
    public void addSmartHouse(SmartHouseDto smartHouseDto) {
        SmartHouse smartHouse = smartHouseMapper.toEntity(smartHouseDto);
        smartHouseRepository.save(smartHouse);
    }

    @Override
    @Transactional
    public void deleteSmartHouse(Integer id) {
        SmartHouse smartHouse = smartHouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hydroponic data not fount"));
        smartHouseRepository.delete(smartHouse);
    }

    @Override
    public List<SmartHouseListDto> getAllSmartHouse() {
        List<SmartHouseList> list = smartHouseListRepository.findAll();

        return smartHouseListMapper.toDtoList(list);
    }

}
