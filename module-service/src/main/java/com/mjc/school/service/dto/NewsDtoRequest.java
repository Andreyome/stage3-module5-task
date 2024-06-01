package com.mjc.school.service.dto;


import java.util.List;

public record NewsDtoRequest(
        String content,

        String title,
        String authorName,
        List<String> tagNames
) {
}
