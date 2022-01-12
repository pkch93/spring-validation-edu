package edu.pkch.validation.grouping;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupingRequest {
    public static final String A_INVALID_MESSAGE = "a는 5 이상이어야 합니다.";
    public static final String B_INVALID_MESSAGE = "b는 10 이상이어야 합니다.";
    public static final String D_INVALID_MESSAGE = "d는 100이하여야 합니다.";

    @Min(value = 5, groups = GroupA.class, message = A_INVALID_MESSAGE)
    private int a;

    @Min(value = 10, message = B_INVALID_MESSAGE)
    private int b;

    private int c;

    @Max(value = 100, groups = GroupD.class, message = D_INVALID_MESSAGE)
    private int d;

    @Builder
    public GroupingRequest(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}
