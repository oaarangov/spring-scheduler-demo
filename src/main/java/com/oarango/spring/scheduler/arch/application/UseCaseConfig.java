package com.oarango.spring.scheduler.arch.application;

import com.oarango.spring.scheduler.arch.domain.UseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {
    @Bean
    public UseCase useCaseBean() {
        return new UseCase();
    }
}
