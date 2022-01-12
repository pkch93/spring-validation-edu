package edu.pkch.validation.grouping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class GroupDValidationServiceTest {

    @Autowired
    private GroupDValidationService groupDValidationService;

    /**
     * 클래스 레벨에 붙은 그룹에 대해 Bean Validation을 수행한다.
     * GroupDValidationService에는 클래스 레벨에 @Validated(GroupD.class)가 붙어있다.
     */
    @Test
    void validateForGroupD() {
        // given
        GroupingRequest request = GroupingRequest.builder()
                .a(3)
                .b(0)
                .c(0)
                .d(101)
                .build();

        // when & then
        assertThatThrownBy(() -> groupDValidationService.validateForGroupD(request))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("validateForGroupD.request.d: " + GroupingRequest.D_INVALID_MESSAGE);
    }

    /**
     * 클래스 레벨과 메서드 레벨의 @Validated에 그룹을 지정한 경우 메서드의 그룹 대상으로만 유효성 검증을 한다..
     */
    @Test
    void validateForGroupAAndD() {
        // given
        GroupingRequest request = GroupingRequest.builder()
                .a(3)
                .b(0)
                .c(0)
                .d(101)
                .build();

        // when & then
        assertThatThrownBy(() -> groupDValidationService.validateForGroupAAndD(request))
                .isInstanceOf(ConstraintViolationException.class)
                .hasMessage("validateForGroupAAndD.request.a: " + GroupingRequest.A_INVALID_MESSAGE);
    }
}