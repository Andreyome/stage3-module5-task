package com.mjc.school.controller;

import com.mjc.school.service.dto.CommentDtoRequest;
import com.mjc.school.service.dto.CommentDtoResponse;

import java.util.List;

public interface RestCommentController extends BaseController<CommentDtoRequest, CommentDtoResponse,Long> {
    List<CommentDtoResponse> getCommentsByNewsId(Long id);
}
