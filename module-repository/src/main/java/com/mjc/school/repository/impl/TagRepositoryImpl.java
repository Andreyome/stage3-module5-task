package com.mjc.school.repository.impl;

import com.mjc.school.repository.TagRepository;
import com.mjc.school.repository.model.TagModel;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository("TagRepository")
public class TagRepositoryImpl extends AbstractDBRepository<TagModel, Long> implements TagRepository {
    @Override
    public List<TagModel> readByNewsId(Long id) {
        return entityManager.createQuery("SELECT t FROM TagModel t INNER JOIN t.newsModelList n WHERE n.id=:id", TagModel.class).setParameter("id", id).getResultList();
    }

    @Override
    void updateExistingModel(TagModel existingEntity, TagModel updatedEntity) {
        existingEntity.setName(updatedEntity.getName());
    }

    public Optional<TagModel> readTagByName(String name) {
        TypedQuery<TagModel> tq = entityManager.createQuery(
                "SELECT t FROM TagModel t WHERE t.name=:name", TagModel.class);
        tq.setParameter("name", name);
        try {
            return Optional.of(tq.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
