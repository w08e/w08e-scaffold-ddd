package com.w08e.infrastructure.repository;


import com.w08e.common.core.domain.BaseRepository;
import com.w08e.domain.goods.Goods;
import com.w08e.domain.goods.repositoy.GoodsRepository;
import com.w08e.infrastructure.db.model.GoodsEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 15:34
 */

@Repository
@Transactional
public class GoodsJpaRepository extends BaseRepository<Long, Goods, GoodsEntity> implements GoodsRepository {

    public GoodsJpaRepository() {
        super(GoodsEntity.class);
    }

    @Override
    protected void update(GoodsEntity entity, Goods domainObject) {

    }

    @Override
    protected GoodsEntity convertToEntity(Goods domainObject) {
        return new GoodsEntity(null, domainObject.getName(),
                domainObject.getImage(),
                domainObject.getOriginalPrice(),
                domainObject.getDiscountPrice(),
                domainObject.getStock(),
                domainObject.getCategory(),
                domainObject.getDesc());
    }

    @Override
    protected Goods convertToDomain(GoodsEntity entity) {
        return null;
    }


    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<Goods> loadByName(String name) {
        return loadAll(root ->
                criteriaBuilder.equal(root.get("name"), name)
        );
    }
}
