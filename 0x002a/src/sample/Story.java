package sample;

public class Story {


    private BinarySearchTree<Question> QuestionSearchTree = null;//For binary search tree
    private Question firstQuestion = null;//For flow of story
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

    public Question getByID(int ıd){
        return currQuestion;//bu geçici düzeltecem bunu
    }
}
