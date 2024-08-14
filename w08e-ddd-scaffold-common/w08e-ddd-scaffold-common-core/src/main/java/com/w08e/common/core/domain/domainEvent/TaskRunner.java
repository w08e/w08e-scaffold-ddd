package com.w08e.common.core.domain.domainEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskRunner {
    private boolean hasError;

    public static TaskRunner newTaskRunner() {
        return new TaskRunner();
    }

    public void run(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable t) {
            log.error("Failed to run task: ", t);
            hasError = true;
        }
    }

    public boolean isHasError() {
        return hasError;
    }

}
