package com.w08e.common.core.domain.domainEvent.publish.interception;

import com.w08e.common.core.domain.domainEvent.publish.DomainEventPublisher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DomainEventHandlingInterceptor implements HandlerInterceptor {

    private final DomainEventPublisher eventPublisher;

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) {

        List<Long> eventIds = ThreadLocalDomainEventIdHolder.allEventIds();
        log.info("本地线程池查询到事件:【{}】", eventIds);
        try {
            eventPublisher.publish(eventIds);
        } finally {
            ThreadLocalDomainEventIdHolder.remove();
        }
    }

}
