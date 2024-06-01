package com.mjc.school.service.dto;


//@Valid
public record AuthorDtoRequest(
//        @Min(value = 3,message = "TOO LOW")
//        @Max(value = 15,message = "TOO LONG")
//        @NotNull(message = "should not be NULL")
        String name
) {
}