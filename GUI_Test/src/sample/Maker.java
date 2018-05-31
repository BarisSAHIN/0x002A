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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

//ÖNEMLİ NOT : UI ile ilgili metodları içermemektedir. Şu anlık sadece, yeni bir Question ve Answer yapımını sağlamaktadır.
//Verilen Answer ve Questionlar ile bir Hashtable oluşturup, onları istenilen dosya moduna çekmedtedir.
/**
 * This class is used by Application class to create a new story.
 * With this class you can add new questions, answers to your story and save your story for others to play.
 **/
public class Maker extends User implements Initializable {

    //Class Variables
    HashMap<Integer, Question> questions;
    HashMap<Integer, Answer> answers;
    CStats stats;
    Question selectedQuestion;
    Answer selectedAnswer;
    String gameName = "42";
    int createdQuestionNum;
    int createdAnswerNum;
    private HashMap<String, Integer> gameChar;

    //FXML Variables

    //Panes
    @FXML
    AnchorPane questionListPanel;
    @FXML
    AnchorPane answerListPanel;
    @FXML
    AnchorPane selectedQuestionPanel;
    @FXML
    AnchorPane selectedAnswerPanel;

    //Lists
    @FXML
    ListView<Question> questionsListView;
    @FXML
    ListView<Answer> answersListView;

    //Selected Question Parts
    @FXML
    Label fxmlSelectedQuestionID;
    @FXML
    TextArea fxmlSelectedQuestionText;
    @FXML
    TextField fxmlSelectedQuestionAnswerNum;
    @FXML
    ChoiceBox fxmlSelectedQuestionSideEffectBox;
    @FXML
    ChoiceBox fxmlSelectedQuestionSideEffectModifier;
    @FXML
    TextField fxmlSelectedQuestionSideEffectValue;


    //Selected Answer Parts
    @FXML
    Label fxmlSelectedAnswerID;
    @FXML
    TextArea fxmlSelectedAnswerText;
    @FXML
    TextField fxmlSelectedAnswerTargetQuestionID;
    @FXML
    ChoiceBox fxmlSelectedAnswerPreReqBox;
    @FXML
    ChoiceBox fxmlSelectedAnswerPreReqModifier;
    @FXML
    TextField fxmlSelectedAnswerPreReqValue;


    //CSTATS
    @FXML TextField fxmlStatName1;
    @FXML TextField fxmlStatName2;
    @FXML TextField fxmlStatName3;
    @FXML TextField fxmlStatDefault1;
    @FXML TextField fxmlStatDefault2;
    @FXML TextField fxmlStatDefault3;

    //Game Name
    @FXML
    TextField fxmlGameName;

    public Maker() {
        questions = new HashMap<Integer, Question>();
        answers = new HashMap<Integer, Answer>();
        stats = new CStats();
        selectedAnswer = null;
        selectedQuestion = null;
        createdAnswerNum = 0;
        createdQuestionNum = 0;
    }

    // Question Part

    /**
     * Updates selectedQuestion with given parameters.
     *
     * @throws IDNotAllowed
     * @caution fxmlSelectedQuestionAnswerNum must be smaller than 5
     */
    public void UpdateQuestion() throws IDNotAllowed {
        if (selectedQuestion != null) {
            Integer newNumOfAnswers = Integer.parseInt(fxmlSelectedQuestionAnswerNum.getText());
            if (newNumOfAnswers > 4) newNumOfAnswers = 4;
            // selectedQuestion.setPreRequisite();
            selectedQuestion.setText(fxmlSelectedQuestionText.getText());
            while (selectedQuestion.Answers.size() > newNumOfAnswers) {
                RemoveLastAnswer();
            }
            while (selectedQuestion.Answers.size() != newNumOfAnswers) {
                CreateNewAnswer();
            }

            //SideEffect section
            if(!fxmlSelectedQuestionSideEffectBox.getValue().toString().equals("None")) {
                HashMap<String,Pair<Character,Integer>> newSide = new HashMap<String, Pair<Character,Integer>>();
                Pair<Character,Integer> newPair = new Pair<Character,Integer>(fxmlSelectedQuestionSideEffectModifier.getValue().toString().charAt(0), Integer.parseInt(fxmlSelectedQuestionSideEffectValue.getText().toString()) );
                newSide.put(fxmlSelectedQuestionSideEffectBox.getValue().toString(),newPair);
                selectedQuestion.setPreRequisite(newSide);
                System.out.println(selectedQuestion.preRequisite);
            }else{
                fxmlSelectedQuestionSideEffectValue.setText("");
                fxmlSelectedQuestionSideEffectModifier.setValue("None");
                fxmlSelectedQuestionSideEffectBox.setValue("None");
                selectedQuestion.preRequisite = new HashMap<>();
            }


            questionsListView.refresh();
            answersListView.refresh();
            selectedQuestionPanel.requestLayout();
            selectedAnswerPanel.requestLayout();
        }
    }

    /**
     * Creates a new Question with default text and 0 answers.
     *
     * @throws IDNotAllowed
     */
    public void CreateNewQuestion() throws IDNotAllowed {
        int id = ++createdQuestionNum;
        Question newQ = new Question(id);
        questions.put(newQ.getId(), newQ);
        newQ.Answers.clear();

        questionsListView.getItems().add(newQ);


    }

    /**
     * Updates selectedQuestion on a Question Click from Question List
     */
    public void QuestionListButtonPushed() {
        Question selectedQuestion = questionsListView.getSelectionModel().getSelectedItem();
        SelectQuestion(selectedQuestion);
    }

    public void AnswerListButtonPressed() {
        Answer selectedAnswer = answersListView.getSelectionModel().getSelectedItem();
        SelectAnswer(selectedAnswer);
    }


    /**
     * Method for GUI usage. Changes selectedQuestion according to the mouse click
     *
     * @param selected Question that has been clicked on
     * @throws IllegalArgumentException happens only if clicked question is null
     */
    public void SelectQuestion(Question selected){
        if (selected != null) {
            selectedQuestion = selected;
            Integer noA = selectedQuestion.Answers.size();

            fxmlSelectedQuestionAnswerNum.setText(noA.toString());
            fxmlSelectedQuestionText.setText(selectedQuestion.getQuestionText());
            noA = selectedQuestion.getId();
            fxmlSelectedQuestionID.setText(noA.toString());

            //SideEffect section
            if(!selectedQuestion.preRequisite.isEmpty()) {
                System.out.println("Selection -> " + selectedQuestion.preRequisite);

                String key = "";
                if(selectedQuestion.preRequisite.get(fxmlStatName1.getText()) != null){
                    key = fxmlStatName1.getText();
                }else if(selectedQuestion.preRequisite.get(fxmlStatName2.getText()) != null){
                    key = fxmlStatName2.getText();
                }else{
                    key = fxmlStatName3.getText();
                }

                fxmlSelectedQuestionSideEffectBox.setValue(key);

                Pair<Character, Integer> qPair = selectedQuestion.getPreRequisite().get(key);
                System.out.println("Pair -> " + qPair + " Key -> " + key);

                fxmlSelectedQuestionSideEffectModifier.setValue(qPair.getKey().toString());
                fxmlSelectedQuestionSideEffectValue.setText(qPair.getValue().toString());
            }else{
                fxmlSelectedQuestionSideEffectValue.setText("");
                fxmlSelectedQuestionSideEffectModifier.setValue("None");
                fxmlSelectedQuestionSideEffectBox.setValue("None");
            }

            selectedAnswer = null;

            answersListView.getItems().clear();
            for (Answer ans : selectedQuestion.Answers) {
                answersListView.getItems().add(ans);
            }
            answersListView.refresh();

        }
    }

    /**
     * Deletes selected question.
     */
    public void DeleteQuestion() {
        if (selectedQuestion != null) {
            RemoveAllAnswers();
            questionsListView.getItems().remove(selectedQuestion);
            questions.remove(selectedQuestion.getId());
            //TODO check for answers goin for that question ?
            selectedQuestion = null;
        }

        //SelectedQuestion Update for null
        fxmlSelectedQuestionID.setText("-1");
        fxmlSelectedQuestionText.setText("Select A Question To Edit");
        fxmlSelectedQuestionAnswerNum.setText("0");

    }


    //Answer Part

    /**
     * Updates answer with given parameters.
     */
    public void UpdateAnswer() {
        if (selectedAnswer != null) {
            Integer targetQuestionID = Integer.parseInt(fxmlSelectedAnswerTargetQuestionID.getText());
            //TODO: Check for valid ID
            //TODO: Update sideEff / preReq
            // selectedQuestion.setPreRequisite();
            selectedAnswer.setAnswerText(fxmlSelectedAnswerText.getText());
            selectedAnswer.setDestinationID(targetQuestionID);


            //preReq
            //SideEffect section
            if(!fxmlSelectedAnswerPreReqBox.getValue().toString().equals("None")) {
                HashMap<String,Pair<Character,Integer>> newSide = new HashMap<String, Pair<Character,Integer>>();
                Pair<Character,Integer> newPair = new Pair<Character,Integer>(fxmlSelectedAnswerPreReqModifier.getValue().toString().charAt(0), Integer.parseInt(fxmlSelectedAnswerPreReqValue.getText().toString()) );
                newSide.put(fxmlSelectedAnswerPreReqBox.getValue().toString(),newPair);
                selectedAnswer.setStatsToBeChanged(newSide);
                System.out.println(selectedAnswer.statsToBeChanged);
            }else{
                fxmlSelectedAnswerPreReqValue.setText("");
                fxmlSelectedAnswerPreReqModifier.setValue("None");
                fxmlSelectedAnswerPreReqBox.setValue("None");
                selectedAnswer.statsToBeChanged = new HashMap<>();
            }
        }
        questionsListView.refresh();
        answersListView.refresh();
        selectedQuestionPanel.requestLayout();
        selectedAnswerPanel.requestLayout();
    }


    /**
     * Creates new answer with default text, special ID and null destionation for selectedQuestion.
     * Max Answer for a Question is 4.
     */
    public void CreateNewAnswer() {
        if (selectedQuestion != null && selectedQuestion.Answers.size() != 4) {
            int id = ++createdAnswerNum;
            Answer newA = new Answer(id, selectedQuestion.getId());
            answers.put(newA.getId(), newA);
            selectedQuestion.AddAnswer(newA);
            answersListView.getItems().add(newA);
        }
        answersListView.refresh();

        //TODO : Answer List
    }

    public void SelectAnswer(Answer selected) {
        if (selected != null) {
            selectedAnswer = selected;
            System.out.println("Selected Answer -> " + selectedAnswer);
            Integer numID = selectedAnswer.getId();
            //System.out.println("Answer ID -> " + numID);
            fxmlSelectedAnswerID.setText(numID.toString());
            fxmlSelectedAnswerText.setText(selectedAnswer.getAnswerText());
            numID = selectedAnswer.getDestinationID();
            fxmlSelectedAnswerTargetQuestionID.setText(numID.toString());

            //preReq Stuff
            //SideEffect section
            if(!selectedAnswer.statsToBeChanged.isEmpty()) {
                System.out.println("Selection -> " + selectedAnswer.statsToBeChanged);

                String key = "";
                if(selectedAnswer.statsToBeChanged.get(fxmlStatName1.getText()) != null){
                    key = fxmlStatName1.getText();
                }else if(selectedAnswer.statsToBeChanged.get(fxmlStatName2.getText()) != null){
                    key = fxmlStatName2.getText();
                }else{
                    key = fxmlStatName3.getText();
                }

                fxmlSelectedAnswerPreReqBox.setValue(key);

                Pair<Character, Integer> qPair = selectedAnswer.getStatsToBeChanged().get(key);
                System.out.println("Pair -> " + qPair + " Key -> " + key);

                fxmlSelectedAnswerPreReqModifier.setValue(qPair.getKey().toString());
                fxmlSelectedAnswerPreReqValue.setText(qPair.getValue().toString());
            }else{
                fxmlSelectedAnswerPreReqValue.setText("");
                fxmlSelectedAnswerPreReqModifier.setValue("None");
                fxmlSelectedAnswerPreReqBox.setValue("None");
            }

        }

        //TODO : Update CSTATS

    }


    /**
     * Deletes selectedAnswer from the selectedQuestion
     *
     * @post selectedAnswer becomes null
     */
    public void DeleteAnswer() {
        selectedQuestion.Answers.remove(selectedAnswer);
        answers.remove(selectedAnswer.id);
        answersListView.getItems().remove(selectedAnswer);
        answersListView.refresh();
        selectedAnswer = null;
    }

    /**
     * Removes lastAnswer from the selectedQuestion
     *
     * @return removed answer
     */
    private Answer RemoveLastAnswer() {

        answersListView.getItems().remove(selectedQuestion.Answers.size()-1);
        return selectedQuestion.Answers.remove(selectedQuestion.Answers.size() - 1);
    }

    /**
     * Removes all answers from the selectedQuestion
     */
    private void RemoveAllAnswers() {
        while (selectedQuestion.Answers.size() != 0) {
            RemoveLastAnswer();
        }
        selectedAnswer = null;
    }


    //CStats

    /**
     * Updates CStats for game Character.

     */
    public void UpdateCStats() {
        //TODO : need clear method

        fxmlSelectedQuestionSideEffectBox.getItems().clear();
        fxmlSelectedAnswerPreReqBox.getItems().clear();

        System.out.println(fxmlStatName1.getText().isEmpty());
        if(!fxmlStatName1.getText().isEmpty()){
            stats.addParam(fxmlStatName1.getText(),Integer.parseInt(fxmlStatDefault1.getText()) );
            fxmlSelectedQuestionSideEffectBox.getItems().add(fxmlStatName1.getText());
            fxmlSelectedAnswerPreReqBox.getItems().add(fxmlStatName1.getText());

        }
        if(!fxmlStatName2.getText().isEmpty()){
            stats.addParam(fxmlStatName2.getText(),Integer.parseInt(fxmlStatDefault2.getText()) );
            fxmlSelectedAnswerPreReqBox.getItems().add(fxmlStatName2.getText());
            fxmlSelectedQuestionSideEffectBox.getItems().add(fxmlStatName2.getText());

        }
        if(!fxmlStatName3.getText().isEmpty()){
            stats.addParam(fxmlStatName3.getText(),Integer.parseInt(fxmlStatDefault3.getText()) );
            fxmlSelectedQuestionSideEffectBox.getItems().add(fxmlStatName3.getText());
            fxmlSelectedAnswerPreReqBox.getItems().add(fxmlStatName3.getText());
        }

        //TODO: Change all Question and Answer cstats.
        fxmlSelectedQuestionSideEffectBox.getItems().add( "None");
        fxmlSelectedAnswerPreReqBox.getItems().add( "None");



    }

    /**
     * Returns the stat name for given index
     *
     * @param i index for stat name
     * @return Required stat name
     */
    public String getStatName(int i) {
        return (String) stats.getStats().toArray()[i];
    }


    public int currentNumOfQuestions() {
        return questions.size();
    }

    public int currentNumOfAnswers() {
        return answers.size();
    }

    public int numOfDifferentEndings() {
        int endings = 0;
        for (int i = 0; i < createdQuestionNum; ++i) {
            if (questions.containsKey(i)) {
                if (questions.get(i).Answers.size() == 0) {
                    ++endings;
                }
            }
        }
        return endings;
    }

    public void SetGameName() {
        gameName = fxmlGameName.getText();
    }

    public String GetGameName() {
        return gameName;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        fxmlSelectedQuestionSideEffectModifier.getItems().clear();
        fxmlSelectedQuestionSideEffectModifier.getItems().addAll("+","-","*","=", "None");

        fxmlSelectedAnswerPreReqModifier.getItems().clear();
        fxmlSelectedAnswerPreReqModifier.getItems().addAll("<","=",">","None");

        fxmlSelectedQuestionSideEffectBox.getItems().clear();
        fxmlSelectedQuestionSideEffectBox.getItems().add("None");

        fxmlSelectedAnswerPreReqBox.getItems().clear();
        fxmlSelectedAnswerPreReqBox.getItems().add("None");


        System.out.print(fxmlSelectedAnswerText.getPrefColumnCount());

        fxmlGameName.setText("42");


    }


    /**
     * Saves the created story in a file to share with players.
     */
    //Yaratılan oyun senaryosunu, daha sonra oynanabilmesi için dosya olarak kaydeder.
    public void save() throws IOException {
        //TODO: Son belirlenen formata göre kontrol edilmeli.

        File saveFile = new File(gameName + ".txt");

        saveFile.delete();
        saveFile.createNewFile();

        FileWriter fileWriter = new FileWriter(saveFile);

        //Cstat saving.
        /*fileWriter.append(stats);
        fileWriter.flush();
*/
        //Quesiton & Answer saving
        Object[] questionIDS =  questions.keySet().toArray();
        System.out.println("question ID length -> " + questionIDS.length);
        for (Object i : questionIDS) {
            System.out.println(" Save i -> " + i);
            fileWriter.append(questions.get(questionIDS[(Integer)i-1]).toString());
            fileWriter.append("\n");
            fileWriter.flush();
        }

    }
}