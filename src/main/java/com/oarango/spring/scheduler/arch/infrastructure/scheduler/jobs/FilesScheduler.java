package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

import com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs.utils.CustomFileFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class FilesScheduler {
    private final ThreadPoolTaskScheduler taskScheduler;
    private final CronTrigger cronTrigger;
    private final FtpClient ftpClient;

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {
        taskScheduler.schedule(new pdfRunnerTask(ftpClient, new CustomFileFilter("pdf")), cronTrigger);
    }
}
