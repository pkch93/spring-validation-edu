package edu.pkch.validation.grouping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated(GroupD.class)
public class GroupDValidationService {

    public void validateForGroupD(@Valid GroupingRequest request) {
        log.info("grouping request: {}", request);
    }

    @Validated(GroupA.class)
    public void validateForGroupAAndD(@Valid GroupingRequest request) {
        log.info("grouping request: {}", request);
    }
}
