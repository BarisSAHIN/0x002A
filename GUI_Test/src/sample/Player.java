package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Player extends User implements Initializable{

    private Story theStory;
    public static String pickedGameFile= "./saved";
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
            theStory=new Story(pickedGameFile + Welcome.filename);
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
        if(theStory.isEnd())
            finish();
        else {
            fxmlQuestionText.setText(theStory.getFirstQuestion().getQuestionText());
            ArrayList<Answer> firstAnswers = theStory.legalAnswers();
            if (firstAnswers.size() >= 1) {
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            } else
                fxmlAnswer1.setVisible(false);

            if (firstAnswers.size() >= 2) {
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            } else
                fxmlAnswer1.setVisible(false);

            if (firstAnswers.size() >= 3) {
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            } else
                fxmlAnswer1.setVisible(false);

            if (firstAnswers.size() == 4) {
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            } else
                fxmlAnswer1.setVisible(false);
        }
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
        theStory.undo();
        if(questions.empty())
            fxmlUndoButton.setVisible(false);

        ArrayList<Answer> firstAnswers=theStory.legalAnswers();
        if(firstAnswers.size()>=1){
            fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
            fxmlAnswer1.setVisible(true);
        }
        else
            fxmlAnswer1.setVisible(false);

        if(firstAnswers.size()>=2){
            fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
            fxmlAnswer1.setVisible(true);
        }
        else
            fxmlAnswer1.setVisible(false);

        if(firstAnswers.size()>=3){
            fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
            fxmlAnswer1.setVisible(true);
        }
        else
            fxmlAnswer1.setVisible(false);

        if(firstAnswers.size()==4){
            fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
            fxmlAnswer1.setVisible(true);
        }
        else
            fxmlAnswer1.setVisible(false);
    }

    public void PassToNextQuestion(int answerNum){
        fxmlPastQuestionList.getItems().add(theStory.getCurrQuestion());
        questions.push(theStory.getCurrQuestion());
        theStory.toNextQuestion(answerNum);
        if(theStory.isEnd())
            finish();
        else{
            fxmlQuestionText.setText(theStory.getCurrQuestion().getQuestionText());
            ArrayList<Answer> firstAnswers=theStory.legalAnswers();
            if(firstAnswers.size()>=1){
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            }
            else
                fxmlAnswer1.setVisible(false);

            if(firstAnswers.size()>=2){
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            }
            else
                fxmlAnswer1.setVisible(false);

            if(firstAnswers.size()>=3){
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            }
            else
                fxmlAnswer1.setVisible(false);

            if(firstAnswers.size()==4){
                fxmlAnswer1.setText(firstAnswers.get(0).getAnswerText());
                fxmlAnswer1.setVisible(true);
            }
            else
                fxmlAnswer1.setVisible(false);
            fxmlUndoButton.setVisible(true);
        }
    }


    public void finish(){
        theStory.getCurrQuestion().getQuestionText();
    }
}