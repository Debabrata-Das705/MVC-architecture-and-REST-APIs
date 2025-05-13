package com.mvc.MVCDemo.Annotation;

import com.mvc.MVCDemo.Advice.EmployeeRoleValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles= Arrays.asList("ADMIN","USER");
        return roles.contains(s);
    }
}
