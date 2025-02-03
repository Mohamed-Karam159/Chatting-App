package com.liqaa.shared.models.entities;

public class FileMessage {
    private int id;
    private int messageId;
    private String fileName;
    private int fileSize;
    private String filePath;

    // Constructor
    public FileMessage() {}

    public FileMessage(int id, int messageId, String fileName, int fileSize, String filePath) {
        this.id = id;
        this.messageId = messageId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "FileMessage{" +
                "id=" + id +
                ", messageId=" + messageId +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
