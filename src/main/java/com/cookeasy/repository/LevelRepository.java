package com.cookeasy.repository;

import com.cookeasy.model.entity.LevelEntity;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
    LevelEntity findByLevelNameEnum(LevelNameEnum levelNameEnum);
}