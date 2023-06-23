package com.example.msa.Model;

public class Survey {
    private int id;
    private String title;

    public Survey(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
