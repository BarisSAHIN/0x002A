package sample;

import java.util.HashMap;
import java.util.Scanner;

public class Answer extends Node {
    public Question next;
    String answerText;
    HashMap<String, Integer> statsToBeChanged;
    Answer(){
        int useStat;
        next = null;
        writeAnswer();
        System.out.println("Do you wanna use stats? 1 for yes any key for no");
        Scanner sc = new Scanner(System.in);
        useStat = sc.nextInt();
        if(useStat == 1){
            String name;
            int stat;
            System.out.println("Enter Stat Name and Stat Value");
            name = sc.next();
            stat = sc.nextInt();
            statsToBeChanged = new HashMap<String, Integer>();
            statsToBeChanged.put(name,stat);
        }
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

    public HashMap<String, Integer> changeStats(){
        return statsToBeChanged;
    }

}
