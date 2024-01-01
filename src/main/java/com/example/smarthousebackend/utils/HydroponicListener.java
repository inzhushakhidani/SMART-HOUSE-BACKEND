package com.example.smarthousebackend.utils;

import com.example.smarthousebackend.entity.SmartHouse;
import com.example.smarthousebackend.entity.SmartHouseList;
import com.example.smarthousebackend.repository.SmartHouseListRepository;
import com.example.smarthousebackend.repository.SmartHouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Component
@RequiredArgsConstructor
public class HydroponicListener {

    private final SmartHouseListRepository smartHouseListRepository;
    private final SmartHouseRepository smartHouseRepository;

    @Scheduled(fixedRate = 20000)
    public void postUpdate() {
        SmartHouse smartHouse = smartHouseRepository.findById(1).get();

        SmartHouseList smartHouseList = new SmartHouseList();
        smartHouseList.setSmartHouse_id(smartHouse.getId());
        smartHouseList.setTemperature(smartHouse.getTemperature());
        smartHouseList.setHumidity(smartHouse.getHumidity());
        smartHouseList.setPressure(smartHouse.getPressure());
        smartHouseList.setCo2(smartHouse.getCo2());
        smartHouseList.setDoor(smartHouse.getDoor());
        smartHouseList.setMotion(smartHouse.getMotion());
        smartHouseList.setMacAddress(smartHouse.getMacAddress());
        smartHouseList.setUpdatedAt(smartHouse.getUpdatedAt());
        log.info(smartHouseList.toString());
        try {
            smartHouseListRepository.save(smartHouseList);
        } catch (Exception e) {
            log.error("Ошибка при сохранении: " + e.getMessage(), e);
        }
    }

        @Scheduled(fixedRate = 20000)
        @Transactional
        public void postUpdate2() {
            // ваш существующий код

            // Добавим условие для удаления старых данных, если их количество превышает 1000
            long rowCount = smartHouseListRepository.count();
            int maxRowCount = 1000;

            if (rowCount > maxRowCount) {
                // Определяем количество записей, которые нужно удалить
                int deleteCount = (int) (rowCount - maxRowCount);

                // Удаляем старые записи
                smartHouseListRepository.deleteOldestRecords(deleteCount);
            }
    }
}
