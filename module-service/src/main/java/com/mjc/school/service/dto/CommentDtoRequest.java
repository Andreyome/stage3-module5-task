package com.mjc.school.service.dto;

public record CommentDtoRequest(
        String content,
        Long newsId
) {
}
