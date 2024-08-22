package com.w08e.infrastructure.repository;

import cn.hutool.core.collection.CollectionUtil;
import com.w08e.common.core.domain.AggregateRoot;
import com.w08e.common.core.domain.BaseEntity;
import com.w08e.common.core.domain.domainEvent.DomainEvent;
import com.w08e.common.core.domain.domainEvent.DomainEventDao;
import com.w08e.common.core.domain.domainEvent.DomainEventType;
import com.w08e.common.core.domain.domainEvent.publish.interception.ThreadLocalDomainEventIdHolder;
import com.w08e.common.core.utils.StringUtils;

import com.w08e.json.utils.JsonUtils;
import com.w08e.redis.utils.RedisUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/7/8 16:54
 */
public abstract class BaseRepository<ID, D extends AggregateRoot, E extends BaseEntity> {

    private static final Logger log = LoggerFactory.getLogger(BaseRepository.class);
    @PersistenceContext
    private EntityManager entityManager;
    private final Class<E> entityClass;
    protected CriteriaBuilder criteriaBuilder;

    @Resource
    private DomainEventDao domainEventDao;

    @PostConstruct
    private void init() {
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public BaseRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
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
        // 领域事件持久化redis 这里持久化到数据库更好
        if (CollectionUtil.isNotEmpty(domainObject.getEvents())) {

            List<DomainEvent> domainEvents = new ArrayList<>();
            for (DomainEvent event : domainObject.getEvents()) {
                if (StringUtils.equalsIgnoreCase(event.getType().getCode(), DomainEventType.CREATE.getCode())) {
                    event.setArInfo(domainObject);
                }
                DomainEvent save = domainEventDao.save(event);

                domainEvents.add(save);
            }
            ThreadLocalDomainEventIdHolder.addEvents(domainEvents); //记录事件ID以备后用
        }
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

