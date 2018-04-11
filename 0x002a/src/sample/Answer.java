package sample;

import java.util.HashMap;
import java.util.Scanner;
import javafx.util.Pair;

public class Answer extends Node {
    public Question next;
    String answerText;
    HashMap<String, Pair<Character, Integer>> statsToBeChanged;
    Answer(){
        next = null;
        writeAnswer();
    }

    private void writeAnswer(){
        Scanner sc = new Scanner(System.in);
        answerText = sc.next();
    }

    public void showAnswer(){
        System.out.println(answerText);
    }

    public Question GetNextQuestion(){
        return next;
    }

    public void setNextQuestion(Question nextQuestion){
        next = nextQuestion;
    }

    public boolean hasNextQuestion(){
        if( next != null){
            return true;
        }
        else{
            return false;
        }
    }

    public HashMap<String, Pair<Character, Integer>> changeStats(){
        return statsToBeChanged;
    }

}
