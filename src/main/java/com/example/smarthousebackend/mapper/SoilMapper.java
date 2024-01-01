package com.example.smarthousebackend.mapper;

import com.example.smarthousebackend.dto.SoilDto;
import com.example.smarthousebackend.dto.UserDto;
import com.example.smarthousebackend.entity.Soil;
import com.example.smarthousebackend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SoilMapper extends Mappable<Soil, SoilDto> {

    SoilDto toDto(Soil soil);

    Soil toEntity(SoilDto soilDto);

}
