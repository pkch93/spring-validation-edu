package edu.pkch.validation.grouping;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class GroupingValidationApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GroupA로 그루핑된 a 쿼리 파라미터는 유효성 검증하지 않는다.")
    void validate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/grouping/valid")
                .queryParam("a", "3")
                .queryParam("b", "10")
                .queryParam("c", "100")
                .queryParam("d", "100"))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Method에 매핑된 Validated는 영향을 미치지 않는다.")
    void validateByMethodValidated() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/grouping/valid/method-groupA")
                .queryParam("a", "3")
                .queryParam("b", "10")
                .queryParam("c", "100")
                .queryParam("d", "100"))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Parameter에 매핑된 Validated에 GroupA로 매핑하면 GroupA인 필드만 검증한다.")
    void validateByParameterValidated() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/grouping/valid/parameter-groupA")
                .queryParam("a", "10")
                .queryParam("b", "5")
                .queryParam("c", "100")
                .queryParam("d", "100"))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Parameter에 매핑된 Validated에 GroupA로 매핑하면 GroupA인 필드만 검증한다.")
    void validateByParameterValidated2() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/grouping/valid/parameter-groupA")
                .queryParam("a", "3")
                .queryParam("b", "10")
                .queryParam("c", "100")
                .queryParam("d", "100"))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}