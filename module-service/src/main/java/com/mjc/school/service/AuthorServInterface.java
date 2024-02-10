package com.mjc.school.service;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

import java.util.Optional;

public interface AuthorServInterface extends BaseService<AuthorDtoRequest, AuthorDtoResponse,Long> {
    AuthorDtoResponse getAuthorByNewsId(Long id);
}
