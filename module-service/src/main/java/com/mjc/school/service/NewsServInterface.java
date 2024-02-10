package com.mjc.school.service;


import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

import java.util.List;
import java.util.Optional;

public interface NewsServInterface extends BaseService<NewsDtoRequest, NewsDtoResponse,Long> {
    List<NewsDtoResponse> readNewsByParams(Optional<List<Long>> tagsIds, Optional<List<String>>tagsNames, Optional<String> authorName, Optional<String> title, Optional<String> content);

}
