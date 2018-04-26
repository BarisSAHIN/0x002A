package sample;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *      This class is needed for game's/application's Questions.
 *      A question is a Node.
 *      Implements Comparable to do ID comparisions.
 */
public class Question extends Node implements Comparable {
    /**
     *  Each question has a unique ID. ID is stored as integer.
     */
    private int id;
    /**
     *  Each question has Answers in itself. Answers are stored as ArrayList, size can be
     *   0,1,2,3 or 4.
     */
    private ArrayList<Answer> Answers;
    /**
     *  Question text to be showed to User. Text is stored as String.
     */
    private String QuestionText;
    /**
     *  There are questions with prerequisites. If User can not satisfy these, game will move
     *   to another question.
     */
    HashMap<String, Pair<Character, Integer>> preRequisite;


    /**
     *  Inits ID with zero and inits class' data fields.
     * @throws IDNotAllowed ID's cannot be negative numbers.
     */
    public Question() throws IDNotAllowed {
        Answers = new ArrayList<Answer>();
        setAnswers(null);
        setText();
        setId(0);
        preRequisite = new HashMap<>();
    }

    /**
     *  Constructor to create a question with specific ID.
     * @param id Integer value to be set as ID.
     */
    public Question(int id) throws IDNotAllowed {
        Answers = new ArrayList<Answer>();
        setAnswers(null);
        setText();
        setId(id);
        preRequisite = new HashMap<>();
    }

    /**
     *  Creates a question with given Answers. Answers are expected as ArrayList.
     *  In order to set question ID, please use setID() method.
     * @param a Answers to be initialized as ArrayList.
     */
    public Question(ArrayList<Answer> a){
        Answers = new ArrayList<Answer>();
        setAnswers(a);
        setText();
        preRequisite = new HashMap<>();
    }

    /**
     *  Creates a question with the given line from file.
     *  Line format should be like this->
     *   QuestionID)QuestionText-Answer1Text*LinkedQuestion/Answer2Text*LinkedQuestion...
     * @param line A single line from the text file.
     */
    public Question(String line){
        Answers = new ArrayList<Answer>();
        parseLine(line);
        preRequisite = new HashMap<>();
    }

    /**
     *  This constructor is written for Maker class' needs.
     * @param text Question Text as a string.
     * @param a Question's answers as ArrayList.
     */
    public Question(String text, ArrayList<Answer> a){
        Answers = new ArrayList<Answer>();
        setAnswers(a);
        QuestionText = text;
        preRequisite = new HashMap<>();
    }

    /**
     *  This constructor inits Question Text, Question's answers and Question ID all together.
     * @param text Question text as String.
     * @param a Question's answers as ArrayList.
     * @param ID Question's ID as Integer.
     */
    public Question(String text, ArrayList<Answer> a, int ID) throws IDNotAllowed{
        setAnswers(a);
        QuestionText = text;
        setId(ID);
        preRequisite = new HashMap<>();
    }

    /**
     *  Getter for Question ID.
     * @return Question ID as Integer.
     */
    public int getId(){ return id; }

    /**
     *  Setter for ID. Throws exception if ID's value is negative.
     * @param id Question's ID to be set. Cannot be negative number.
     * @throws IDNotAllowed when ID is a negative number, exception occurs.
     */
    public void setId(int id) throws IDNotAllowed{
        if(id<0)
            throw new IDNotAllowed("Cannot init id with negative number. IDNotAllowed Exception.");
        else
            this.id = id;
    }

    /**
     *  When a Question has no Answer, it is marked as Story End.
     * @return Returns true if Story is ended, false otherwise.
     */
    public boolean isEnd(){
        if(Answers.size() == 0)
            return true;
        else
            return false;
    }

    /**
     *  Getter for Answers.
     * @return Returns an ArrayList with Answers.
     */
    public ArrayList<Answer> GetAnswers(){
        return Answers;
    }

    /**
     *  Setter for Question Text.
     */
    private void setText(){
        Scanner sc = new Scanner(System.in);
        QuestionText = sc.next();
    }

    /**
     *  Setter for Answers. Sets Question's anwers with given ArrayList.
     * @param a ArrayList of Answers.
     */
    private void setAnswers(ArrayList<Answer> a){
        Answers = a;
    }

    /**
     *  Getter for Prerequisites.
     * @return Returns a HashMap with prerequisite.
     */
    public HashMap<String, Pair<Character, Integer>> getPreRequisite() {
        return preRequisite;
    }

    /**
     *  Setter for Prerequisites.
     * @param preRequisite HashMap as prerequisite.
     */
    public void setPreRequisite(HashMap<String, Pair<Character, Integer>> preRequisite) {
        this.preRequisite = preRequisite;
    }

    /**
     *  Adds only one answer to Question. If Question already has 4 answers, another answer cannot be added.
     * @param a Answer object to be added to Question.
     */
    public void AddAnswer(Answer a){
        if(Answers.size() < 4) {
            Answers.add(a);
        }
        else{
            System.out.println("You can't add more answer.Max Answer");
        }
    }

    /**
     *  Printer method for Question Text.
     */
    public void showQuestion(){
        System.out.println(QuestionText);
    }

    /**
     *  Printer method for Question and Question's answers.
     */
    public void showQuestionAndAnswer(){
        System.out.println(QuestionText);
        for(int i=0; i < Answers.size(); ++i){
            Answers.get(i).showAnswer();
        }
    }

    /**
     *  Compares IDs only.
     * @param o another Question object.
     * @return Returns 1 if Question's id is bigger than other's id. -1 if smaller.
     */
    @Override
    public int compareTo(Object o) {
        return (this.getId() > ((Question) o).getId()) ? 1 : -1;
    }

    /**
     *  Puts together parsed data with the format needed to be in text file. Format is:
     *      QuestionID)QuestionText-Answer1Text*LinkedQuestion/Answer2Text*LinkedQuestion...
     * @return Returns line as String.
     */
    public String toString(){
        String ret = id+"\\)"+QuestionText;
        for(Answer k: Answers){
            ret = ret + k;
            ret += "/";
        }
        ret += ".";
        return ret;
    }

    /**
     *  Parses the line with specific characters in order to init Question's data. Line format is:
     *      QuestionID)QuestionText-Answer1Text*LinkedQuestion/Answer2Text*LinkedQuestion...
     * @param line Given String line to be parsed.
     */
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

/**
 *  Id's cannot be negative. In that case, throws exception.
 */
class IDNotAllowed extends Exception{
    public IDNotAllowed(String message){
        super(message);
    }
}
