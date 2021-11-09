package course.springadvanced.finalproject.service.impl;

import course.springadvanced.finalproject.model.entity.LevelEntity;
import course.springadvanced.finalproject.model.entity.enumeration.LevelNameEnum;
import course.springadvanced.finalproject.repository.LevelRepository;
import course.springadvanced.finalproject.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void initializeLevels() {
        if(this.levelRepository.count() != 0) return;

        LevelEntity beginner = new LevelEntity();
        beginner.setLevelNameEnum(LevelNameEnum.BEGINNER);

        this.levelRepository.saveAndFlush(beginner);

        LevelEntity intermediate = new LevelEntity();
        intermediate.setLevelNameEnum(LevelNameEnum.INTERMEDIATE);

        this.levelRepository.saveAndFlush(intermediate);

        LevelEntity advanced = new LevelEntity();
        advanced.setLevelNameEnum(LevelNameEnum.ADVANCED);

        this.levelRepository.saveAndFlush(advanced);
    }
}