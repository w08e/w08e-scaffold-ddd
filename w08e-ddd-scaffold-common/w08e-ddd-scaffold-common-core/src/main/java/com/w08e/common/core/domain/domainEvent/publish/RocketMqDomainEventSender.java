package com.w08e.common.core.domain.domainEvent.publish;

import com.w08e.common.core.domain.domainEvent.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * @Author: 梦想成为超人的猪猪侠
 * @Date: 2024/8/13 16:20
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RocketMqDomainEventSender implements DomainEventSender {

//    private final RocketMQTemplate rocketMQTemplate;
    @Override
    public void send(DomainEvent event) {
//        rocketMQTemplate.asyncSend();
        log.info("---------发送mq消息----------------");
    }
}
