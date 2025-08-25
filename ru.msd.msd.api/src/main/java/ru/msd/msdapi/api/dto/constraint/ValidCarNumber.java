package ru.msd.msdapi.api.dto.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CarNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCarNumber {
    String message() default "Invalid car number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
