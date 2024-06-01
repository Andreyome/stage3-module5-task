package com.mjc.school.service.dto;

public record CommentDtoRequest(
//        @Min(5)
//        @Max(255)
//        @NotNull
        String content,
        Long newsId
) {
}
