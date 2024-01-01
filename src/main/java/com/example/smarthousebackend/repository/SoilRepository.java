package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.Soil;
import com.example.smarthousebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Integer> {
}
