package com.w08e.common.core.domain.domainEvent.publish.interception;



import com.w08e.common.core.domain.domainEvent.DomainEvent;

import java.util.LinkedList;
import java.util.List;

import static cn.hutool.core.collection.CollUtil.isNotEmpty;
import static java.lang.ThreadLocal.withInitial;

public class ThreadLocalDomainEventIdHolder {
    private static final ThreadLocal<LinkedList<Long>> THREAD_LOCAL_EVENT_IDS = withInitial(LinkedList::new);

    public static void clear() {
        eventIds().clear();
    }

    public static void remove() {
        THREAD_LOCAL_EVENT_IDS.remove();
    }

    public static List<Long> allEventIds() {
        List<Long> eventIds = eventIds();
        return isNotEmpty(eventIds) ? List.copyOf(eventIds) : List.of();
    }

    public static void addEvents(List<DomainEvent> events) {
        events.forEach(ThreadLocalDomainEventIdHolder::addEvent);
    }

    public static void addEvent(DomainEvent event) {
        LinkedList<Long> eventIds = eventIds();
        eventIds.add(event.getId());
    }

    private static LinkedList<Long> eventIds() {
        return THREAD_LOCAL_EVENT_IDS.get();
    }

}
