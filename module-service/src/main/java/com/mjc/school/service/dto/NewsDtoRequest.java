package com.mjc.school.service.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public record NewsDtoRequest(
                             @Size(min = 5, max = 255)
                             @NotNull
                             String content,
                             @Min(5)
                             @Max(30)
                             @NotNull
                             String title,
                             String authorName,
                             List<String> tagNames
) {
}
