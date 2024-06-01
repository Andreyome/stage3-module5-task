package com.mjc.school.service.dto;


public record CommentDtoResponse(Long id,
                                 String content,
                                 String createDate,
                                 String lastUpdateDate,
                                 Long newsId
) {
}
