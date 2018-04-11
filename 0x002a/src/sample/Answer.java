package sample;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * muaz yazdı
 */
public class Answer extends Node {
    public Question next;
    String answerText;
    HashMap<String, Pair<Character, Integer>> statsToBeChanged;
    Answer(){
        next = null;
        writeAnswer();
        initPair();
    }

    private void initPair(){
        Character statOp;
        int useStat;
        System.out.println("Do you wanna use stats? 1 for yes any key for no");
        Scanner sc = new Scanner(System.in);
        useStat = sc.nextInt();
        if(useStat == 1){
            String name;
            int changeValue;
            System.out.println("Enter Stat Name");
            name = sc.next();
            System.out.println("Enter Stat Change Operation '+','-','/','*'");
            statOp = sc.next().charAt(0);
            System.out.println("Enter Stat Change Value");
            changeValue = sc.nextInt();

            statsToBeChanged = new HashMap<>();
            Pair<Character, Integer> pairStat = new Pair<>(statOp,changeValue);
            statsToBeChanged.put(name,pairStat);

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

    public HashMap<String, Pair<Character, Integer>> changeStats(){
        return statsToBeChanged;
    }

}
