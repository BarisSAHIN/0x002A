package sample;

import java.util.Scanner;

public class Player extends User {

    private Story theStory;



    public Player(){
        theStory=new Story(pickGame());
    }

    public void play(){
        while(theStory.isEnd())
            giveAnswer();
        finish();
    }

    /**
     * Gets game file from user and returns informations to Application by filling a Story object.
     */
    public String pickGame(){
        System.out.println("Enter game file name you want to play: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void giveAnswer(){
        System.out.println("Your Answer: ");
        Scanner scanner = new Scanner(System.in);
        if(!(theStory.isAnswerLegal(scanner.nextLine())))
            giveAnswer();
    }

    public void finish(){

    }
}