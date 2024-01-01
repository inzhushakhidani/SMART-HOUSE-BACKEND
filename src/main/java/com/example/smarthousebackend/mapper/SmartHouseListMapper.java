package com.example.smarthousebackend.mapper;


import com.example.smarthousebackend.dto.SmartHouseListDto;
import com.example.smarthousebackend.entity.SmartHouseList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SmartHouseListMapper extends Mappable<SmartHouseList, SmartHouseListDto> {
    SmartHouseListDto toDto(SmartHouseList smartHouseList);

    SmartHouseList toEntity(SmartHouseListDto smartHouseListDto);

    List<SmartHouseListDto> toDtoList(List<SmartHouseList> entityList);

    List<SmartHouseList> toEntityLit(List<SmartHouseListDto> dtoList);
}
