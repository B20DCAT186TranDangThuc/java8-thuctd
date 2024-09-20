package com.digidinos.springbootadvance.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UniqueCodeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueCode {
    String message() default "Code must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
