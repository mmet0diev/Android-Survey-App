package com.example.msa.Model;

public class Answer {
    private int id;
    private int answer;

    public Answer(int id, int answer) {
        this.id = id;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return " " + answer + " ";
    }
}

