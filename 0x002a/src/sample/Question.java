package sample;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Question extends Node implements Comparable {
    private int id;
    private ArrayList<Answer> Answers;
    private String QuestionText;
    HashMap<String, Pair<Character, Integer>> preRequisite;

    public Question(){
        setAnswers(null);
        setText();
        this.id = 0;
        preRequisite = new HashMap<>();
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

    public Question(String line){
        Answers = new ArrayList<Answer>();
        parseLine(line);
    }

    //for maker
    public Question(String text, ArrayList<Answer> a){
        setAnswers(a);
        QuestionText = text;
    }
    public Question(String text, ArrayList<Answer> a,int ID){
        setAnswers(a);
        QuestionText = text;
        id = ID;
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

    public HashMap<String, Pair<Character, Integer>> getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(HashMap<String, Pair<Character, Integer>> preRequisite) {
        this.preRequisite = preRequisite;
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
         if(this.id == ((Question) o).getId())
             return 0;
         else if(this.id > ((Question) o).getId())
            return 1;
         else return -1;

    }

    public String toString(){
        String ret = id+"\\)"+QuestionText;
        for(Answer k: Answers){
            ret = ret + k;
            ret += "/";
        }
        ret += ".";
        return ret;
    }

    private void parseLine(String line){
        String[] temp = line.split("\\)");
        id = Integer.parseInt(temp[0]);
        temp = temp[1].split("-");
        QuestionText = temp[0];
        temp = temp[1].split("/");
        for(String k : temp){
            Answers.add(new Answer(k));
        }
    }
}
