package com.example.smarthousebackend.controllers;

import com.example.smarthousebackend.dto.SoilDto;
import com.example.smarthousebackend.service.SoilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/soil")
public class SoilController {

    private final SoilService soilService;

    @GetMapping("")
    public ResponseEntity<SoilDto> getSoil(){
        return ResponseEntity.ok(soilService.get());
    }
}
