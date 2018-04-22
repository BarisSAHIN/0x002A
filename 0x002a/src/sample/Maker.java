package sample;

import java.util.HashMap;

//Tüm methodlar JavaFX gerektirdiğinde kabaca doldurulmuş bulunmaktadır.
// JavaFX ile doldurulmaya başlandığında daha mantıklı iş yapar hale gelecekler.

public class Maker extends User {

    //Yaratılan oyunu kendi içerisinde tutarak, kaydetme işlemini kolaylaştırır
    private Story newStory;

    //Karakter statlarının ismini ve başlangıç değerlerini tutar.
    private HashMap<String, Integer> gameChar;


    //Verilen cevaba soru ekler. Eğer Parametre olan Answer null verilirse, başlangıç sorusu olarak eklenir.
    public Question addQuestion(Answer currAnswer){
        return new Question();
    }

    //Verilen soruya cevap ekler
    public Answer addAnswer(Question currQuestion){
        return new Answer();
    }

    //Yaratılan oyun senaryosunu, daha sonra oynanabilmesi için dosya olarak kaydeder.
    public void save(){

    }

    //Tüm senaryo yaratma işlemlerinin gerçekleşeceği genel method
    public void createStory(){
        Question firstQuestion;

        //Döngü içerisinde stat isimleri alınacak. Başlangıç değerleri girilecek

        //Oyuna başlangıç sorusu oluşturulacak







        //Kaydete basılırsa save methodunu çağır.
        save();

    }



}