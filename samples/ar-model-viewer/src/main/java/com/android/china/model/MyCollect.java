package com.android.china.model;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "MyCollects")
public class MyCollect {
    /**
     * 这个id指的是收藏的东西的id
     */
    int id;
    /**
     * kind = 0: 是收藏的科普里面的内容
     * kind = 1: 是收藏的馆藏里面的内容
     */
    int kind;   //种类
    /**
     * 是否收藏
     * 0: 未收藏
     * 1: 收藏
     */
    int isCollected;

    String name;

    String description;

    int image;

    @Ignore
    public MyCollect(int id,int kind,int isCollected,String name,String description,int image){
        this.id = id;
        this.kind = kind;
        this.isCollected = isCollected;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getIsCollected() {
        return isCollected;
    }

    public void setIsCollected(int isCollected) {
        this.isCollected = isCollected;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
