package com.android.china.model;

import java.io.Serializable;


public class China implements Serializable {
    private String describe;
    private int imageId;
    private String bookName;
    private String bookAuthor;

    public China(String describe, int imageId, String bookName, String bookAuthor) {
        this.describe = describe;
        this.imageId = imageId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
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
