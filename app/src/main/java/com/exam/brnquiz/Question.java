package com.exam.brnquiz;

import java.util.ArrayList;

public class Question {

    String questionText;
    ArrayList<String> options;
    int correctIndex;

    public Question(String questionText, ArrayList<String> options, int  correctIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public Question(){
    } // empty constructor

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(int currentIndex) {
        this.correctIndex = currentIndex;
    }

    public void incrementIndex(){
        correctIndex++;
    }
}
