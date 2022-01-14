package edu.pkch.validation.custom;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@CheckValidation
public class CustomObjectValidRequest {

    private String a;

    private int b;

    @Builder
    public CustomObjectValidRequest(String a, int b) {
        this.a = a;
        this.b = b;
    }
}
