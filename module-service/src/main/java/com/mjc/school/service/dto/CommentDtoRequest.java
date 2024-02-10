package com.mjc.school.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record CommentDtoRequest(@Min(1) @Max(Long.MAX_VALUE) Long id,
                                @Min(5)
                                @Max(255)
                                @NotNull
                                String content
) {
}
