package com.mjc.school.service;

import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;

import java.util.List;

public interface CommentServInterface extends BaseService<CommentDtoRequest, CommentDtoResponse,Long> {
    List<CommentDtoResponse> readByNewsId(Long id);
}
