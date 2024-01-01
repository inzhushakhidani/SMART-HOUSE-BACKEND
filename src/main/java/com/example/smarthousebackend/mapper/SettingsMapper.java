package com.example.smarthousebackend.mapper;

import com.example.smarthousebackend.dto.SettingsDto;
import com.example.smarthousebackend.entity.Settings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SettingsMapper extends Mappable<Settings, SettingsDto> {

    SettingsDto toDto(Settings settings);

    Settings toEntity(SettingsDto settingsDto);
}
