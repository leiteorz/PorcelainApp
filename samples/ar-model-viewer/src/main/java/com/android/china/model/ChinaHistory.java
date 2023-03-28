package com.android.china.model;

/**
 * @Author Crwei
 * date 2023/3/24 21:01
 */

public class ChinaHistory {
    String name;    //名字
    String description; //描述
    int imageId;    //图片

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
