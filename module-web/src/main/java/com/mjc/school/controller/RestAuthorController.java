package com.mjc.school.controller;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public interface RestAuthorController extends BaseController<AuthorDtoRequest, AuthorDtoResponse,Long> {
    AuthorDtoResponse getAuthorByNewsId(Long id);
}
