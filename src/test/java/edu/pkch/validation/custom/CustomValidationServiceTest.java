package edu.pkch.validation.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomValidationServiceTest {

    @Autowired
    private CustomValidationService customValidationService;

    @Test
    @DisplayName("@Validated 컴포넌트 빈에서 ConstraintValidator 정상 동작 여부 확인")
    void validate() {
        // given
        CustomValidRequest request = CustomValidRequest.builder()
                .a("02-000-0000")
                .b(10)
                .build();

        // when
        assertDoesNotThrow(() -> customValidationService.validate(request));
    }

    @Test
    @DisplayName("@Validated 컴포넌트 빈에서 ConstraintValidator 실패 동작 여부 확인")
    void validate_thenFailed() {
        // given
        CustomValidRequest request = CustomValidRequest.builder()
                .a("054-000-0000")
                .b(10)
                .build();

        // when
        assertThatThrownBy(() -> customValidationService.validate(request))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("validate.request.a: " + CheckSeoulPhoneNumber.INVALID_SEOUL_PHONE_FORMAT_MESSAGE);
    }

    @Test
    @DisplayName("@Validated 컴포넌트 빈에서 단일 String ConstraintValidator 정상 동작 여부 확인")
    void validatePrimitive() {
        assertDoesNotThrow(() -> customValidationService.validatePrimitive("02-000-0000"));
    }

    @Test
    @DisplayName("@Validated 컴포넌트 빈에서 단일 String ConstraintValidator 실패 동작 여부 확인")
    void validatePrimitive_thenFailed() {
        assertThatThrownBy(() -> customValidationService.validatePrimitive("054-000-0000"))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("validatePrimitive.phoneNumber: " + CheckSeoulPhoneNumber.INVALID_SEOUL_PHONE_FORMAT_MESSAGE);
    }

    @Test
    @DisplayName("@Validated 컴포넌트 빈에서 @Valid 없이 단일 String ConstraintValidator 정상 동작 여부 확인")
    void validatePrimitiveWithoutValid() {
        assertDoesNotThrow(() -> customValidationService.validatePrimitiveWithoutValid("02-000-0000"));
    }

    @Test
    @DisplayName("@Validated 컴포넌트 빈에서 @Valid 없이 단일 String ConstraintValidator 실패 동작 여부 확인")
    void validatePrimitiveWithoutValid_thenFailed() {
        assertThatThrownBy(() -> customValidationService.validatePrimitiveWithoutValid("054-000-0000"))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("validatePrimitiveWithoutValid.phoneNumber: " + CheckSeoulPhoneNumber.INVALID_SEOUL_PHONE_FORMAT_MESSAGE);
    }

    @Test
    @DisplayName("검증하고자 하는 객체에 Validate 용 어노테이션 (@CheckValidation)을 붙이더라도 @Valid나 Validate 용 어노테이션 없이는 유효성 검증이 동작하지 않는다.")
    void validateObject() {
        // given
        CustomObjectValidRequest request = CustomObjectValidRequest.builder()
                .a("12345678910")
                .b(10)
                .build();

        // when & then
        assertDoesNotThrow(() -> customValidationService.validateObject(request));
    }

    @Test
    @DisplayName("검증하고자 하는 객체에 Validate 용 어노테이션(@CheckValidation)을 붙이면 유효성 검증이 동작한다.")
    void validateObjectWithCustomAnnotation() {
        // given
        CustomObjectValidRequest request = CustomObjectValidRequest.builder()
                .a("12345678910")
                .b(10)
                .build();

        // when & then
        assertThatThrownBy(() -> customValidationService.validateObjectWithCustomAnnotation(request))
                .isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    @DisplayName("검증하고자 하는 클래스에 Validate 용 어노테이션(@CheckValidation)을 붙이고 파라미터에 @Valid를 붙이면 유효성 검증이 동작한다.")
    void validateObjectWithValid() {
        // given
        CustomObjectValidRequest request = CustomObjectValidRequest.builder()
                .a("12345678910")
                .b(10)
                .build();

        // when & then
        assertThatThrownBy(() -> customValidationService.validateObjectWithValid(request))
                .isInstanceOf(ConstraintViolationException.class);
    }
}