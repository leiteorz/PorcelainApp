package com.android.china.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DiyWorks")
public class DiyWork {
    int shape = 1;
    int pattern = 1;
    @NonNull
    @PrimaryKey
    String name;

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getPattern() {
        return pattern;
    }

    public void setPattern(int pattern) {
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiyWork(int shape, int pattern, String name) {
        this.shape = shape;
        this.pattern = pattern;
        this.name = name;
    }
}
