package com.oarango.spring.ftp.client.jobs;

import com.oarango.spring.ftp.client.connector.Connection;
import com.oarango.spring.ftp.client.model.AckMessage;
import com.oarango.spring.ftp.client.model.FilesConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FilesRunnerTask implements Runnable {
    private Connection connection;

    public FilesRunnerTask(Connection connection, String fileFilter) {
        this.connection = connection;
        this.connection.setup(fileFilter);
    }

    @Override
    public void run() {
        System.out.println("Sftp Files scheduler...");
        String path = FilesConstants.REMOTE_FILES_PATH.concat("pdf");
        connection.open();
        connection.download(path);
        List<byte[]> records = readFiles(FilesConstants.LOCAL_FILES_PATH);
        // Usecase process
        // Prepare ack file
        AckMessage ackMessage = new AckMessage();
        Path ackPath = writeFile(ackMessage);
        connection.upload(ackPath.toAbsolutePath().toString(), FilesConstants.REMOTE_FILES_PATH.concat(ackPath.toAbsolutePath().toString()));
        connection.close();
    }

    private List<byte[]> readFiles(String path) {
        // Read and transform to byte[]
        return new ArrayList<>();
    }

    private Path writeFile(AckMessage ackMessage) {
        try {
            return Files.write(Path.of(FilesConstants.LOCAL_FILES_PATH.concat(ackMessage.getFileName())), ackMessage.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
