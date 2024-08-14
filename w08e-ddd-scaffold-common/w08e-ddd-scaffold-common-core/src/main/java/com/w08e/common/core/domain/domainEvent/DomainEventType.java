package com.w08e.common.core.domain.domainEvent;

import lombok.Getter;

/**
 * @author 梦想成为超人的猪猪侠
 */

@Getter
public enum DomainEventType {
    CREATE("CREATE","创建"),
    UPDATE("UPDATE","更新"),
    OTHER("OTHER","其他");

    private String code;
    private String msg;

    DomainEventType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
