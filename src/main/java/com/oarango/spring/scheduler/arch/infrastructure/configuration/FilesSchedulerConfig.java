package com.oarango.spring.scheduler.arch.infrastructure.configuration;

import com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs.FtpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class FilesSchedulerConfig {
    @Bean
    public CronTrigger cronTrigger() {
        return new CronTrigger("*/1 * * * * *"); // Cada 1 minuto
    }

    @Bean
    public FtpClient ftpClient(@Value("${ftp.host}") String host,
                               @Value("${ftp.port}") int port,
                               @Value("${ftp.user}") String username,
                               @Value("${ftp.password}") String password,
                               @Value("${ftp.remotePath}") String remotePath) {
        return new FtpClient(host, username, password, port, remotePath);
    }
}
