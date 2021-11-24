package course.springadvanced.cookeasy.util;

import course.springadvanced.cookeasy.service.RecipeService;
import course.springadvanced.cookeasy.util.annotation.UniqueRecipeTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueRecipeTitleValidator implements ConstraintValidator<UniqueRecipeTitle, String> {
    private final RecipeService recipeService;

    @Autowired
    public UniqueRecipeTitleValidator(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return !title.trim().equals("") && !this.recipeService.isTitleOccupied(title);
    }
}