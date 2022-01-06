package edu.pkch.validation;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class PostRequest {
    @NotNull
    private Long memberNumber;

    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @NotNull
    private String contents;

    @Builder
    public PostRequest(Long memberNumber, String title, String contents) {
        this.memberNumber = memberNumber;
        this.title = title;
        this.contents = contents;
    }
}
