package sample;

import javafx.fxml.Initializable;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Player extends User implements Initializable{

    private Story theStory;
    private static final String pickedGame= "./saved";

    public Player() throws IOException, IDNotAllowed {
        try {
            theStory=new Story(pickGame());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IDNotAllowed ıdNotAllowed) {
            ıdNotAllowed.printStackTrace();
        }
    }

    /**
     * The main function that follows from first question to last question -answer by answer- till questions end.
     */
    public void play(){
        while(!theStory.isEnd()) {
            theStory.showQuestionAndAnswer();   //soru-cevapların basılması
            giveAnswer();       //playerın cevabını ekrandan okuma ve storynin sonraki soruya geçişi
        }
        System.out.println("!!!");
        finish();   //ulaşılan sonun ekrana basılması
    }

    /**
     * Prints saved game files at "saved" directory and gets the picked file name.
     */
    private String pickGame(){
        System.out.println("Saved games: ");
        File folder = new File(pickedGame);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; ++i) {
            if (listOfFiles[i].isFile())
                System.out.printf("\t%s\n", listOfFiles[i].getName());
        }
        System.out.println("Enter game file name you want to play: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
   }

    /**
     * Gets answer from terminal till the answer is legal.
     */
    public void giveAnswer(){
        System.out.println("Your Answer: ");
        Scanner scanner = new Scanner(System.in);
        theStory.isAnswerLegal(scanner.nextLine());
    }

    /**
     * Prints the text in 'last question' as result of answer path.
     */
    public void finish(){
        theStory.showQuestion();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}