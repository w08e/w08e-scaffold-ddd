package com.w08e.domain.order;

import lombok.Getter;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/7 15:20
 */

@Getter
public enum OrderStatusEnums {

    UNPAID("UNPAID","未支付"),
    PAID("PAID","已支付");

    private String code;
    private String message;

    OrderStatusEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
