package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class NewsRepository implements BaseRepository<NewsModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public NewsRepository() {

    }

    @Override
    public List<NewsModel> readAll() {
        return entityManager.createQuery("select a from NewsModel a", NewsModel.class).getResultList();
    }

    @Override
    public Optional<NewsModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(NewsModel.class, id));
    }

    @Override
    public NewsModel create(NewsModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public NewsModel update(NewsModel entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            entityManager.remove(entityManager.find(NewsModel.class, id));
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
