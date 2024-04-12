package com.mjc.school.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public record NewsDtoResponse(Long id,
                              String content,
                              String title,
                              String createDate,
                              String lastUpdateDate,
                              AuthorDtoResponse authorDto,
                              List<TagDtoResponse> tagDtoResponseList,
                              List<CommentDtoResponse> commentDtoResponseList) {
}
