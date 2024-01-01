package com.example.smarthousebackend.controllers;


import com.example.smarthousebackend.security.auth.AuthenticationService;
import com.example.smarthousebackend.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AuthenticationService authenticationService;

    @PostMapping("/createUser")
    public ResponseEntity createUser(@RequestBody RegisterRequest registerRequest){
        authenticationService.registerUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/createAdmin")
    public ResponseEntity createAdmin(@RequestBody RegisterRequest registerRequest){
        authenticationService.registerAdmin(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
