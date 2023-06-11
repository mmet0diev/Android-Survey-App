package com.example.msa.Model;

public class Question {
    private int id;
    private String question;

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    public String getText() {
        return question;
    }

    @Override
    public String toString() {
        return question;
    }
}
