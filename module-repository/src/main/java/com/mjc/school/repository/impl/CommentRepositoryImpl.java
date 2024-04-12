package com.mjc.school.repository.impl;

import com.mjc.school.repository.CommentsRepository;
import com.mjc.school.repository.model.CommentModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Repository("CommentRepository")
public class CommentRepositoryImpl extends AbstractDBRepository<CommentModel, Long> implements CommentsRepository {
    @Override
    void updateExistingModel(CommentModel existingEntity, CommentModel updatedEntity) {
        existingEntity.setContent(updatedEntity.getContent());
    }
@Override
    public List<CommentModel> readByNewsId(Long id) {
        try {
            return entityManager.createQuery("SELECT c FROM CommentModel c INNER JOIN c.newsModel n WHERE n.id =:id", CommentModel.class).setParameter("id", id).getResultList();
        } catch (Exception e) {
            throw new EntityNotFoundException("No such news with provided ID");
        }
    }
}
