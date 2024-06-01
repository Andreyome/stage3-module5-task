package com.mjc.school.service;

import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public interface AuthorServInterface extends BaseService<AuthorDtoRequest, AuthorDtoResponse,Long> {
    AuthorDtoResponse getAuthorByNewsId(Long id);
}
