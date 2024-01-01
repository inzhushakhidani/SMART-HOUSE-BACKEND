package com.example.smarthousebackend.service;

import com.example.smarthousebackend.dto.SettingsDto;

public interface SettingsService {

    void editSettings(SettingsDto settingsDto);

    SettingsDto getSettingsBySmartHouse(Integer smartHouse_id);

    void editSecurityMode(Integer val);
}
