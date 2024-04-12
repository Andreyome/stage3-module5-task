package com.mjc.school.service;

import java.util.List;

public interface BaseService<T, R, K> {
    List<R> readAll(Integer page, Integer limit, String sortBy);

    R readById(K id);

    R create(T createRequest);

    R update(K id, T updateRequest);

    boolean deleteById(K id);
}
