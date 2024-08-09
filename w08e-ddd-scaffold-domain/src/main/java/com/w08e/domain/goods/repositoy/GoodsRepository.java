package com.w08e.domain.goods.repositoy;

import com.w08e.common.core.domain.DataRepository;
import com.w08e.domain.goods.Goods;

import java.util.List;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 15:33
 */
public interface GoodsRepository extends DataRepository<Long, Goods> {
    List<Goods> loadByName(String name);
}
