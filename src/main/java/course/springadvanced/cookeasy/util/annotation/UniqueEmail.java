package course.springadvanced.cookeasy.util.annotation;

import course.springadvanced.cookeasy.util.UniqueEmailValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "Email must be unique.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
