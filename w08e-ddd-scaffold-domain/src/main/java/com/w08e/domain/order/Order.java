package com.w08e.domain.order;

import com.w08e.common.core.domain.AggregateRoot;
import com.w08e.domain.goods.Goods;
import com.w08e.domain.user.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author w08e
 * @date 2023/8/1
 */
@Data
public class Order extends AggregateRoot {

    private BigDecimal price;
    private OrderStatusEnums payStatus;
    private Integer quantity;
    private User user;
    private List<Goods> goodsList;

    public Order(Integer quantity, User user, List<Goods> goodsList) {
        this.quantity = quantity;
        this.user = user;
        this.goodsList = goodsList;
    }

    public Order() {
    }

    public void applyPaid() {
        this.payStatus = OrderStatusEnums.UNPAID;
    }
}
