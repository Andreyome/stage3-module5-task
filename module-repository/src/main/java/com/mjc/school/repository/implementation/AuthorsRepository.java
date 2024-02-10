package com.mjc.school.repository.implementation;

import com.mjc.school.repository.AuthorRepInterface;
import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorsRepository implements AuthorRepInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public AuthorsRepository() {}

    @Override
    public List<AuthorModel> readAll(Integer page, Integer limit, String sortBy) {
        String request = "SELECT a from AuthorModel a";
        if(sortBy!=null){
            request +=" order by " +sortBy;
        }
        return         entityManager.createQuery(request, AuthorModel.class).setFirstResult(page-1).setMaxResults((page-1)*limit).getResultList();
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
    public Optional<AuthorModel> getAuthorByNewsId(Long id){
        try{
            return Optional.of(entityManager.createQuery("Select a FROM AuthorModel a INNER JOIN a.newsModelList n WHERE n.id =:id",AuthorModel.class).setParameter("id",id).getSingleResult());
        }
        catch (Exception e){
            return Optional.empty();
        }
    }
    @Override
    public boolean existById(Long id) {
        return readById(id).isPresent();
    }
}
