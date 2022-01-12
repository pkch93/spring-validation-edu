package edu.pkch.validation.grouping;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupingRequest {
    @Min(value = 5, groups = GroupA.class)
    private int a;

    @Min(10)
    private int b;

    private int c;

    public GroupingRequest(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
