package com.example.msa.Model;

public class Survey {

    private int id;
    private String title;

    // Constructor with both id and title
    public Survey(int id, String title) {
        this.id = id;
        this.title = title;
    }

    // Constructor with only title (for adding new surveys without an explicit id)
    public Survey(String title) {
        this.title = title;
    }

    // Getters and setters for id and title
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Override toString method for meaningful representation
    @Override
    public String toString() {
        return title;
    }
}
