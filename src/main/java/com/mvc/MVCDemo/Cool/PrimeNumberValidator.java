package com.mvc.MVCDemo.Cool;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation ,Integer> {

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
         int c=0;
         for(int i=1;i<=input;i++){
             if(input % i==0){
                 c++;
             }
         }
         if(c==2) return true;
         else
             return false;
    }
}






