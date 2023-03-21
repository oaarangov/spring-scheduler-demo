package com.oarango.spring.ftp.client.model;

import java.time.LocalDate;

public class AckMessage {
    private String fileName;
    private String status;
    private String message;
    private LocalDate receivedDate;
    private LocalDate sendedDate;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public LocalDate getSendedDate() {
        return sendedDate;
    }

    public void setSendedDate(LocalDate sendedDate) {
        this.sendedDate = sendedDate;
    }

    @Override
    public String toString() {
        return "AckMessage {" +
                "fileName: '" + fileName + '\'' +
                ", status: '" + status + '\'' +
                ", message: '" + message + '\'' +
                ", receivedDate: " + receivedDate +
                ", sendedDate: " + sendedDate +
                "}";
    }
}
