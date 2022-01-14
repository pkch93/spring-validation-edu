package edu.pkch.validation.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class CustomValidationService {

    public void validate(@Valid CustomValidRequest request) {
        log.info("request: {}", request);
    }

    public void validatePrimitive(@Valid @CheckSeoulPhoneNumber String phoneNumber) {
        log.info("phoneNumber: {}", phoneNumber);
    }

    public void validatePrimitiveWithoutValid(@CheckSeoulPhoneNumber String phoneNumber) {
        log.info("phoneNumber: {}", phoneNumber);
    }

    /**
     * Validate용 커스텀 어노테이션(@CheckValidation)을 대상 클래스인 CustomObjectValidRequest에 붙이는 것만으로는
     * 유효성 검증이 동작하지 않는다.
     */
    public void validateObject(CustomObjectValidRequest request) {
        log.info("request: {}", request);
    }

    /**
     * Validate용 커스텀 어노테이션인 @CheckValidation를 붙이는 경우 CustomObjectValidator를 통한 유효성 검증 동작
     */
    public void validateObjectWithCustomAnnotation(@CheckValidation CustomObjectValidRequest request) {
        log.info("request: {}", request);
    }

    /**
     * Validate용 커스텀 어노테이션(@CheckValidation)을 대상 클래스인 CustomObjectValidRequest에 붙이고
     * 파라미터에는 @Valid를 붙이면 CustomObjectValidtor를 통한 유효성 검증 동작
     */
    public void validateObjectWithValid(@Valid CustomObjectValidRequest request) {
        log.info("request: {}", request);
    }
}
