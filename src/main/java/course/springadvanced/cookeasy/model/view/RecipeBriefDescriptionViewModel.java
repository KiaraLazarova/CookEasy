package course.springadvanced.cookeasy.model.view;

import course.springadvanced.cookeasy.model.entity.CategoryEntity;
import course.springadvanced.cookeasy.model.entity.LevelEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeBriefDescriptionViewModel {
    private Long id;
    private String title;
    private String description;
    private long likes;
    private long saves;
    private long cooks;
    private long comments;
    private CategoryEntity categoryEntity;
    private LevelEntity levelEntity;
}