package com.w08e.api.service;

import cn.hutool.core.lang.Pair;
import com.w08e.api.command.GoodsCommand;
import com.w08e.domain.goods.Goods;
import com.w08e.domain.goods.factory.GoodsFactory;
import com.w08e.domain.goods.repositoy.GoodsRepository;
import com.w08e.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 16:09
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    private final GoodsFactory goodsFactory;

    @Transactional
    public Pair create(GoodsCommand goodsCommand) {

        Goods goods = goodsFactory.create(goodsCommand.getName(), goodsCommand.getOriginalPrice(), goodsCommand.getStock(), goodsCommand.getCategory(), new User("hello"));

        goodsRepository.save(goods);

        log.info("goods created: [{}]", goods.toString());

        return Pair.of(goods.getId(), goods.getName());
    }
}
