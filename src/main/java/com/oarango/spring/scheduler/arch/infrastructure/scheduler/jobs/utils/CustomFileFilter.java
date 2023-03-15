package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs.utils;

import java.io.File;
import java.io.FileFilter;

public class CustomFileFilter implements FileFilter {
    private final String extension;

    public CustomFileFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File pathname) {
        return pathname.getName().toLowerCase().endsWith(extension);
    }

    @Override
    public String toString() {
        return extension;
    }
}
