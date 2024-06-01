package com.mjc.school.controller;

import org.springframework.hateoas.EntityModel;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll(Integer page, Integer limit, String sortBy);

    EntityModel<R> readById(K id);

    EntityModel<R> create(T createRequest);

    EntityModel<R> update(K Id, T updateRequest);

    void deleteById(K id);
}
