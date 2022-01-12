package edu.pkch.validation.grouping;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class GroupingValidationServiceTest {

    @Autowired
    private GroupingValidationService groupingValidationService;

    @Test
    void groupingValidate() {
        // given
        GroupingRequest groupingRequest = GroupingRequest.builder()
                .a(3)
                .b(5) // b는 @Min(10)으로 제한되어있음.
                .c(100)
                .build();

        // when & then
        assertDoesNotThrow(() -> groupingValidationService.groupingValidate(groupingRequest));
    }

    /**
     * @Validated 가 붙은 경우 @Validated 어노테이션이 붙은 파라미터는 Bean Validation을 지원받을 수 없다.
     */
    @Test
    void groupingValidateWithValidated() {
        // given
        GroupingRequest groupingRequest = GroupingRequest.builder()
                .a(3)
                .b(5)
                .c(100)
                .build();

        // when & then
        assertDoesNotThrow(() -> groupingValidationService.groupingValidateWithValidated(groupingRequest));
    }

    /**
     * @Validated 가 붙은 경우 @Valid 어노테이션이 붙은 파라미터는 메서드 AOP를 통해 Bean Validation을 지원받을 수 있다.
     */
    @Test
    void groupingValidateWithValid() {
        // given
        GroupingRequest groupingRequest = GroupingRequest.builder()
                .a(3)
                .b(5)
                .c(100)
                .build();

        // when & then
        assertThatThrownBy(() -> groupingValidationService.groupingValidateWithValid(groupingRequest))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("groupingValidateWithValid.request.b: " + GroupingRequest.B_INVALID_MESSAGE);
    }

    /**
     * @Validated는 메서드 레벨에서 group을 지정할 수 있다.
     */
    @Test
    void groupingValidateWithValidatedGroupA() {
        // given
        GroupingRequest groupingRequest = GroupingRequest.builder()
                .a(3)
                .b(10)
                .c(100)
                .build();

        // when & then
        assertThatThrownBy(() -> groupingValidationService.groupingValidateWithValidatedGroupA(groupingRequest))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("groupingValidateWithValidatedGroupA.request.a: " + GroupingRequest.A_INVALID_MESSAGE);
    }
}