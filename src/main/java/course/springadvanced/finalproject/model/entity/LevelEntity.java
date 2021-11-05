package course.springadvanced.finalproject.model.entity;

import course.springadvanced.finalproject.model.entity.enumeration.LevelNameEnum;
import javax.persistence.*;

@Entity
@Table(name = "levels")
public class LevelEntity extends BaseEntity {
    private LevelNameEnum levelNameEnum;

    public LevelEntity() {
    }

    @Column(name = "level", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public LevelNameEnum getLevelNameEnum() {
        return this.levelNameEnum;
    }

    public void setLevelNameEnum(LevelNameEnum levelNameEnum) {
        this.levelNameEnum = levelNameEnum;
    }
}