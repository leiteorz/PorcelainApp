package com.android.china.model;

/**
 * @Author Crwei
 * date 2023/4/21 19:26
 */

public class Pattern {
    String tab;
    int id;
    int check = 0;

    public Pattern(String tab, int id) {
        this.tab = tab;
        this.id = id;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
