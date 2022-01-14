package edu.pkch.validation.custom;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CustomValidationApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("CheckSeoulPhoneNumber 어노테이션 성공 동작 확인")
    void customValidation() throws Exception {
        // given & when
        MockHttpServletResponse response = mockMvc.perform(get("/api/custom/validation")
                .queryParam("a", "02-000-0000")
                .queryParam("b", "10")
                .queryParam("c", "true")
        )
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("CheckSeoulPhoneNumber 어노테이션 실패 동작 확인")
    void customValidation_thenFalied() throws Exception {
        // given & when
        MockHttpServletResponse response = mockMvc.perform(get("/api/custom/validation")
                .queryParam("a", "054-000-0000") // 서울 지역 번호 포멧이 아니므로 실패
                .queryParam("b", "10")
                .queryParam("c", "true")
        )
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("컨트롤러에서 커스텀 Validate 어노테이션만으로는 유효성 검증 동작이 되지 않는다.")
    void customObjectValidation_thenFailed() throws Exception {
        // given & when
        MockHttpServletResponse response = mockMvc.perform(get("/api/custom/validation/object")
                .queryParam("a", "12345678910") // 길이가 10이 넘으므로 실패
                .queryParam("b", "10")
        )
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("컨트롤러에서 @Valid와 커스텀 Validate가 붙은 객체의 유효성 검증 동작 확인")
    void customObjectValidationWithValid() throws Exception {
        // given & when
        MockHttpServletResponse response = mockMvc.perform(get("/api/custom/validation/object-valid")
                .queryParam("a", "12345678910") // 길이가 10이 넘으므로 실패
                .queryParam("b", "10")
        )
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}