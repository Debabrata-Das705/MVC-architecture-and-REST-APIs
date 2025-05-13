package com.mvc.MVCDemo.Cool;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(
        validatedBy ={PrimeNumberValidator.class}
)
public @interface PrimeNumberValidation {
    String message() default "{Enter prime}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
