package com.mjc.school.repository;

import com.mjc.school.repository.model.impl.NewsModel;

import java.util.List;
import java.util.Optional;

public interface NewsRepInterface extends BaseRepository<NewsModel,Long> {
    List<NewsModel> readNewsByParams(Optional<List<Long>> tagsIds, Optional<List<String>>tagsNames, Optional<String> authorName, Optional<String> title, Optional<String> content);
}
