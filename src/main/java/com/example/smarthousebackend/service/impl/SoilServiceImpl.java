package com.example.smarthousebackend.service.impl;

import com.example.smarthousebackend.dto.SoilDto;
import com.example.smarthousebackend.dto.SoilListDto;
import com.example.smarthousebackend.entity.Soil;
import com.example.smarthousebackend.entity.SoilList;
import com.example.smarthousebackend.mapper.SoilListMapper;
import com.example.smarthousebackend.mapper.SoilMapper;
import com.example.smarthousebackend.repository.SoilListRepository;
import com.example.smarthousebackend.repository.SoilRepository;
import com.example.smarthousebackend.service.SoilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SoilServiceImpl implements SoilService {

    private final SoilRepository soilRepository;
    private final SoilMapper soilMapper;
    private final SoilListRepository soilListRepository;
    private final SoilListMapper soilListMapper;

    @Override
    public void save(SoilDto soilDto) {
        Soil soil = soilMapper.toEntity(soilDto);
        soil.setId(1);
        soilRepository.save(soil);
    }

    @Override
    public SoilDto get() {
        Soil soil = soilRepository.findById(1).get();
        return soilMapper.toDto(soil);

    }

    @Override
    public List<SoilListDto> getAllList() {
        List<SoilList> list = soilListRepository.findAll();
        return soilListMapper.toDtoList(list);
    }
}
