package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Player extends User implements Initializable{

    private Story theStory;
    private static final String pickedGameFile= "./saved";
    Stack<Question> questions;


    @FXML AnchorPane fxmlQuestionAnswer;

    @FXML Label fxmlQuestionText;

    @FXML Button fxmlUndoButton;

    @FXML Button fxmlAnswer1;
    @FXML Button fxmlAnswer2;
    @FXML Button fxmlAnswer3;
    @FXML Button fxmlAnswer4;

    @FXML ListView<Question> fxmlPastQuestionList;


    public Player() throws IOException, IDNotAllowed {
        try {
            theStory=new Story("test1.txt");
            questions=new Stack<>();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IDNotAllowed ıdNotAllowed) {
            ıdNotAllowed.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fxmlUndoButton.setVisible(false);

        fxmlQuestionText.setText(theStory.getFirstQuestion().getQuestionText());
        ArrayList<Answer> firstAnswers=theStory.getFirstQuestion().GetAnswers();
        if(firstAnswers.get(0)!=null)
            fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
        if(firstAnswers.get(1)!=null)
            fxmlAnswer2.setText(firstAnswers.get(1).getAnswerText());
        if(firstAnswers.get(2)!=null)
            fxmlAnswer3.setText(firstAnswers.get(2).getAnswerText());
        if(firstAnswers.get(3)!=null)
            fxmlAnswer4.setText(firstAnswers.get(3).getAnswerText());
    }

    public void Answered1(){
        PassToNextQuestion(1);
    }
    public void Answered2(){
        PassToNextQuestion(2);
    }
    public void Answered3(){
        PassToNextQuestion(3);
    }
    public void Answered4(){
        PassToNextQuestion(4);
    }

    public void undoQuestion(){
        fxmlPastQuestionList.getItems().remove(questions.pop());

        if(questions.empty())
            fxmlUndoButton.setVisible(false);
    }

    public void PassToNextQuestion(int answerNum){
        fxmlPastQuestionList.getItems().add(theStory.getCurrQuestion());
        questions.push(theStory.getCurrQuestion());
        theStory.toNextQuestion(answerNum);
        fxmlQuestionText.setText(theStory.getCurrQuestion().getQuestionText());
        ArrayList<Answer> firstAnswers=theStory.getFirstQuestion().GetAnswers();
        fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
        fxmlAnswer2.setText(firstAnswers.get(1).getAnswerText());
        fxmlAnswer3.setText(firstAnswers.get(2).getAnswerText());
        fxmlAnswer4.setText(firstAnswers.get(3).getAnswerText());
        fxmlUndoButton.setVisible(true);
    }

    /**
     * Prints the text in 'last question' as result of answer path.
     */
    public void finish(){
        theStory.showQuestion();
    }
}