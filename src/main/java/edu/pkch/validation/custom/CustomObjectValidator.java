package edu.pkch.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomObjectValidator implements ConstraintValidator<CheckValidation, CustomObjectValidRequest> {

    @Override
    public boolean isValid(CustomObjectValidRequest request, ConstraintValidatorContext context) {
        String a = request.getA();
        int b = request.getB();
        return a.length() < 10 && b >= 10;
    }
}
