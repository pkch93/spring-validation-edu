package edu.pkch.validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SeoulPhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckSeoulPhoneNumber {
    String INVALID_SEOUL_PHONE_FORMAT_MESSAGE = "서울지역 번호가 아닙니다.";

    String message() default INVALID_SEOUL_PHONE_FORMAT_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
