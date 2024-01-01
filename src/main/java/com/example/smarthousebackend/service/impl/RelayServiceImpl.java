package com.example.smarthousebackend.service.impl;

import com.example.smarthousebackend.dto.RelayDto;
import com.example.smarthousebackend.entity.Relay;
import com.example.smarthousebackend.mapper.RelayMapper;
import com.example.smarthousebackend.repository.Firebase;
import com.example.smarthousebackend.repository.RelayRepository;
import com.example.smarthousebackend.service.RelayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RelayServiceImpl implements RelayService {

    private final Firebase firebase;
    private final RelayRepository relayRepository;
    private final RelayMapper relayMapper;

    @Override
    public void clickButton(Integer device, Integer val) {
        switch (device){
            case 0:
                firebase.lamp(val);
                break;
            case 1:
                firebase.switch1(val);
                break;
            case 2:
                firebase.switch2(val);
                break;
            case 3:
                firebase.alarm(val);
                break;
            case 4:
                firebase.humidifier(val);
                break;
            case 5:
                firebase.heating(val);
                break;
            case 6:
                firebase.airflow(val);
                break;
            case 7:
                firebase.pump(val);
                break;
            default:
                break;
        }
    }

    @Override
    public RelayDto getValue() throws ExecutionException, InterruptedException {
        CompletableFuture<RelayDto> relayDto = firebase.getRelayValue();
        return relayDto.get();
    }

    @Override
    public void editMode(Integer id) {
        firebase.editMode(id);
    }

    @Override
    public List<RelayDto> getAll() {
        List<Relay> relayList = relayRepository.findAll();
        return relayMapper.toDtoList(relayList);
    }
}
