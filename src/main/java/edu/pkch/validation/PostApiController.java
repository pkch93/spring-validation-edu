package edu.pkch.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Slf4j
@RestController
@Validated
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

    @GetMapping("/{postId}")
    public void readPost(@PathVariable @Min(1) Long postId) {
        log.info("postId: {}", postId);
    }
}
