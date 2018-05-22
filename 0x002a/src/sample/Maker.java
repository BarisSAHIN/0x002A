package sample;

import javafx.util.Pair;

import java.util.HashMap;

//ÖNEMLİ NOT : UI ile ilgili metodları içermemektedir. Şu anlık sadece, yeni bir Question ve Answer yapımını sağlamaktadır.
//Verilen Answer ve Questionlar ile bir Hashtable oluşturup, onları istenilen dosya moduna çekmedtedir.
/**
 * This class is used by Application class to create a new story.
 * With this class you can add new questions, answers to your story and save your story for others to play.
 **/
public class Maker extends User {


    HashMap<Integer,Question> questions;
    HashMap<Integer,Answer> answers;

    Question selectedQuestion;
    Answer selectedAnswer;

    int createdQuestionNum;
    int createdAnswerNum;

    /** Stores character' name, stats and initial values of character's stats.*/
    private HashMap<String, Integer> gameChar;

    public Maker(){
        questions = new HashMap<Integer,Question>();
        answers = new HashMap<Integer,Answer>();
        selectedAnswer = null;
        selectedQuestion = null;
        createdAnswerNum = 0;
        createdQuestionNum = 0;
    }

    // Question Part

    public void updateQuestion(String _text, HashMap<String, Pair<Character,Integer>> _sideEff, int numOfAnswers ) throws IDNotAllowed {

        selectedQuestion.setPreRequisite( _sideEff);
        selectedQuestion.setText(_text);
        if(selectedQuestion.Answers.size() != numOfAnswers){
            //TODO adjust size with default answers.
        }
    }

    public void createNewQuestion()throws IDNotAllowed{
        int id = ++createdQuestionNum;
        Question newQ = new Question(id);
        questions.put(newQ.getId(),newQ);
        selectedQuestion = newQ;
    }

    public void selectQuestion (Question selected) throws IllegalArgumentException{

        if(selected != null){
            selectedQuestion = questions.get(selected.getId());
        }else{
            throw new IllegalArgumentException("Given question in selectQuestion method is null\n");
        }
    }






    //Answer Part



    /**
     * Adds a new question to the answer given in parameter and returns the question.
     * @param currAnswer Answer that leads to new question.
     * @return Returns the new question.
     */
    //Verilen cevaba soru ekler. Eğer Parametre olan Answer null verilirse, başlangıç sorusu olarak eklenir.
    //Added temporary try catch blocks to avoid compile errors.
    public Question addQuestion(Answer currAnswer){
        try{
            return new Question();
        }
        catch(Exception e){
            return null;
        }
    }

    //Creates a new Answer
    public Answer createAnswer(){

        return new Answer();
    }


    //Takes an answer, creates a new and replaces new one with the old one
    public Answer changeAnswer(Answer oldAns){
        return oldAns;
    }

    public Question changeQuestion(Question oldQues){
        return oldQues;
    }




    /**
     * Adds an answer to the question given in parameter and returns the answer.
     * @param currQuestion Question to add the new answer.
     * @return Returns the new answer.
     */
    //Verilen soruya cevap ekler
    public Answer addAnswer(Question currQuestion){
        return new Answer();
    }

    /**
     * Saves the created story in a file to share with players.
     */
    //Yaratılan oyun senaryosunu, daha sonra oynanabilmesi için dosya olarak kaydeder.
    public void save(){

    }






}