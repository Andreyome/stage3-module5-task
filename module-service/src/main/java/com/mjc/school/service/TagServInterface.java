package com.mjc.school.service;

import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.List;

public interface TagServInterface extends BaseService<TagDtoRequest, TagDtoResponse, Long> {
    List<TagDtoResponse> readByNewsId(Long id);
}
