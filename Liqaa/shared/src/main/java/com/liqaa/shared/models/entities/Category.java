package com.liqaa.shared.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Category implements Serializable
{
    private int id;
    private int userId;
    private String categoryName;
    private Timestamp createdAt;

    public Category(int id, int userId, String categoryName, Timestamp createdAt)
    {
        this.id = id;
        this.userId = userId;
        this.categoryName = categoryName;
        this.createdAt = createdAt;
    }

    public Category(int userId, String categoryName) {
        this.userId = userId;
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", userId=" + userId +
                ", categoryName='" + categoryName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
