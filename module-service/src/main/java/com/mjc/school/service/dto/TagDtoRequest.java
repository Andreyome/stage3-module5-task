package com.mjc.school.service.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record TagDtoRequest(
        @Min(3)
        @Max(15)
        @NotNull
        String name) {
}
