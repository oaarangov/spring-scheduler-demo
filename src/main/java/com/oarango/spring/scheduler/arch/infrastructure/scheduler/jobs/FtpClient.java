package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileFilter;
import java.util.Properties;

public class FtpClient implements Connection {
    private String remoteHost;
    private String username;
    private String password;
    private int port;
    private String remotePath;
    private FileFilter filter;
    private ChannelSftp channelSftp;

    public FtpClient(String remoteHost, String username, String password, int port, String remotePath) {
        this.remoteHost = remoteHost;
        this.username = username;
        this.password = password;
        this.port = port;
        this.remotePath = remotePath;
    }

    @Override
    public void setup(Object... options) {
        filter = (FileFilter) options[0];
        JSch jsch = new JSch();
        try {
            jsch.setKnownHosts("/Users/john/.ssh/known_hosts"); // Lectura de certificado SSL
        } catch (JSchException e) {
            throw new RuntimeException("Error al leer el certificado", e);
        }
        Session jschSession = null;
        try {
            jschSession = jsch.getSession(username, remoteHost, port);
        } catch (JSchException e) {
            throw new RuntimeException("Error al obtener la sesión", e);
        }
        jschSession.setPassword(password);
        Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no"); // Para evitar llave/certificado
        jschSession.setConfig(config);
        try {
            jschSession.connect();
        } catch (JSchException e) {
            throw new RuntimeException("Error al conectar", e);
        }
        try {
            channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
        } catch (JSchException e) {
            throw new RuntimeException("Error al abrir el canal de comunicación", e);
        }
    }

    @Override
    public void connect() {
        try {
            channelSftp.connect();
        } catch (JSchException e) {
            throw new RuntimeException("Error al conectar al canal", e);
        }
    }

    @Override
    public void read(Object src, Object dst) {
        connect();
        // TODO: 15/03/23 Aplicar filtro para lectura de archivos

        File remoteFile = (File) src;
        File localDir = (File) dst;

        try {
            channelSftp.get(remoteFile.getName(), localDir.getAbsolutePath() + remoteFile.getName());
        } catch (SftpException e) {
            throw new RuntimeException("Error al leer del directorio remoto", e);
        }

        channelSftp.exit();
    }

    @Override
    public void write(Object src, Object dst) {
        connect();

        File localFile = (File) src;
        File remoteDir = (File) dst;

        try {
            channelSftp.put(localFile.getName(), remoteDir.getAbsolutePath() + localFile.getName());
        } catch (SftpException e) {
            throw new RuntimeException("Error al escribir en el directorio remoto", e);
        }
        channelSftp.exit();
    }

    @Override
    public void close() {
        channelSftp.disconnect();
    }

    public void setFilter(FileFilter filter) {
        this.filter = filter;
    }
}