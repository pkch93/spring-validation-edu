package edu.pkch.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Slf4j
@Service
@Validated
public class PostService {

    public void createPost(@Valid PostRequest request) {
        log.info("request: {}", request);
    }
}
