package sample;

import java.util.HashMap;

//Tüm methodlar JavaFX gerektirdiğinde kabaca doldurulmuş bulunmaktadır.
// JavaFX ile doldurulmaya başlandığında daha mantıklı iş yapar hale gelecekler.

/**
 * This class is used by Application class to create a new story.
 * With this class you can add new questions, answers to your story and save your story for others to play.
 **/
public class Maker extends User {

    //Yaratılan oyunu kendi içerisinde tutarak, kaydetme işlemini kolaylaştırır
    /**
     * Components of the new story is stored in newStory. Used in save method to save the story.
     */
    private Story newStory;

    //Karakter statlarının ismini ve başlangıç değerlerini tutar.
    /**
     * Stores character' name, stats and initial values of character's stats.
     */
    private HashMap<String, Integer> gameChar;


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

    /**
     * General method for story creation mode.
     */
    //Tüm senaryo yaratma işlemlerinin gerçekleşeceği genel method
    public void createStory(){
        Question firstQuestion;

        //Döngü içerisinde stat isimleri alınacak. Başlangıç değerleri girilecek

        //Oyuna başlangıç sorusu oluşturulacak







        //Kaydete basılırsa save methodunu çağır.
        save();

    }



}