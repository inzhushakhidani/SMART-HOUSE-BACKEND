package com.example.smarthousebackend.controllers;

import com.example.smarthousebackend.dto.RelayDto;
import com.example.smarthousebackend.service.RelayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/relay")
public class RelayController {

    private final RelayService relayService;

    @GetMapping("/{id}/{val}")
    public ResponseEntity edit(@PathVariable Integer id, @PathVariable Integer val){
        relayService.clickButton(id, val);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<RelayDto> get() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(relayService.getValue());
    }

    @GetMapping("/mode/{id}")
    public ResponseEntity editMode(@PathVariable Integer id) {
        relayService.editMode(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RelayDto>> getAll(){
        return ResponseEntity.ok(relayService.getAll());
    }

}
