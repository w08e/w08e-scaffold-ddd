package com.w08e.common.core.domain.domainEvent;




/**
 * @author 梦想成为超人的猪猪侠
 */
public interface DomainEventHandler {

    boolean canHandle(DomainEvent domainEvent);

    void handle(DomainEvent domainEvent, TaskRunner taskRunner);

    default int priority() {
        return 0;//越小优先级越高
    }


}
