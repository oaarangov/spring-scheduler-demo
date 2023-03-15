package com.oarango.spring.scheduler.arch.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
public class CronConfig {
    @Bean
    public PeriodicTrigger periodicTrigger() {
        return new PeriodicTrigger(1000, TimeUnit.MILLISECONDS);
    }
}
