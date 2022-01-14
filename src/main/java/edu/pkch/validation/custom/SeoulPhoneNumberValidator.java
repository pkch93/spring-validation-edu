package edu.pkch.validation.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.regex.Pattern;

public class SeoulPhoneNumberValidator implements ConstraintValidator<CheckSeoulPhoneNumber, String> {
    private static final Pattern SEOUL_PHONE_REGEX = Pattern.compile(
            "^02-[0-9]{3,4}-[0-9]{3,4}$"
    );

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return Objects.nonNull(phoneNumber) && SEOUL_PHONE_REGEX.matcher(phoneNumber).matches();
    }
}
