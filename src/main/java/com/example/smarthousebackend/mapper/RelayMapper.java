package com.example.smarthousebackend.mapper;

import com.example.smarthousebackend.dto.RelayDto;
import com.example.smarthousebackend.entity.Relay;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RelayMapper extends Mappable<Relay, RelayDto> {

    RelayDto toDto(Relay settings);

    Relay toEntity(RelayDto settingsDto);

    List<RelayDto> toDtoList(List<Relay> relayList);
}
