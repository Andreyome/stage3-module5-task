package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements BaseRepository<TagModel,Long> {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public TagRepository(){}
    @Override
    public List<TagModel> readAll() {
        return entityManager.createQuery("SELECT a from TagModel a", TagModel.class).getResultList();
    }

    @Override
    public Optional<TagModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(TagModel.class,id));
    }

    @Override
    public TagModel create(TagModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public TagModel update(TagModel entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            entityManager.remove(entityManager.find(TagModel.class,id));
            return true;
        }
        catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }

}
