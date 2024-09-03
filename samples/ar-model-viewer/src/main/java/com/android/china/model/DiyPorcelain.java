package com.android.china.model;

public class DiyPorcelain {
    int id = -1;
    String name;
    int pictureId;

    public DiyPorcelain(String _name,int _pictureId){
        this.name = _name;
        this.pictureId = _pictureId;
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

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }
}
