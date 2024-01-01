package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.SmartHouse;
import com.example.smarthousebackend.entity.SoilList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoilListRepository extends JpaRepository<SoilList, Integer> {
}
