package edu.pkch.validation.custom;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomValidRequest {

    @CheckSeoulPhoneNumber
    private String a;

    @Min(10)
    private int b;
    
    private boolean c;

    @Builder
    public CustomValidRequest(String a, int b, boolean c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
