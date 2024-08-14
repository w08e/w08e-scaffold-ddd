package com.w08e.domain.goods.event;


import com.w08e.common.core.domain.domainEvent.DomainEvent;
import com.w08e.common.core.domain.domainEvent.DomainEventType;
import com.w08e.common.core.domain.user.User;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/14 09:52
 */
public class GoodsAddEvent extends DomainEvent {

    private Long goodsId;

    public GoodsAddEvent(Long goodsId) {
        super(DomainEventType.CREATE, new User("张三"));
        this.goodsId = goodsId;
    }

}
