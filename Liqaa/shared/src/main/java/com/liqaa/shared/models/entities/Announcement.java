package com.liqaa.shared.models.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Announcement implements Serializable {
    private int id;
    private String title, content;
    private LocalDateTime sentAt;

    public Announcement(int id, String title, String content, LocalDateTime sentAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.sentAt = sentAt;
    }

    public Announcement(String title, String content, LocalDateTime sentAt) {
        this.title = title;
        this.content = content;
        this.sentAt = sentAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
