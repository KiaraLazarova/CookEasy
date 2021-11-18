package course.springadvanced.cookeasy.repository;

import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity, Long> {
    LevelEntity findByLevelNameEnum(LevelNameEnum levelNameEnum);
}