package com.android.china.model;

public class PorcelainStory {
    int id = -1;
    String name;
    String description;
    int imageId;

    public PorcelainStory(String _name,String _description,int _imageId){
        this.name = _name;
        this.description = _description;
        this.imageId = _imageId;
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
}
