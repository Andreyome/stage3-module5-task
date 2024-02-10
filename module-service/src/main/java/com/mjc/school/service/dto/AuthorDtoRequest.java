package com.mjc.school.service.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record AuthorDtoRequest(@Min(1) @Max(Long.MAX_VALUE) Long id,
                               @Min(3)
                               @Max(15)
                               @NotNull
                               String name
) {
}