package com.w08e.common.core.domain.domainEvent.publish;

import java.util.List;

public interface DomainEventPublisher {
    void publish(List<String> eventIds);
}
