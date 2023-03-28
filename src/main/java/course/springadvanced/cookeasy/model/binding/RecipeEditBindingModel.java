package course.springadvanced.cookeasy.model.binding;

import course.springadvanced.cookeasy.model.entity.enumeration.CategoryNameEnum;
import course.springadvanced.cookeasy.model.entity.enumeration.LevelNameEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class RecipeEditBindingModel {
    private Long id;
    @NotNull
    @Min(value = 0)
    @Max(value = 23)
    private int hours;
    @NotNull
    @Min(value = 0)
    @Max(value = 59)
    private int minutes;
    @NotNull
    private CategoryNameEnum categoryNameEnum;
    @NotNull
    private LevelNameEnum levelNameEnum;
    @NotBlank
    @Size(min = 10)
    private String description;
}