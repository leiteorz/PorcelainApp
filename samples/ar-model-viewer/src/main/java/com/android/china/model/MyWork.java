package com.android.china.model;

/**
 * 个人主页显示的我的作品
 */
public class MyWork {
    int id; //作品id
    private String name;    //作品名称
    private int picture_id; //图片存储地址

    //初始化id
    {
        this.id = -1;
    }

    /**
     * 构造方法
     */
    public MyWork(String _name,int _picture_id){
        this.name = _name;
        this.picture_id = _picture_id;
    }

    /**
     * getter and setter
     */
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
