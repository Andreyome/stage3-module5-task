package com.mjc.school.repository.impl;

import com.mjc.school.repository.NewsRepository;
import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.model.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("NewsRepository")
public class NewsRepositoryImpl extends AbstractDBRepository<NewsModel, Long> implements NewsRepository {
    public List<NewsModel> readNewsByParams(List<Long> tagsIds, List<String> tagsNames, String authorName, String title, String content) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsModel> criteriaQuery = criteriaBuilder.createQuery(NewsModel.class);
        Root<NewsModel> root = criteriaQuery.from(NewsModel.class);
        criteriaQuery.select(root);
        Predicate predicate = criteriaBuilder.conjunction();
        if (tagsIds!=null && !tagsIds.isEmpty()) {
            Join<TagModel, NewsModel> nt = root.join("tagModelList");
            predicate= criteriaBuilder.and(predicate,nt.get("id").in(tagsIds));
        }
        if (tagsNames!=null && !tagsNames.isEmpty()) {
            Join<TagModel, NewsModel> nt = root.join("tagModelList");
            predicate= criteriaBuilder.and(predicate,nt.get("name").in(tagsNames));
        }
        if (authorName!=null && !authorName.isBlank()) {
            Join<AuthorModel, NewsModel> na = root.join("authorModel");
            predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(na.get("name"),"%"+authorName+"%"));
        }
        if (title!=null && !title.isBlank()) {
            predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("title"),"%"+title+"%"));
        }
        if (content!=null && !content.isBlank()) {
            predicate=criteriaBuilder.and(predicate,criteriaBuilder.like(root.get("content"),"%"+content+"%"));
        }
        return entityManager.createQuery(criteriaQuery.where(predicate)).getResultList();
    }

    @Override
    void updateExistingModel(NewsModel existingEntity, NewsModel updatedEntity) {
        existingEntity.setContent(updatedEntity.getContent());
        existingEntity.setTitle(updatedEntity.getTitle());
        existingEntity.setAuthorModel(updatedEntity.getAuthorModel());
        existingEntity.setTagModelList(updatedEntity.getTagModelList());
    }
}
