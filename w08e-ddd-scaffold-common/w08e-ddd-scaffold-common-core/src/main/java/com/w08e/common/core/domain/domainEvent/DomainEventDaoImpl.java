package com.w08e.common.core.domain.domainEvent;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DomainEventDaoImpl implements DomainEventDao {

    @Override
    public void insert(List<DomainEvent> events) {

    }

    @Override
    public DomainEvent byId(String id) {
        return null;
    }

    @Override
    public List<DomainEvent> byIds(List<String> ids) {
        return List.of();
    }

    @Override
    public <T extends DomainEvent> T latestEventFor(String arId, DomainEventType type, Class<T> eventClass) {
        return null;
    }

    @Override
    public void successPublish(DomainEvent event) {

    }

    @Override
    public void failPublish(DomainEvent event) {

    }

    @Override
    public void successConsume(DomainEvent event) {

    }

    @Override
    public void failConsume(DomainEvent event) {

    }

    @Override
    public List<DomainEvent> tobePublishedEvents(String startId, int limit) {
        return List.of();
    }
}
