package com.mjc.school.service.dto;


import java.util.List;

public record NewsDtoRequest(
//                             @Size(min = 5, max = 255)
//                             @NotNull
                             String content,
//                             @Min(5)
//                             @Max(30)
//                             @NotNull
                             String title,
                             String authorName,
                             List<String> tagNames
) {
}
