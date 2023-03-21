package com.oarango.spring.ftp.client.connector;

import java.util.List;

public interface Connection {
    void setup(Object... args);

    void open();

    Object list(String path);

    void download(String path);

    void upload(Object src, Object dst);

    void close();
}
