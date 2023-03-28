package com.android.china.model;

import com.android.china.adpter.GuanCangAdapter;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GuanCangs")
public class GuanCang implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id = -1;
    String name;    //名字
    String description; //描述
    String content; //点进去的内容
    int imageId;    //图片
    public GuanCang(){

    }
    public GuanCang(String _name, String _description, int _imageId){
        this.name = _name;
        this.description = _description;
        this.imageId = _imageId;
    }

    public GuanCang(String _name, String _description, int _imageId,String _content){
        this.name = _name;
        this.description = _description;
        this.imageId = _imageId;
        this.content = _content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
