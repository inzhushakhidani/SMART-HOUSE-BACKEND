package com.example.smarthousebackend.controllers;


import com.example.smarthousebackend.dto.SettingsDto;
import com.example.smarthousebackend.service.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/settings")
public class SettingsController {

    private final SettingsService settingsService;

    @GetMapping("/{smartHouse_id}")
    public ResponseEntity<SettingsDto> getSettings(@PathVariable Integer smartHouse_id){
        return ResponseEntity.ok(settingsService.getSettingsBySmartHouse(smartHouse_id));
    }

    @PostMapping("/edit")
    public ResponseEntity editSettings(@RequestBody SettingsDto settingsDto){
       settingsService.editSettings(settingsDto);
       return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/security/{val}")
    public ResponseEntity editSecurityMode(@PathVariable Integer val){
        settingsService.editSecurityMode(val);
        return new ResponseEntity(HttpStatus.OK);
    }
}
