package com.w08e.common.core.domain.domainEvent;

import lombok.Getter;

/**
 * @author 梦想成为超人的猪猪侠
 */

@Getter
public enum DomainEventStatus {
    CREATED,
    PUBLISH_SUCCEED,
    PUBLISH_FAILED,
    CONSUME_SUCCEED,
    CONSUME_FAILED,
}
