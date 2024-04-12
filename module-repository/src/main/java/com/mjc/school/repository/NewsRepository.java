package com.mjc.school.repository;

import com.mjc.school.repository.model.NewsModel;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends BaseRepository<NewsModel,Long> {
    List<NewsModel> readNewsByParams(List<Long> tagsIds, List<String> tagsNames, String authorName, String title, String content);
}
