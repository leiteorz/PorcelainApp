package com.android.china.model;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * @Author Crwei
 * date 2023/3/24 21:01
 */
@Entity(tableName = "ChinaHistory")
public class ChinaHistory {
    @NotNull
    @PrimaryKey(autoGenerate = false)
    private String name;
    private String description;
    private int imageId;
    @Ignore
    public ChinaHistory() {
    }

    public ChinaHistory(String name, String description, int imageId) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ChinaHistory{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
