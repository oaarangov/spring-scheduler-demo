package com.oarango.spring.scheduler.arch.infrastructure.scheduler.jobs;

public interface Connection {
    void setup(Object... options);

    void connect();

    void read(Object src, Object dst);

    void write(Object src, Object dst);

    void close();
}
