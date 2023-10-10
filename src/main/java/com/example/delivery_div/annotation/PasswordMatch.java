package com.example.delivery_div.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy= PasswordMatchValidator.class)
@Documented
public @interface PasswordMatch {

    String message() default "${com.example.delivery_div.annotation.PasswordMatch.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

