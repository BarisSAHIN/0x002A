package sample;

import javafx.util.Pair;
import java.util.HashMap;
import java.util.Scanner;

/**
 *  Answer class extended from Node.
 *  Answer's object hold Answer text and result.
 *  (result: Next Question, stats or End Game).
 */
public class Answer extends Node {

    private static final String SPLITTER = "\\*";
    public Question next;
    int nextQuestionID;
    String answerText;
    HashMap<String, Pair<Character, Integer>> statsToBeChanged;


    /**
     * Non-parameter constructor.
     */
    Answer(){
        next = null;
        writeAnswer();
        initPair();
    }

    /**
     * Constructor with parameter.
     * @param input type is string.
     * İt's parse to string.After initialize answerText and nextQuestionID.
     */
    Answer(String input){
        String[] tokens = input.split(SPLITTER );
        answerText = tokens[0];
        nextQuestionID = Integer.parseInt(tokens[1]);

    }

    /**
     * Initialize pair for use "Character Stat" feature.
     */
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

    /**
     * This method take Answer Text from the user.
     */
    private void writeAnswer(){
        Scanner sc = new Scanner(System.in);
        answerText = sc.next();
    }
    /**
     * This method show Answer Text to the user.
     */
    public void showAnswer(){
        System.out.println(answerText);
    }
    /**
     * If this answer object selected by user in a game.
     * This method will take user to next question.
     * @return next question address.
     */
    public Question GetNextQuestion(){
        return next;
    }
    /**
     * This method will use for set next quest after the this answer.
     */
    public void setNextQuestion(Question nextQuestion){
        System.out.println("don ");
        next = nextQuestion;
    }
    /**
     * This method will use for answer have a next question.
     * If there is no next question, it's mean game is ended.
     * @return boolean. İf next variable == null return  false.
     * Otherwise return true.
     */
    public boolean hasNextQuestion(){
        if( next != null){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This method will use for "Character Stats" feature.
     * @return statsToBeChanged variable.
     */
    public HashMap<String, Pair<Character, Integer>> changeStats(){
        return statsToBeChanged;
    }

    /**
     * Override toString method.
     * @return answerText string variable.
     */
    @Override
    public String toString() {
        return " " + answerText + " ";
    }
}
