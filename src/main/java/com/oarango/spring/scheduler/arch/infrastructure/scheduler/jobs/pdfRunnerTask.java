package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

import java.io.FileFilter;

public class pdfRunnerTask implements Runnable {
    private final Connection connection;

    public pdfRunnerTask(Connection connection, FileFilter filter) {
        this.connection = connection;
        setup(filter);
    }

    @Override
    public void run() {
        System.out.println("Runnable Task with on thread " + Thread.currentThread().getName());
        read();
    }

    void setup(Object... options) {
        connection.setup(options);
    }

    void connect() {
        connection.connect();
    }

    void read() {
        connection.read(null, null);
    }

    void write() {
        connection.write(null, null);
    }

    void close() {
        connection.close();
    }
}
