package com.mjc.school.service;


import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.List;

public interface NewsServInterface extends BaseService<NewsDtoRequest, NewsDtoResponse,Long> {
    List<NewsDtoResponse> readNewsByParams(List<Long> tagsIds, List<String> tagsNames, String authorName, String title, String content);

}
