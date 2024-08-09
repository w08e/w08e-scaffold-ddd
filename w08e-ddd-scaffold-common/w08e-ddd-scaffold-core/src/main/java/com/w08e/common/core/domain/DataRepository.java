package com.w08e.common.core.domain;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/7/23 16:54
 */
public interface DataRepository<ID, D extends AggregateRoot> {

    /**
     * 保存更新领域对象
     * @param domainObject 领域
     */
    void save(D domainObject);

    /**
     * load
     * @param id id
     * @return 领域对象
     */
    D load(ID id);

    void delete(ID id);

}
