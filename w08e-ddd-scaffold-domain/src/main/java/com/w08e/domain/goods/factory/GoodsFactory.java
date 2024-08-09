package com.w08e.domain.goods.factory;

import cn.hutool.core.collection.CollectionUtil;
import com.w08e.common.core.exception.ServiceException;
import com.w08e.domain.goods.Goods;
import com.w08e.domain.goods.repositoy.GoodsRepository;
import com.w08e.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 15:33
 */
@Component
@RequiredArgsConstructor
public class GoodsFactory {

    private final GoodsRepository goodsRepository;
    public Goods create(String name,
                        BigDecimal originalPrice,
                        Integer stock,
                        String category,
                        User user) {

        if(CollectionUtil.isNotEmpty(goodsRepository.loadByName(name)))throw new ServiceException("xxx已存在了");

        // 类目校验
        // 库存校验

        return new Goods(name, originalPrice, stock, category, user);

    }
}
