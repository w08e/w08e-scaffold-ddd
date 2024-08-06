package com.w08e.infrastructure.db.enums;

import lombok.Getter;

/**
 * @author jinyueWang
 * @date 2023/8/1
 */

@Getter
public enum OrderStatusEnums {
    CREATED("CREATED", "创建"),
    UNPAID("CREATED", "创建"),
    PAID("CREATED", "创建");

    private String code;
    private String desc;

    OrderStatusEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
