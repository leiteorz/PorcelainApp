package com.android.china.model;

/**
 * @Author Crwei
 * date 2023/3/14 21:01
 */

public class China {
    private String describe;
    private int imageId;

    public China(String describe, int imageId) {
        this.describe = describe;
        this.imageId = imageId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
