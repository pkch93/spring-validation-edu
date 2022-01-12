package edu.pkch.validation.grouping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class GroupingValidationService {

    public void groupingValidate(GroupingRequest request) {
        log.info("grouping request: {}", request);
    }

    public void groupingValidateWithValidated(@Validated GroupingRequest request) {
        log.info("grouping request: {}", request);
    }

    public void groupingValidateWithValid(@Valid GroupingRequest request) {
        log.info("grouping request: {}", request);
    }

    @Validated(GroupA.class)
    public void groupingValidateWithValidatedGroupA(@Valid GroupingRequest request) {
        log.info("grouping request: {}", request);
    }
}
