package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.Relay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelayRepository extends JpaRepository<Relay, Integer> {
}
