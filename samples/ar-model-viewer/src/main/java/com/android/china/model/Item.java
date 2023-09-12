package com.android.china.model;
public class Item {
    private String name;
    private double score;

    public Item(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
