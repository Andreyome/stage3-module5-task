package com.mjc.school.repository;

import com.mjc.school.repository.model.CommentModel;

import java.util.List;

public interface CommentsRepository extends BaseRepository<CommentModel,Long> {
    List<CommentModel> readByNewsId(Long id);
}
