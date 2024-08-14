package com.w08e.common.core.domain.domainEvent.publish;

import cn.hutool.core.collection.CollectionUtil;
import com.w08e.common.core.domain.domainEvent.DomainEvent;
import com.w08e.common.core.domain.domainEvent.DomainEventDao;
import jakarta.annotation.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/12 17:34
 */

@Component
public class AsynchronousDomainEventPublisher implements DomainEventPublisher {

    @Resource
    private DomainEventDao domainEventDao;
    @Resource
    private DomainEventSender domainEventSender;
    @Resource
    private TaskExecutor taskExecutor;

    @Override
    public void publish(List<String> eventIds) {
        if (CollectionUtil.isNotEmpty(eventIds)) {
            taskExecutor.execute(() -> {
                // 根据事件ID，从事件发布表中加载相应事件
                List<DomainEvent> domainEvents = domainEventDao.byIds(eventIds);
                // 发布事件
                domainEvents.forEach(domainEventSender::send);
            });
        }
    }
}
