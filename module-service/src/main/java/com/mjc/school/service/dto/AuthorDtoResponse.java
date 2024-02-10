package com.mjc.school.service.dto;

import java.time.LocalDateTime;

public record AuthorDtoResponse(Long id,
                                String name,
                                LocalDateTime createDate,
                                LocalDateTime lastUpdateDate) {
}
