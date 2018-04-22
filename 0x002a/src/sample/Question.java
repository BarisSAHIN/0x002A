package sample;

import java.util.ArrayList;
import java.util.Scanner;

public class Question extends Node implements Comparable {
    private int id;
    private ArrayList<Answer> Answers;
    private String QuestionText;

    public Question(){
        setAnswers(null);
        setText();
        this.id = 0;
    }

    public Question(int id){
        setAnswers(null);
        setText();
        this.id = id;
    }

    public Question(ArrayList<Answer> a){
        setAnswers(a);
        setText();
    }

    //for maker
    public Question(String text, ArrayList<Answer> a){
        setAnswers(a);
        QuestionText = text;
    }

    public int getId(){ return id; }

    public boolean isEnd(){
        if(Answers.size() == 0)
            return true;
        else
            return false;
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

    @Override
    public int compareTo(Object o) {
        return (this.id == ((Question) o).getId()) ? 1 : 0;
    }
}
