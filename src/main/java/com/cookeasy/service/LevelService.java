package com.cookeasy.service;

import com.cookeasy.model.entity.LevelEntity;
import com.cookeasy.model.entity.enumeration.LevelNameEnum;

public interface LevelService {
    void initializeLevels();
    LevelEntity findLevelByLevelName(LevelNameEnum levelNameEnum);
}