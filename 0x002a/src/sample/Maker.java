package sample;

import javafx.util.Pair;

import java.util.ArrayList;
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
    CStats stats;
    Question selectedQuestion;
    Answer selectedAnswer;

    int createdQuestionNum;
    int createdAnswerNum;

    /** Stores character' name, stats and initial values of character's stats.*/
    private HashMap<String, Integer> gameChar;

    public Maker(){
        questions = new HashMap<Integer,Question>();
        answers = new HashMap<Integer,Answer>();
        stats = new CStats();
        selectedAnswer = null;
        selectedQuestion = null;
        createdAnswerNum = 0;
        createdQuestionNum = 0;
    }

    // Question Part

    /**
     * Updates selectedQuestion with given parameters.
     * @caution numOfAnswers must be smaller than 5
     * @param _text new text for Question
     * @param _sideEff new sideEffect for Question
     * @param numOfAnswers num of answers in this question
     * @throws IDNotAllowed
     */
    public void UpdateQuestion(String _text, HashMap<String, Pair<Character,Integer>> _sideEff, int numOfAnswers ) throws IDNotAllowed {

        selectedQuestion.setPreRequisite( _sideEff);
        selectedQuestion.setText(_text);
        // numOfAnswers'ın 4 veya daha küçük olacağı öngörülerek yazılmıştır.
        while(selectedQuestion.Answers.size() > numOfAnswers){
            RemoveLastAnswer();
        }
        while(selectedQuestion.Answers.size() != numOfAnswers){
            CreateNewAnswer();
        }
    }

    /**
     * Creates a new Question with default text and 0 answers.
     * @throws IDNotAllowed
     */
    public void CreateNewQuestion()throws IDNotAllowed{
        int id = ++createdQuestionNum;
        Question newQ = new Question(id);
        questions.put(newQ.getId(),newQ);
        selectedQuestion = newQ;


    }

    /**
     * Method for GUI usage. Changes selectedQuestion according to the mouse click
     * @param selected Question that has been clicked on
     * @throws IllegalArgumentException happens only if clicked question is null
     */
    public void SelectQuestion (Question selected) throws IllegalArgumentException{
        if(selected != null){
            selectedQuestion = questions.get(selected.getId());
            selectedAnswer = null;
        }else{
            throw new IllegalArgumentException("Given question in selectQuestion method is null\n");
        }
    }

    /**
     * Deletes selected question.
     */
    public void DeleteQuestion(){
        if(selectedQuestion != null){
            RemoveAllAnswers();
            questions.remove(selectedQuestion.getId());
            //TODO check for answers goin for that question ?
            selectedQuestion = null;
        }
    }


    //Answer Part

    /**
     * Updates answer with given parameters.
     * @param _text new text for answer
     * @param targetQuestion new destination Question ID for answer
     * @param _preReq new preRequisite for answer
     */
    public void UpdateAnswer(String _text, int targetQuestion,HashMap<String,Pair<Character,Integer>> _preReq ){
        selectedAnswer.UpdateAllFields(_text,questions.get(targetQuestion),_preReq);
    }

    /**
     * Creates new answer with default text, special ID and null destionation for selectedQuestion.
     * Max Answer for a Question is 4.
     */
    public void CreateNewAnswer(){
        int id = ++createdAnswerNum;
        Answer newA = new Answer(id, selectedQuestion.getId());
        answers.put(newA.getId(), newA);
        selectedQuestion.AddAnswer(newA);
    }

    /**
     * Deletes selectedAnswer from the selectedQuestion
     * @post selectedAnswer becomes null
     */
    public void DeleteAnswer(){
        selectedQuestion.Answers.remove(selectedAnswer);
        answers.remove(selectedAnswer.id);
        selectedAnswer = null;
    }

    /**
     * Removes lastAnswer from the selectedQuestion
     * @return removed answer
     */
    private Answer RemoveLastAnswer(){
        //TODO check if index is correct ( -1 is needed ? )
        return  selectedQuestion.Answers.remove(selectedQuestion.Answers.size()-1 );
    }

    /**
     * Removes all answers from the selectedQuestion
     */
    private void RemoveAllAnswers(){
        while(selectedQuestion.Answers.size() != 0){
            RemoveLastAnswer();
        }
        selectedAnswer = null;
    }


    //CStats

    /**
     * Updates CStats for game Character.
     * @param statName String array for name of the stats
     * @param values Integer array for default values for the stats
     */
    public void UpdateCStats(String[] statName, Integer[] values){
        for(int i = 0; i<3; ++i){
            stats.addParam(statName[i], values[i]);
        }
    }

    /**
     * Returns the stat name for given index
     * @param i index for stat name
     * @return Required stat name
     */
    public String getStatName(int i){
        return (String)stats.getStats().toArray()[i];
    }


    public int currentNumOfQuestions(){
        return questions.size();
    }

    public int currentNumOfAnswers(){
        return answers.size();
    }

    public int numOfDifferentEndings(){
        int endings = 0;
        for(int i = 0; i<createdQuestionNum; ++i){
            if(questions.containsKey(i)){
                if(questions.get(i).Answers.size() == 0){
                    ++endings;
                }
            }
        }
        return endings;
    }


    /**
     * Saves the created story in a file to share with players.
     */
    //Yaratılan oyun senaryosunu, daha sonra oynanabilmesi için dosya olarak kaydeder.
    public void save(){
        //TODO: Son belirlenen formata göre yazılmalı.
    }
}