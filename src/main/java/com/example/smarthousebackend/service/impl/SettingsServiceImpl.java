package com.example.smarthousebackend.service.impl;

import com.example.smarthousebackend.dto.SettingsDto;
import com.example.smarthousebackend.entity.Settings;
import com.example.smarthousebackend.entity.SmartHouse;
import com.example.smarthousebackend.exceptions.EntityNotFoundException;
import com.example.smarthousebackend.mapper.SettingsMapper;
import com.example.smarthousebackend.repository.Firebase;
import com.example.smarthousebackend.repository.SettingsRepository;
import com.example.smarthousebackend.repository.SmartHouseRepository;
import com.example.smarthousebackend.service.SettingsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository settingsRepository;
    private final SmartHouseRepository hydroponicRepository;
    private final SettingsMapper settingsMapper;
    private final Firebase firebase;

    @Override
    @Transactional
    public void editSettings(SettingsDto settingsDto){
        firebase.editSettings(settingsDto);
    }

    @Override
    public SettingsDto getSettingsBySmartHouse(Integer smartHouse_id) {
        SmartHouse smartHouse = hydroponicRepository.findById(smartHouse_id)
                .orElseThrow(() -> new EntityNotFoundException("SmartHouse data not fount"));

        Settings settings = settingsRepository.findBySmartHouse(smartHouse)
                .orElseThrow(() -> new EntityNotFoundException("SmartHouse data not fount"));
        return settingsMapper.toDto(settings);
    }

    @Override
    public void editSecurityMode(Integer val) {
        firebase.editSecurity(val);
    }
}
