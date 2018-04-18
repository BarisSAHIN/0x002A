package sample;

public class Story {

    private BinarySearchTree<Question> rootForSearch = null;//For binary search tree
    private Question firtQuestion = null;//For flow of story
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
    public boolean isAnswerLegal(String Answer){
        return false;
    }

}
