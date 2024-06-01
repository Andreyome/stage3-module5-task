package com.mjc.school.repository.impl;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDBRepository<T extends BaseEntity<K>, K> implements BaseRepository<T, K> {
    @PersistenceContext
    protected EntityManager entityManager;
    private final Class<T> entityClass;

    abstract void updateExistingModel(T existingEntity, T updatedEntity);

    public AbstractDBRepository() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public List<T> readAll(Integer page, Integer pageSize, String sortBy) {
        int count = (page - 1) * pageSize;
        String[] sortBySpecification = sortBy.split(":");
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        CriteriaQuery<T> cq = criteriaQuery.select(root);
        CriteriaQuery<T> sorted;
        if (sortBySpecification[1].equalsIgnoreCase("ASC")) {
            sorted = cq.orderBy(criteriaBuilder.asc(root.get(sortBySpecification[0])));
        } else {
            sorted = cq.orderBy(criteriaBuilder.desc(root.get(sortBySpecification[0])));
        }
        TypedQuery<T> typedQuery = entityManager.createQuery(sorted);
        typedQuery.setMaxResults(pageSize);
        typedQuery.setFirstResult(count);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<T> readById(K id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public T update(K id, T entity) {
        T existingModel = entityManager.find(entityClass, id);
        updateExistingModel(existingModel, entity);
        entityManager.flush();
        return existingModel;
    }

    @Override
    public boolean deleteById(K id) {
        T existingEntity = (entityManager.find(entityClass, id));
        if (existingEntity == null) {
            return false;
        }
        entityManager.remove(existingEntity);
        entityManager.flush();
        return true;
    }

    @Override
    public boolean existById(K id) {
        return entityManager.find(entityClass, id) != null;
    }
}
