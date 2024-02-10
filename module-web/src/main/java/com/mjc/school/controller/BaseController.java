package com.mjc.school.controller;

import java.util.List;

public interface BaseController<T, R, K> {

    List<R> readAll(Integer page, Integer limit, String sortBy);

    R readById(K id);

    R create( T createRequest);

    R update(K Id,T updateRequest);

    void deleteById(K id);
}
