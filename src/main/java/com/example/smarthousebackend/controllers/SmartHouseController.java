package com.example.smarthousebackend.controllers;

import com.example.smarthousebackend.dto.SettingsDto;
import com.example.smarthousebackend.dto.SmartHouseDto;
import com.example.smarthousebackend.dto.SmartHouseListDto;
import com.example.smarthousebackend.service.SmartHouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/smartHouse")
public class SmartHouseController {

    private final SmartHouseService smartHouseService;


    @GetMapping("/get/{id}")
    public ResponseEntity<SmartHouseDto> getSmartHouse(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(smartHouseService.getSmartHouse(id));
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity editSmartHouse(@PathVariable Integer id, @RequestBody SmartHouseDto smartHouseDto, @RequestBody SettingsDto settingsDto) throws ExecutionException, InterruptedException {
        smartHouseService.editSmartHouseInFirebase(id, smartHouseDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SmartHouseListDto>> getAllSmartHouse(){
        return ResponseEntity.ok(smartHouseService.getAllSmartHouse());
    }




}
