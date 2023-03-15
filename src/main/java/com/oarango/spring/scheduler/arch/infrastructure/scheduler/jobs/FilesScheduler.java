package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

import com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs.utils.CustomFileFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class FilesScheduler {
    private final ThreadPoolTaskScheduler taskScheduler;
    private final PeriodicTrigger periodicTrigger;

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {
        taskScheduler.schedule(new PdfRunnerTask(new CustomFileFilter("pdf")), periodicTrigger);
        taskScheduler.schedule(new CsvRunnerTask(new CustomFileFilter("csv")), periodicTrigger);
    }
}
