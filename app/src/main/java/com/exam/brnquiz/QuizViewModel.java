package com.exam.brnquiz;

import androidx.lifecycle.MutableLiveData;

import java.util.LinkedList;

public class QuizViewModel extends androidx.lifecycle.ViewModel {


    private MutableLiveData<Integer> answer;

    int answerInt;
    MutableLiveData<Question> current;


    public QuizViewModel() {
        answer = new MutableLiveData<>();
        current = new MutableLiveData<>();
        answerInt = 0;
    }

    public MutableLiveData<Question> getCurrent() {
        return current;
    }

    public void setCurrent(Question c) {
        current.setValue(c);
    }

    public MutableLiveData<Integer> getAnswer(){
        return answer;
    }

    public void setAnswer(Integer l)
    {
        answer.setValue(l);
        answerInt = l;
    }

    public void addAnswer (Integer m){
        answer.setValue(m);
    }
    public int getAnswerInt (){
        return answerInt;
    }

    public void addInt (int m){
        answer.setValue(m);
        //answerInt = m;
    }



}
