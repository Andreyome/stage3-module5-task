package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorsRepository implements BaseRepository<AuthorModel, Long> {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorsRepository() {}

    @Override
    public List<AuthorModel> readAll() {
        return entityManager.createQuery("SELECT a from AuthorModel a", AuthorModel.class).getResultList();
    }

    @Override
    public Optional<AuthorModel> readById(Long id) {
        return Optional.ofNullable(entityManager.find(AuthorModel.class,id));
    }

    @Override
    public AuthorModel create(AuthorModel entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public AuthorModel update(AuthorModel entity) {
        entityManager.merge(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            entityManager.remove(entityManager.find(AuthorModel.class,id));
            return true;
        }
        catch (Exception e) {
            System.out.println("ERROR IN DELETING");
            return false;
        }
    }
    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
