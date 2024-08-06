package com.w08e.common.core.domain;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/7/23 16:54
 */
public interface DataRepository<ID, D extends AggregateRoot, E extends BaseEntity> {

    void save(D domainObject);

    D load(ID id);

    void delete(ID id);

}
