package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

import java.io.FileFilter;

public class CsvRunnerTask implements Runnable {
    private final FileFilter filter;

    public CsvRunnerTask(FileFilter filter) {
        this.filter = filter;
    }

    @Override
    public void run() {
        System.out.println("Runnable Task with " + filter + " on thread " + Thread.currentThread().getName());
    }
}
