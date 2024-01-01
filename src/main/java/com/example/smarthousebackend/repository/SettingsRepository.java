package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.Settings;
import com.example.smarthousebackend.entity.SmartHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

    Optional<Settings> findBySmartHouse(SmartHouse smartHouse);
}
