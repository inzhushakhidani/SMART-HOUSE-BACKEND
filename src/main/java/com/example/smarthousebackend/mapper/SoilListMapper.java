package com.example.smarthousebackend.mapper;

import com.example.smarthousebackend.dto.SmartHouseListDto;
import com.example.smarthousebackend.dto.SoilDto;
import com.example.smarthousebackend.dto.SoilListDto;
import com.example.smarthousebackend.entity.SmartHouseList;
import com.example.smarthousebackend.entity.Soil;
import com.example.smarthousebackend.entity.SoilList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SoilListMapper extends Mappable<SoilList, SoilListDto> {

    SoilListDto toDto(SoilList soilList);

    SoilList toEntity(SoilListDto soilListDto);

    List<SoilListDto> toDtoList(List<SoilList> entityList);

    List<SoilList> toEntityList(List<SoilListDto> dtoList);
}
