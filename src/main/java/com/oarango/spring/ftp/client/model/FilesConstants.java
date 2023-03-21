package com.oarango.spring.ftp.client.model;

public class FilesConstants {
    public static final String PDF_FILE_EXTENSION = "pdf";
    public static final String LOCAL_FILES_PATH =
            System.getProperty("user.dir")
                    .concat(System.getProperty("file.separator"))
                    .concat("temps")
                    .concat(System.getProperty("file.separator"));
    public static final String REMOTE_FILES_PATH = "/egakat/";
}
