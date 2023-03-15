package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

import java.io.FileFilter;

public class PdfRunnerTask implements Runnable {

    private final FileFilter filter;

    public PdfRunnerTask(FileFilter filter) {
        this.filter = filter;
    }

    @Override
    public void run() {
        System.out.println("Runnable Task with " + filter + " on thread " + Thread.currentThread().getName());
    }
}