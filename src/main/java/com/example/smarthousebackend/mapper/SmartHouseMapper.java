package com.example.smarthousebackend.mapper;

import com.example.smarthousebackend.dto.SmartHouseDto;
import com.example.smarthousebackend.entity.SmartHouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SmartHouseMapper extends Mappable<SmartHouse, SmartHouseDto> {
    SmartHouseDto toDto(SmartHouse smartHouse);

    SmartHouse toEntity(SmartHouseDto smartHouseDto);
}
