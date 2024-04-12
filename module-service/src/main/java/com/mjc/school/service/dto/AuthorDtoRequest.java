package com.mjc.school.service.dto;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Valid
public record AuthorDtoRequest(
        @Min(value = 3,message = "TOO LOW")
        @Max(value = 15,message = "TOO LONG")
        @NotNull(message = "should not be NULL")
        String name
) {
}