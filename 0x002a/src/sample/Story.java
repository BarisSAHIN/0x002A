package sample;

public class Story {



    private BinarySearchTree<Question> QuestionSearchTree = null;//For binary search tree
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

    public boolean isEnd(){
        return currQuestion.isEnd();
    }
    public void showQuestion(){

        currQuestion.showQuestion();

    }
    public boolean isAnswerLegal(String Answer){
        return false;
    }
    public void addQuestion(){



    }

    public Question getCurrQuestion() {
        return currQuestion;
    }

    public Question getFirtQuestion() {
        return firtQuestion;
    }
    public Question getByID(int ıd){
        return currQuestion;//bu geçici düzeltecem bunu
    }
}
