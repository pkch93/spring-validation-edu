package edu.pkch.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void createPost() {
        // given
        PostRequest request = PostRequest.builder()
                .memberNumber(1L)
                .title("t") // PostRequest의 title은 5 ~ 100 사이의 길이가 필요하다.
                .contents("contents")
                .build();

        // when & then
        assertThatThrownBy(() -> postService.createPost(request))
                .isInstanceOf(ConstraintViolationException.class);
    }
}