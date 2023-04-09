package com.android.china.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * @Author Crwei
 * date 2023/3/24 21:01
 */
@Entity(tableName = "ChinaHistory")
public class ChinaHistory implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id = 0;
    String name;
    String description;
    String imageUrl = "https://leiteorz.oss-cn-hangzhou.aliyuncs.com/img/127BF99700BFAFEF3D3AF6C4C6B0DAC8.jpg";
    int imageId;
    @Ignore
    public ChinaHistory(String name, String description, int imageId,String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
