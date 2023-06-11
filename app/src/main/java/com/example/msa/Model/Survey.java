package com.example.msa.Model;

public class Survey {
    private int id;
    private String title;
    private String start;
    private String end;

    public Survey(int id, String title, String start, String end) {
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getEnd() {
        return end;
    }

    public String getStart() {
        return start;
    }

    @Override
    public String toString() {
        return title + " valid: " + start + " to " + end;
    }
}
