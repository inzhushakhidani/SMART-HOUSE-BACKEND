package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.SmartHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartHouseRepository extends JpaRepository<SmartHouse, Integer> {
}
