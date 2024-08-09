package com.w08e.common.core.domain;

import com.w08e.common.core.domain.domianEvent.DomainEvent;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 梦想成为超人的猪猪侠
 */
@Data
public abstract class AggregateRoot implements Identified, MarkerInterface {

    private static final int MAX_OPS_LOG_SIZE = 20;

    private Long id;
    // 创建时间
    private OffsetDateTime createdAt;
    // 创建人姓名id
    private Long creator;
    // 更新时间
    private OffsetDateTime updatedAt;
    // 更新人姓名id
    private Long updater;
    // 领域事件列表，用于临时存放完成某个业务流程中所发出的事件，会被BaseRepository保存到事件表中
    private List<DomainEvent> events;

    protected AggregateRoot() {
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }


    protected AggregateRoot(Long userId) {
        Objects.requireNonNull(userId, "USER_ID must not be blank.");
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
        this.updater = userId;
        this.creator = userId;
    }

    protected void raiseEvent(DomainEvent event) {
//        event.setArInfo(this);
        allEvents().add(event);
    }

    public void clearEvents() {
        this.events = null;
    }

    private List<DomainEvent> allEvents() {
        if (events == null) {
            this.events = new ArrayList<>();
        }

        return events;
    }

    @Override
    public Long getIdentifier() {
        return id;
    }
}
