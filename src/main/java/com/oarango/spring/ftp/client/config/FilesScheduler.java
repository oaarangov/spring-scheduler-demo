package com.oarango.spring.ftp.client.config;

import com.oarango.spring.ftp.client.jobs.FilesRunnerTask;
import com.oarango.spring.ftp.client.model.FilesConstants;
import com.oarango.spring.ftp.client.model.SftpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class FilesScheduler {
    private final ThreadPoolTaskScheduler taskScheduler;
    private final CronTrigger cronTrigger;
    private final SftpClient sftpClient;

    @PostConstruct
    public void scheduleRunnableWithCronTrigger() {
        taskScheduler.schedule(new FilesRunnerTask(sftpClient, FilesConstants.PDF_FILE_EXTENSION), cronTrigger);
    }
}
