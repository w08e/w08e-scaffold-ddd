package com.w08e.common.core.domain.domainEvent;

import com.w08e.common.core.domain.AggregateRoot;
import com.w08e.common.core.domain.user.User;
import com.w08e.common.core.gererator.SnowflakeIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Objects;

import static java.time.OffsetDateTime.now;


/**
 * @author 梦想成为超人的猪猪侠
 */
@Getter
@Table
@Entity(name = "ddd_domain_event")
@NoArgsConstructor
public  class DomainEvent {

    // 事件ID，不能为空
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflake_id_generator")
    private Long id;

    // 事件标识id不为空唯一
    @Column(name = "flag_id")
    private String flagId;
    // 事件对应的聚合根ID，不能为空
    @Column(name = "domain_id", unique = true)
    private Long domainId;
    // 事件类型
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DomainEventType type;
    // 状态
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DomainEventStatus status;
    // 已经发布的次数，无论成功与否
    @Column(name = "published_count")
    private int publishedCount;
    // 已经被消费的次数，无论成功与否
    @Column(name = "consumed_count")
    private int consumedCount;
    // 引发该事件的memberId
    @Column(name = "raised_by")
    private String raisedBy;
    // 事件产生时间
    @Column(name = "raised_at")
    private OffsetDateTime raisedAt;

    /**
     * 这里把user作为必要参数 看具体业务
     */
    public DomainEvent(DomainEventType type, User user) {

//        this.id = newEventId();
        this.type = type;
        this.status = DomainEventStatus.CREATED;
        this.publishedCount = 0;
        this.consumedCount = 0;
        this.raisedBy = user.getName();
        this.raisedAt = now();
    }

    public String newEventId() {
        return "EVENT" + SnowflakeIdGenerator.newSnowflakeId();
    }

    public void setArInfo(AggregateRoot ar) {
        this.domainId = ar.getId();
    }

    public boolean isConsumedBefore() {
        return this.consumedCount > 0;
    }

    public boolean isNotConsumedBefore() {
        return !isConsumedBefore();
    }

    public boolean isRaisedByHuman() {
        return Objects.nonNull(raisedBy);
    }


}
