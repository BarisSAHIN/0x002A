package sample;

public class Story {

    private Question rootForSearch = null;
    private Question firtQuestion = null;
    private CStats GameChar = null;
    private Question currQuestion = null;
    public Story(String fileName){

        initializeStory(fileName);

    }

    public void initializeStory(String fileName){
        //Verilen file'dan initialize et
        return;
    }
    public void saveStory(){

    }
    public boolean isFinish(){
        return currQuestion.isEnd();
    }
    public void showQuestion(){

        currQuestion.showQuestion();

    }
    public boolean ifLegalGetNextQuestion(String Answer){
        return false;
    }

}
