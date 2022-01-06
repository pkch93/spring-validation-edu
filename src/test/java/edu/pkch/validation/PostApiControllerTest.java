package edu.pkch.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PostApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * {@link org.springframework.validation.annotation.Validated} 어노테이션은 파라미터 수준에 달려있어야
     * JSR-303 규약에 따른 유효성 검사를 진행한다.
     * 클래스, 메서드 수준에 달려있다면 그룹을 지정한다.
     */
    @Test
    @DisplayName("메서드 수준의 @Validated는 유효성 검사에 영향을 미치지 않는다.")
    void createPost() throws Exception {
        PostRequest request = PostRequest.builder()
                .memberNumber(1L)
                .title("t")
                .contents("contents")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void createPostByValid() throws Exception {
        PostRequest request = PostRequest.builder()
                .memberNumber(1L)
                .title("title")
                .contents("contents")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/valid")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("게시글 요청의 title의 길이는 5 ~ 100자 이내가 아니면 400 BAD REQUEST 응답을 받는다.")
    void createPost_whenTitleLengthIs1_thenResponseStatusIsBadRequest() throws Exception {
        PostRequest request = PostRequest.builder()
                .memberNumber(1L)
                .title("t")
                .contents("contents")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/valid")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createPostByValidated() throws Exception {
        PostRequest request = PostRequest.builder()
                .memberNumber(1L)
                .title("title")
                .contents("contents")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/validated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("게시글 요청의 title의 길이는 5 ~ 100자 이내가 아니면 400 BAD REQUEST 응답을 받는다.")
    void createPostByValidated_whenTitleLengthIs1_thenResponseStatusIsBadRequest() throws Exception {
        PostRequest request = PostRequest.builder()
                .memberNumber(1L)
                .title("t")
                .contents("contents")
                .build();

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/posts/validated")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}