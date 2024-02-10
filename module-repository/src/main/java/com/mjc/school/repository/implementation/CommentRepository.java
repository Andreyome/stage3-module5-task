package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.CommentsRepInterface;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepository implements CommentsRepInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CommentRepository() {
    }


    @Override
    public List<CommentModel> readAll(Integer page, Integer limit, String sortBy) {
        String request = "select a from CommentModel a";
        if(sortBy!=null){
            request +=" order by " +sortBy;
        }
        return entityManager.createQuery(request, CommentModel.class).setFirstResult(page-1).setMaxResults((page-1)*limit).getResultList();
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

    @Override
    public List<CommentModel> readByNewsId(Long id) {
        try{
            return entityManager.createQuery("SELECT c FROM CommentModel c INNER JOIN c.newsModel n WHERE n.id =:id", CommentModel.class).setParameter("id",id).getResultList();
        }
        catch(Exception e) {
            throw new EntityNotFoundException("No such news with provided ID");
        }
    }
}
