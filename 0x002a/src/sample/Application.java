package sample;

import java.util.Scanner;

public class Application {

    int user=0;
    Story story;

    public void mainMenu(){

        Scanner chooseScanner = new Scanner(System.in);

        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------");
        System.out.println("-------------- W E L C O M E --------------");
        System.out.println("------------------ T H E ------------------");
        System.out.println("--------------- 0 x 0 0 2 A ---------------");
        System.out.println("-------------------------------------------");
        System.out.println("-------------------------------------------\n\n");


        System.out.println(" 1) Player\n 2) Maker");
        user = chooseScanner.nextInt();

        if(user==1){

            Player gamePlayer= new Player();
            gamePlayer.play();

        }
        else if(user==2){
            Maker gameMaker = new Maker();
            gameMaker.createStory();

        }
        // take user from screen(some javafx  shit);
    }





}