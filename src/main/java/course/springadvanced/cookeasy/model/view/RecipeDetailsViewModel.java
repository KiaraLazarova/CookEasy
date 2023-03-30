package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import course.springadvanced.cookeasy.model.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RecipeDetailsViewModel {
    private Long id;
    private LocalDate createdOn;
    private String title;
    private int hours;
    private int minutes;
    private String description;
    private long likes;
    private long saves;
    private long cooks;
    private long comments;
    private CategoryEntity categoryEntity;
    private LevelEntity levelEntity;
    private UserEntity author;
}