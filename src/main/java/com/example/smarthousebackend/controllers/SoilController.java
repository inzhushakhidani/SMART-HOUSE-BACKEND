package com.example.smarthousebackend.controllers;

import com.example.smarthousebackend.dto.SoilDto;
import com.example.smarthousebackend.dto.SoilListDto;
import com.example.smarthousebackend.service.SoilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/soil")
public class SoilController {

    private final SoilService soilService;

    @GetMapping("")
    public ResponseEntity<SoilDto> getSoil(){
        return ResponseEntity.ok(soilService.get());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SoilListDto>> getAllSoil(){
        return ResponseEntity.ok(soilService.getAllList());
    }

}
