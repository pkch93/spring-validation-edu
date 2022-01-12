package edu.pkch.validation.grouping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/grouping/valid")
public class GroupingValidationApiController {

    @GetMapping
    public void validate(@Valid GroupingRequest request) {
        log.info("grouping request: {}", request);
    }

    @GetMapping("/method-groupA")
    @Validated(GroupA.class)
    public void validateGroupAByMethodValidated(@Valid GroupingRequest request) {
        log.info("grouping request: {}", request);
    }

    @GetMapping("/parameter-groupA")
    public void validateGroupAByParameterValidated(@Validated(GroupA.class) GroupingRequest request) {
        log.info("grouping request: {}", request);
    }
}
