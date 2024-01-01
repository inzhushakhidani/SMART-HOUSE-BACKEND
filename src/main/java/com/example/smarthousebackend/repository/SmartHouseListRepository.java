package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.SmartHouseList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SmartHouseListRepository  extends JpaRepository<SmartHouseList, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM SmartHouseList h WHERE h.id IN (SELECT hl.id FROM SmartHouseList hl ORDER BY hl.updatedAt ASC LIMIT :count)")
    void deleteOldestRecords(@Param("count") int count);
}
