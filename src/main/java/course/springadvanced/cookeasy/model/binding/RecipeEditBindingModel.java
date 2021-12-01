package course.springadvanced.cookeasy.model.binding;

import course.springadvanced.cookeasy.model.entity.enumeration.CategoryNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import javax.validation.constraints.*;

public class RecipeEditBindingModel {
    private Long id;
    @NotNull
    @Min(value = 0)
    @Max(value = 23)
    private int hours;
    @NotNull
    @Min(value = 1)
    @Max(value = 59)
    private int minutes;
    @NotNull
    private CategoryNameEnum categoryNameEnum;
    @NotNull
    private LevelNameEnum levelNameEnum;
    @NotBlank
    @Size(min = 10)
    private String description;

    public RecipeEditBindingModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public CategoryNameEnum getCategoryNameEnum() {
        return this.categoryNameEnum;
    }

    public void setCategoryNameEnum(CategoryNameEnum categoryNameEnum) {
        this.categoryNameEnum = categoryNameEnum;
    }

    public LevelNameEnum getLevelNameEnum() {
        return this.levelNameEnum;
    }

    public void setLevelNameEnum(LevelNameEnum levelNameEnum) {
        this.levelNameEnum = levelNameEnum;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}