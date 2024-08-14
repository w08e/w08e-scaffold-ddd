package com.w08e.application;

import com.w08e.common.core.domain.domainEvent.DomainEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * @author: w08e
 */

@SpringBootApplication(scanBasePackages = {"com.w08e"})
@EnableJpaRepositories(basePackages = "com.w08e.infrastructure.repository")
@EntityScan(basePackages = "com.w08e.infrastructure.db.model", basePackageClasses = DomainEvent.class)
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class, args);
    }
}