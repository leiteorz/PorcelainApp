package com.android.china.model;

/**
 * @Author Crwei
 * date 2023/4/21 18:33
 */

public class PorcelainShape {
    int id;
    String name;

    public PorcelainShape(int id, String name) {
        this.id = id;
        this.name = name;
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
}
