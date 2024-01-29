package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository implements BaseRepository<CommentModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CommentRepository() {
    }


    @Override
    public List<CommentModel> readAll() {
        return entityManager.createQuery("select a from CommentModel a", CommentModel.class).getResultList();
    }

    @Override
    public Optional<CommentModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(CommentModel.class, id));
    }

    @Override
    public CommentModel create(CommentModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public CommentModel update(CommentModel entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            entityManager.remove(entityManager.find(CommentModel.class, id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
