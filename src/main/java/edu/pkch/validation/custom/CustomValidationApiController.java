package edu.pkch.validation.custom;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/custom/validation")
public class CustomValidationApiController {

    @GetMapping
    public void customValidation(@Valid CustomValidRequest request) {
        log.info("request: {}", request);
    }

    /**
     * ConstraintValidator를 통해 검증하고자하는 객체를 ConstraintValidator에 정의한 어노테이션으로는 동작하지 않음.
     */
    @GetMapping("/object")
    public void customObjectValidation(@CheckValidation CustomObjectValidRequest request) {
        log.info("request: {}", request);
    }

    /**
     * @Valid 와 함꼐 ConstraintValidator를 통해 검증하고자하는 객체를 파라미터에 정의하면 동작한다.
     * @param request ConstraintValidator를 통해 검증하고자하는 객체
     */
    @GetMapping("/object-valid")
    public void customObjectValidationWithValid(@Valid CustomObjectValidRequest request) {
        log.info("request: {}", request);
    }
}
