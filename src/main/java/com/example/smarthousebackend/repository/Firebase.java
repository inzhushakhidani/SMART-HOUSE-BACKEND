package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.dto.*;
import com.example.smarthousebackend.entity.*;
import com.example.smarthousebackend.mapper.RelayMapper;
import com.example.smarthousebackend.service.MailService;
import com.example.smarthousebackend.service.SmartHouseService;
import com.example.smarthousebackend.service.SoilService;
import com.google.firebase.database.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class Firebase {

    private final SmartHouseService smartHouseService;
    private final SmartHouseRepository smartHouseRepository;
    private final RelayMapper relayMapper;
    private final RelayRepository relayRepository;
    private final SoilService soilService;
    private final MailService mailService;
    private final UserRepository userRepository;
    private final SoilRepository soilRepository;
    private final SoilListRepository soilListRepository;

    public void editSettings(SettingsDto settings){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Settings");
        database.child("humidity").child("max").setValueAsync(settings.getMaxHumidity());
        database.child("humidity").child("avg").setValueAsync(settings.getAvgHumidity());
        database.child("humidity").child("min").setValueAsync(settings.getMinHumidity());
        database.child("temperature").child("max").setValueAsync(settings.getMaxTemperature());
        database.child("temperature").child("avg").setValueAsync(settings.getAvgTemperature());
        database.child("temperature").child("min").setValueAsync(settings.getMinTemperature());
        database.child("soil_moisture").child("avg").setValueAsync(settings.getAvgSoilMoisture());
        database.child("soil_moisture").child("min").setValueAsync(settings.getMinSoilMoisture());
        database.child("time").child("start").setValueAsync(settings.getStartTime());
        database.child("time").child("stop").setValueAsync(settings.getStopTime());
        database.child("mode").setValueAsync(settings.getMode());
        database.child("security").setValueAsync(settings.getSecurity());

    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void saveHydroponicData(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @SneakyThrows
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // onDataChange вызывается каждый раз, когда данные в базе данных изменяются

                // Получаем значение из DataSnapshot
                SmartHouseDto smartHouseDto = SmartHouseDto.builder()
                        .co2(dataSnapshot.child("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68").child("CO2").getValue(Double.class))
                        .door(dataSnapshot.child("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68").child("Door").getValue(Integer.class))
                        .humidity(dataSnapshot.child("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68").child("Humidity").getValue(Double.class))
                        .motion(dataSnapshot.child("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68").child("Motion").getValue(Integer.class))
                        .pressure(dataSnapshot.child("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68").child("Pressure").getValue(Double.class))
                        .temperature(dataSnapshot.child("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68").child("Temperature").getValue(Double.class))
                        .macAddress("08:B6:1F:C1:8C:68")
                        .build();


                Relay relay = Relay.builder()
                        .lamp(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("0").getValue(Integer.class))
                        .switch1(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("1").getValue(Integer.class))
                        .switch2(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("2").getValue(Integer.class))
                        .alarm(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("3").getValue(Integer.class))
                        .humidifier(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("4").getValue(Integer.class))
                        .heating(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("5").getValue(Integer.class))
                        .airflow(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("6").getValue(Integer.class))
                        .pump(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("7").getValue(Integer.class))
                        .mode(dataSnapshot.child("Settings").child("mode").getValue(Integer.class))
                        .localDate(new Date())
                        .build();

                relayRepository.save(relay);


                SettingsDto settingsDto = SettingsDto.builder()
                        .maxHumidity(dataSnapshot.child("Settings").child("humidity").child("max").getValue(Double.class))
                        .avgHumidity(dataSnapshot.child("Settings").child("humidity").child("avg").getValue(Double.class))
                        .minHumidity(dataSnapshot.child("Settings").child("humidity").child("min").getValue(Double.class))
                        .maxTemperature(dataSnapshot.child("Settings").child("temperature").child("max").getValue(Double.class))
                        .avgTemperature(dataSnapshot.child("Settings").child("temperature").child("avg").getValue(Double.class))
                        .minTemperature(dataSnapshot.child("Settings").child("temperature").child("min").getValue(Double.class))
                        .startTime(dataSnapshot.child("Settings").child("time").child("start").getValue(String.class))
                        .stopTime(dataSnapshot.child("Settings").child("time").child("stop").getValue(String.class))
                        .avgSoilMoisture(dataSnapshot.child("Settings").child("soil_moisture").child("avg").getValue(Double.class))
                        .minSoilMoisture(dataSnapshot.child("Settings").child("soil_moisture").child("min").getValue(Double.class))
                        .mode(dataSnapshot.child("Settings").child("mode").getValue(Integer.class))
                        .security(dataSnapshot.child("Settings").child("security").getValue(Integer.class))
                        .build();

                smartHouseService.editSmartHouse(1, smartHouseDto, settingsDto);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                log.info("Ошибка чтения данных: " + databaseError.toException());
            }
        });
    }

    public void lamp(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("0").setValueAsync(val);
    }
    public void switch1(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("1").setValueAsync(val);
    }
    public void switch2(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("2").setValueAsync(val);
    }
    public void alarm(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("3").setValueAsync(val);
    }
    public void humidifier(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("4").setValueAsync(val);
    }
    public void heating(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("5").setValueAsync(val);
    }
    public void airflow(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("6").setValueAsync(val);
    }
    public void pump(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-1").child("Relay").child("08:B6:1F:C1:8D:28");
        database.child("7").setValueAsync(val);
    }
    public void motion(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68");
        database.child("Motion").setValueAsync(val);
    }
    public void door(Integer val){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Master-2").child("BME-280").child("08:B6:1F:C1:8C:68");
        database.child("Door").setValueAsync(val);
    }


    @Async
    @Scheduled(fixedRate = 2000)
    public void saveSoil() {
        log.info("Soil check");
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @SneakyThrows
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                log.info("Soil check1");

                SoilDto soilDto = SoilDto.builder()
                        .flow(dataSnapshot.child("Master-2").child("Soil").child("08:B6:1F:C1:94:24").child("Flow").getValue(Double.class))
                        .moisture(dataSnapshot.child("Master-2").child("Soil").child("08:B6:1F:C1:94:24").child("Moisture").getValue(Double.class))
                        .timestamp(dataSnapshot.child("Master-2").child("Soil").child("08:B6:1F:C1:94:24").child("Timestamp").getValue(Long.class)+"")
                        .totalFlow(soilService.get().getTotalFlow()+dataSnapshot.child("Master-2").child("Soil").child("08:B6:1F:C1:94:24").child("Flow").getValue(Double.class))
                        .updatedAt(new Date())
                        .build();

                log.info(soilDto.toString());
                soilService.save(soilDto);

                SoilList soilList = SoilList.builder()
                        .flow(soilDto.getFlow())
                        .moisture(soilDto.getMoisture())
                        .timestamp(soilDto.getTimestamp())
                        .totalFlow(soilDto.getTotalFlow())
                        .updatedAt(new Date()).build();
                soilListRepository.save(soilList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                log.info("Ошибка чтения данных: " + databaseError.toException());
            }
        });
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void checkSmartHouse() throws ExecutionException, InterruptedException {
        if(getMode()==0) {
            SmartHouse smartHouse = smartHouseRepository.findById(1).get();
            Settings settings = smartHouse.getSettings();
            Soil soil = soilRepository.findById(1).get();

            if(soil.getMoisture()<settings.getMinSoilMoisture()){
                pump(0);
            }else if(soil.getMoisture()>=settings.getAvgSoilMoisture()){
                pump(1);
            }

            if(smartHouse.getMotion() == 1){
                log.info("motion");
                if(!motionExecuted) {
                    motionExecuted = true;
                    motion(0);
                    lamp(0);
                    switch1(0);
                    switch2(0);

                    Thread.sleep(20000);
                    lamp(1);
                    switch1(1);
                    switch2(1);
                    motionExecuted = false;
                }
            }

            if (smartHouse.getDoor() == 1 && settings.getSecurity() == 0) {
                door(0);
                if(!doorExecuted) {
                    doorExecuted = true;
                    alarm(0);
                    MailStructure mailStructure = new MailStructure();
                    mailStructure.setSubject("ПРОНИКНОВЕНИЕ!");
                    mailStructure.setMessage("Кто-то вошел в дом.\nВремя: " + new Date());

                    List<User> users = userRepository.findAll();
                    for (User user : users) {
                        mailService.sendMail(user.getEmail(), mailStructure);
                    }
                    doorExecuted = false;
                }
            } else {
                alarm(1);
            }


            if (smartHouse.getTemperature() <= settings.getMinTemperature()) {
                heating(0);//Обогреватель вкл
                log.info("heating on");
            }
            if (smartHouse.getTemperature() >= settings.getAvgTemperature()) {
                heating(1);//Обогреватель выкл
                log.info("heating off");
            }

            if (smartHouse.getTemperature() >= settings.getMaxTemperature() || smartHouse.getHumidity() >= settings.getMaxHumidity()) {
                airflow(0);//Вытяжка вкл
                log.info("airflow on");
            } else {
                airflow(1);//Вытяжка выкл
                log.info("airflow off");
            }

            if (smartHouse.getHumidity() <= settings.getMinHumidity()) {
                humidifier(0);//Увлажнитель вкл
                log.info("humidifier on");
            }
            if (smartHouse.getHumidity() >= settings.getAvgHumidity()) {
                humidifier(1);//Увлажнитель выкл
                log.info("humidifier off");
            }
        }
    }
    private boolean doorExecuted = false;
    private boolean motionExecuted = false;

    public CompletableFuture<RelayDto> getRelayValue() {
        CompletableFuture<RelayDto> future = new CompletableFuture<>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @SneakyThrows
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                RelayDto relayDto = RelayDto.builder()
                        .lamp(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("0").getValue(Integer.class))
                        .switch1(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("1").getValue(Integer.class))
                        .switch2(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("2").getValue(Integer.class))
                        .alarm(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("3").getValue(Integer.class))
                        .humidifier(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("4").getValue(Integer.class))
                        .heating(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("5").getValue(Integer.class))
                        .airflow(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("6").getValue(Integer.class))
                        .pump(dataSnapshot.child("Master-1").child("Relay").child("08:B6:1F:C1:8D:28").child("7").getValue(Integer.class))
                        .mode(dataSnapshot.child("Settings").child("mode").getValue(Integer.class))
                        .localDate(new Date())
                        .build();
                future.complete(relayDto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                log.info("Ошибка чтения данных: " + databaseError.toException());
                future.completeExceptionally(databaseError.toException());
            }
        });

        return future;
    }

    public void editMode(Integer id){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Settings");
        database.child("mode").setValueAsync(id);
    }

    public Integer getMode() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> mode = new CompletableFuture<>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Settings");
        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mode.complete(dataSnapshot.child("mode").getValue(Integer.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                log.info("Ошибка чтения данных: " + databaseError.toException());
            }
        });
        return mode.get();
    }

    public void editSecurity(Integer val) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Settings");
        database.child("security").setValueAsync(val);

    }
}


