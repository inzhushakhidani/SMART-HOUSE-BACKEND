package com.example.smarthousebackend.repository;

import com.example.smarthousebackend.entity.SmartHouse;
import com.example.smarthousebackend.entity.SoilList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SoilListRepository extends JpaRepository<SoilList, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM SoilList h WHERE h.id IN (SELECT hl.id FROM SoilList hl ORDER BY hl.updatedAt ASC LIMIT :count)")
    void deleteOldestRecords(@Param("count") int count);
}
