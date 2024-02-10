package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.TagRepInterface;
import com.mjc.school.repository.model.impl.CommentModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class TagRepository implements TagRepInterface {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    public TagRepository(){}
    @Override
    public List<TagModel> readAll(Integer page, Integer limit, String sortBy) {
        String request = "select a from TagModel a";
        if(sortBy!=null){
            request +=" order by " +sortBy;
        }
        return entityManager.createQuery(request, TagModel.class).setFirstResult(page-1).setMaxResults((page-1)*limit).getResultList();
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

    @Override
    public List<TagModel> readByNewsId(Long id) {
        return entityManager.createQuery("SELECT t FROM TagModel t INNER JOIN t.newsModelList n WHERE n.id=:id", TagModel.class).setParameter("id",id).getResultList();
    }
}
