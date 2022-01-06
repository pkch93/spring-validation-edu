package edu.pkch.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    @PostMapping @Validated
    public void createPost(@RequestBody PostRequest request) {
        log.info("request: {}", request);
    }

    @PostMapping("/valid")
    public void createPostByValid(@Valid @RequestBody PostRequest request) {
        log.info("request: {}", request);
    }

    @PostMapping("/validated")
    public void createPostByValidated(@Validated @RequestBody PostRequest request) {
        log.info("request: {}", request);
    }
}
