package sample;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Story {


    private BinarySearchTree<Question> QuestionSearchTree = null;//For binary search tree
    private Question firstQuestion = null;//For flow of story
    private CStats GameChar = null;
    private Question currQuestion = null;

    public Story(String fileName) throws IOException {

        initializeStory(fileName);

    }

    public void initializeStory(String fileName) throws IOException {

        FileReader readStory = new FileReader(fileName);
        String bufferString;
        boolean firstFlag = true;
        StringBuilder reset = new StringBuilder();
        StringBuilder parsedString = new StringBuilder();

        BufferedReader readerStory = new BufferedReader(readStory);

        while((bufferString = readerStory.readLine()) != null){
            parsedString.append(bufferString);
            if(bufferString.contains("(")){

                Question localCurrQuestion = new Question(parsedString.toString());
                parsedString = reset;
                if(!firstFlag)
                QuestionSearchTree.add(localCurrQuestion);
                else{
                    firstQuestion = localCurrQuestion;
                    firstFlag=false;
                }
            }
            else
                parsedString.append("\n");
        }




        return;
    }
    private void connector(Question connection) throws IDNotAllowed {
        ArrayList<Answer> answers= connection.GetAnswers();
        int i = 0;
        if(answers==null)
            return;
        while(i<answers.size()){
            Integer  a = connection.GetAnswers().get(i).nextQuestionID;
            Question searchQuestion = new Question(a);
            answers.get(i).setNextQuestion(QuestionSearchTree.search(searchQuestion));
            connector(answers.get(i).GetNextQuestion());
        }
    }
    public void saveStory(){

    }

    public boolean isEnd(){
        return currQuestion.isEnd();
    }
    public void showQuestion(){

        currQuestion.showQuestion();

    }
    public void showQuestionAndAnswer(){
        currQuestion.showQuestionAndAnswer();
    }
    public boolean isAnswerLegal(String Answer){

        return false;
    }

    /**
     * Oyun içerisinde soruların erişim için ön şartları olabiliyor. Bu ön şartların karşılanıp karşılanmadığını kontrol eden method
     * @param currQuestion Test edilecek soru objesi
     * @return  Erişilebiliyorsa True, erişilemiyorsa False
     */
    public boolean isQuestionReachable(Question currQuestion){
       return  GameChar.canAccess(currQuestion.getPreRequisite());
    }


    public void addQuestion(){

    }

    public Question getCurrQuestion() {
        return currQuestion;
    }

    public Question getFirstQuestion() {
        return firstQuestion;
    }

    public Question getByID(int ıd) throws IDNotAllowed {
        Question searchQuestion = new Question(ıd);
        return QuestionSearchTree.search(searchQuestion);
    }
}
