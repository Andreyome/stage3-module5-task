package com.mjc.school.repository.impl;

import com.mjc.school.repository.AuthorRepository;
import com.mjc.school.repository.model.AuthorModel;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("AuthorRepository")
public class AuthorRepositoryImpl extends AbstractDBRepository<AuthorModel, Long> implements AuthorRepository {
    @Override
    void updateExistingModel(AuthorModel existingEntity, AuthorModel updatedEntity) {
        existingEntity.setName(updatedEntity.getName());
    }
@Override
    public Optional<AuthorModel> getAuthorByNewsId(Long id) {
        try {
            return Optional.of(entityManager.createQuery("Select a FROM AuthorModel a INNER JOIN a.newsModelList n WHERE n.id =:id", AuthorModel.class).setParameter("id", id).getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
    public Optional<AuthorModel> readByName(String name){
        TypedQuery<AuthorModel> tq = entityManager.createQuery(
                "SELECT t FROM AuthorModel t WHERE t.name=:name",AuthorModel.class);
        tq.setParameter("name", name);
        try{
            return Optional.of(tq.getSingleResult());
        }
        catch (NoResultException e){
            return Optional.empty();
        }
    }
}
