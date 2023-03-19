package com.android.china.model;

public class ArPorcelain {
    private int id;
    private String name;
    private int picture_id;

    {
        id = -1;
    }
    public ArPorcelain(String name,int picture_id){
        this.name = name;
        this.picture_id = picture_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }
}
