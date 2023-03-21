package com.oarango.spring.ftp.client.model;

import com.jcraft.jsch.*;
import com.oarango.spring.ftp.client.connector.Connection;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

public class SftpClient implements Connection {

    private String server;
    private int port;
    private String user;
    private String password;
    private String sshKey;
    private String fileFilter;
    private ChannelSftp channelSftp;

    public SftpClient(String server, int port, String user, String password, String sshKey) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
        this.sshKey = sshKey;
    }

    @Override
    public void setup(Object... args) {
        fileFilter = (String) args[0];
        if (!fileFilter.startsWith("*")) {
            fileFilter = "*".concat(fileFilter);
        }
    }

    @Override
    public void open() {
        byte[] key = Base64.getDecoder().decode(sshKey); // Java 8 Base64 - or any other
        JSch jsch = new JSch();
        try {
            HostKey hostKey = new HostKey(server, key);
            jsch.getHostKeyRepository().add(hostKey, null);
            Session jschSession = jsch.getSession(user, server);
            jschSession.setPassword(password);
            jschSession.connect();
            channelSftp = (ChannelSftp) jschSession.openChannel("sftp");
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    ChannelSftp.LsEntry entry;

    @Override
    public Object list(String path) {
        if (!path.endsWith("/")) {
            path = path.concat("/");
        }
        connect();
        Vector lsEntries;
        try {
            lsEntries = channelSftp.ls(path.concat(fileFilter));
            System.out.println(lsEntries);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
        channelSftp.exit();
        return lsEntries;
    }

    @Override
    public void download(String path) {
        Vector<ChannelSftp.LsEntry> lsEntries = (Vector<ChannelSftp.LsEntry>) list(path);
        for (ChannelSftp.LsEntry entry : lsEntries) {
            String dst = FilesConstants.LOCAL_FILES_PATH.concat(File.separator).concat(entry.getFilename());
            System.out.println(dst);
            try {
                channelSftp.get(entry.getFilename(), dst);
            } catch (SftpException sftpException) {
                System.out.println("Failed to download the file " + entry.getFilename() + " the sftp folder location.");
            }
        }
    }

    @Override
    public void upload(Object src, Object dst) {
        connect();
        String localFile = (String) src;
        String remoteDir = (String) dst;

        try {
            channelSftp.put(localFile, remoteDir);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
        channelSftp.exit();
    }

    @Override
    public void close() {
        channelSftp.disconnect();
    }

    private void connect() {
        try {
            channelSftp.connect();
            System.out.println("Connected");
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }
}