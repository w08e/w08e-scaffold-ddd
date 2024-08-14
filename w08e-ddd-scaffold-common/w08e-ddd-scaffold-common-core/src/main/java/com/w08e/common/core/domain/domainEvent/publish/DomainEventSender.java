package com.w08e.common.core.domain.domainEvent.publish;


import com.w08e.common.core.domain.domainEvent.DomainEvent;

public interface DomainEventSender {
    public void send(DomainEvent event);

}
