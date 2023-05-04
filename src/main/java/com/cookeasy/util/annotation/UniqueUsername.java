package com.cookeasy.util.annotation;

import com.cookeasy.util.UniqueUsernameValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Constraint(validatedBy = UniqueUsernameValidator.class)
public @interface UniqueUsername {
    String message() default "Username must be unique.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}