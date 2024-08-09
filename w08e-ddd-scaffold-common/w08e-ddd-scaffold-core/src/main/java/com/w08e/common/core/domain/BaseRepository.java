package com.w08e.common.core.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/7/8 16:54
 */
public abstract class BaseRepository<ID, D extends AggregateRoot, E extends BaseEntity> {

    private EntityManager entityManager;
    private final Class<E> entityClass;
    protected CriteriaBuilder criteriaBuilder;

    public BaseRepository(Class<E> entityClass, EntityManager entityManager) {
        this.entityClass = entityClass;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    /**
     * 通用保存逻辑 同步保存事件
     *
     * @param domainObject 领域
     */
    public void save(D domainObject) {
        if (Objects.isNull(domainObject.getId())) {
            E entity = convertToEntity(domainObject);
            E merge = entityManager.merge(entity);
            domainObject.setId((Long) merge.getId());
        } else {
            E entity = entityManager.find(entityClass, domainObject.getId());
            if (entity != null) {
                entity.setModifyTime(OffsetDateTime.now());
                update(entity, domainObject);
                entityManager.merge(entity);
            }
        }
//        // todo 领域事件持久化redis
//        if (CollectionUtil.isNotEmpty(domainObject.getEvents())) {
//            domainObject.getEvents().forEach(event -> {
//                RedisUtils.setCacheObject(entityClass.toString(), event);
//            });
//        }
    }

    public D load(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id)).map(this::convertToDomain).orElse(null);
    }


    /**
     * 根据Predicate生成器函数查询数据。
     *
     * @param predicateGenerator 断言生成器函数
     * @return 符合条件的数据列表
     */
    public List<D> loadAll(PredicateGenerator<E> predicateGenerator) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<E> root = criteriaQuery.from(entityClass);

        // 应用用户提供的Predicate生成器函数
        Predicate predicate = predicateGenerator.generatePredicate(root);
        criteriaQuery.where(predicate);


        List<E> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        return Optional.ofNullable(resultList).map(list -> list.stream().map(this::convertToDomain).toList()).orElseThrow(null);

    }

    /**
     * Predicate生成器函数接口。
     */
    @FunctionalInterface
    public interface PredicateGenerator<E> {
        Predicate generatePredicate(Root<E> root);
    }

    /**
     * 需要更新的字段
     * 只用作set操作
     *
     * @param domainObject 领域
     * @return 实体
     */
    protected abstract void update(E entity, D domainObject);


    /**
     * 领域转实体对象
     *
     * @param domainObject 领域
     * @return 实体
     */
    protected abstract E convertToEntity(D domainObject);

    /**
     * 实体转领域
     *
     * @param entity 实体
     * @return 领域
     */
    protected abstract D convertToDomain(E entity);


}

