package com.oarango.spring.ftp.client.config;

import com.oarango.spring.ftp.client.model.FtpClient;
import com.oarango.spring.ftp.client.model.SftpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
public class FtpClientConfig {
    @Value("${ftp.server}")
    private String server;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String user;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.ssh.key}")
    private String sshKey;

    @Bean
    public CronTrigger cronTriggerBean(){
        return new CronTrigger("*/1 * * * * *"); // Cada 1 minuto
    }

    @Bean
    public SftpClient sftpClientBean() {
        return new SftpClient(server, port, user, password, sshKey);
    }
}
