package sample;

import java.util.ArrayList;
import java.util.Scanner;

public class Question extends Node {
    private ArrayList<Answer> Answers;
    private String QuestionText;

    public Question(){
        setAnswers(null);
        setText();
    }

    public Question(ArrayList<Answer> a){
        setAnswers(a);
        setText();
    }

    public ArrayList<Answer> GetAnswers(){
        return Answers;
    }

    private void setText(){
        Scanner sc = new Scanner(System.in);
        QuestionText = sc.next();
    }

    private void setAnswers(ArrayList<Answer> a){
        Answers = a;
    }

    public void AddAnswer(Answer a){
        if(Answers.size() < 4) {
            Answers.add(a);
        }
        else{
            System.out.println("You can't add more answer.Max Answer");
        }
    }

    public void showQuestion(){
        System.out.println(QuestionText);
    }

    public void showQuestionAndAnswer(){
        System.out.println(QuestionText);
        for(int i=0; i < Answers.size(); ++i){
            Answers.get(i).showAnswer();
        }
    }

}
