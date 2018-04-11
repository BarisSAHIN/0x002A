package sample;

import java.util.ArrayList;

public class Question extends Node {
    private ArrayList<Answer> Answers;
    private String QuestionText;

    public Question(){
        setAnswers(null);
        setText(" ");
    }

    public Question(String q){
        setAnswers(null);
        setText(q);
    }

    public Question(String q, ArrayList<Answer> a){
        setAnswers(a);
        setText(q);
    }

    public ArrayList<Answer> GetAnswers(){
        return Answers;
    }

    private void setText(String q){
        QuestionText = q;
    }

    private void setAnswers(ArrayList<Answer> a){
        Answers = a;
    }

    public void AddAnswer(Answer a){
        Answers.add(a);
    }
}
