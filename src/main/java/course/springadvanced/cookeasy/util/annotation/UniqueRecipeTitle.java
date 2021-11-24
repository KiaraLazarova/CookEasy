package course.springadvanced.cookeasy.util.annotation;

import course.springadvanced.cookeasy.util.UniqueRecipeTitleValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = UniqueRecipeTitleValidator.class)
public @interface UniqueRecipeTitle {
    String message() default "Recipe title must be unique.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}