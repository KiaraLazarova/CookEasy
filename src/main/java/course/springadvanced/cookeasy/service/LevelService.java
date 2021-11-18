package course.springadvanced.cookeasy.service;

import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;

public interface LevelService {
    void initializeLevels();
    LevelEntity findLevelByLevelName(LevelNameEnum levelNameEnum);
}