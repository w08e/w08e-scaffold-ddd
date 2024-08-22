package com.w08e.common.core.domain.domainEvent;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainEventDao extends JpaRepository<DomainEvent, Long> {


//    <T extends DomainEvent> T latestEventFor(String arId, DomainEventType type, Class<T> eventClass);
//
//    void successPublish(DomainEvent event);
//
//    void failPublish(DomainEvent event);
//
//    void successConsume(DomainEvent event);
//
//    void failConsume(DomainEvent event);
//
//    List<DomainEvent> tobePublishedEvents(String startId, int limit);
}
