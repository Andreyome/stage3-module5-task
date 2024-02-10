package com.mjc.school.controller;

import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.List;

public interface RestTagsController extends BaseController<TagDtoRequest, TagDtoResponse,Long> {
    List<TagDtoResponse> getTagsByNewsId(Long id);
}
