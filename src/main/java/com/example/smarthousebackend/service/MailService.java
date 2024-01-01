package com.example.smarthousebackend.service;

import com.example.smarthousebackend.dto.MailStructure;

public interface MailService {

    void sendMail(String mail, MailStructure mailStructure);
}
