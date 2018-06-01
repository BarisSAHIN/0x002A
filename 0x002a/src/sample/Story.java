package sample;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class Story {


    private BinarySearchTree<Question> QuestionSearchTree = null;//For binary search tree
    private Question firstQuestion = null;//For flow of story
    private CStats GameChar = null;
    private CStats GameCharCurr = null;
    private Question currQuestion = null;
    private PriortyQueueQuestion printArray = new PriortyQueueQuestion();
    private Stack<Question> undoQues;
    private int QuestionCount = 0;
    public Story(String fileName) throws IOException, IDNotAllowed {
        QuestionSearchTree = new BinarySearchTree<>();
        initializeStory(fileName);
        currQuestion=firstQuestion;
        undoQues=new Stack<>();
    }

    public void initializeStory(String fileName) throws IOException, IDNotAllowed {

        FileReader readStory = new FileReader(fileName);
        String bufferString;
        boolean firstFlag = true;
        boolean cstatFlag = true;
        StringBuilder parsedString = new StringBuilder();

        BufferedReader readerStory = new BufferedReader(readStory);

        while((bufferString = readerStory.readLine()) != null){
            if(cstatFlag){
                GameChar = new CStats(bufferString);
                cstatFlag = false;
            }
            else{
                System.out.printf("%s\n", bufferString);
                Question localCurrQuestion = new Question(bufferString);
                if(!firstFlag)
                {
                    QuestionSearchTree.add(localCurrQuestion);
                    ++QuestionCount;
                }
                else{
                    ++QuestionCount;
                    QuestionSearchTree.add(localCurrQuestion);
                    firstQuestion = localCurrQuestion;
                    firstFlag=false;
                }

            }


        }
        connector();



        return;
    }
    private void connector() throws IDNotAllowed {
        for(int i = 0;i<QuestionCount;i++){
            Question searchThis = new Question(i);
            Question temp = null;
            temp = QuestionSearchTree.search(searchThis);
            if(temp!=null){
                for(int j=0;j<temp.GetAnswers().size();j++){
                    searchThis = new Question(temp.GetAnswers().get(j).destinationID);
                    Question temp2 = QuestionSearchTree.search(searchThis);
                    if(temp2!=null){
                        temp.GetAnswers().get(j).setNextQuestion(temp2);
                    }
                }
            }
        }
        Question searchThis = new Question(4);
        System.out.println("This: " + QuestionSearchTree.search(searchThis).GetAnswers().size());
        System.out.println("This 2: " + QuestionCount);

    }

    public boolean isEnd(){
        if(currQuestion!=null)
            return currQuestion.isEnd();
        return true;
    }
    public void showQuestion(){

        currQuestion.showQuestion();

    }
    public void showQuestionAndAnswer(){
        currQuestion.showQuestionAndAnswer();
    }




    // SOON
//    /**
//     * Oyun içerisinde soruların erişim için ön şartları olabiliyor. Bu ön şartların karşılanıp karşılanmadığını kontrol eden method
//     * @param currQuestion Test edilecek soru objesi
//     * @return  Erişilebiliyorsa True, erişilemiyorsa False
//     */
//    public boolean isQuestionReachable(Question currQuestion){
//       return  GameChar.canAccess(currQuestion.getPreRequisite());
//    }



    public void toNextQuestion(int parameter){
        System.out.println("---------- "+parameter);
        undoQues.push(currQuestion);
        currQuestion=currQuestion.GetAnswers().get(parameter-1).GetNextQuestion();
        GameCharCurr = GameChar;
        if (currQuestion==null)
            System.out.println(parameter+ "heleley");
        if(!currQuestion.getPreRequisite().isEmpty()){
            GameChar.updateAllStats(currQuestion.getPreRequisite());
        }
    }
    /*
     *
     *
     *
     *
     *
     * */
    public ArrayList<Answer> legalAnswers(){
        ArrayList<Answer> result = new ArrayList<>();
        for (int i=0;i<currQuestion.GetAnswers().size();i++){
            if(!currQuestion.GetAnswers().get(i).getStatsToBeChanged().isEmpty()){
                if(GameChar.canAccess(currQuestion.GetAnswers().get(i).getStatsToBeChanged()))
                    result.add(currQuestion.GetAnswers().get(i));
                else;

            }
            else{
                result.add(currQuestion.GetAnswers().get(i));
            }
        }
        return result;
    }
    /*
     *
     *
     *
     *
     * */
    public Question undo(){
        currQuestion=undoQues.pop();
        GameChar = GameCharCurr;
        return currQuestion;
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

    private void initializePrint(){
        ArrayList<Question> Queue=new ArrayList<>();
        int i=1;
        Question current = null;
        if(firstQuestion!=null){
            Queue.add(firstQuestion);
            printArray.add(firstQuestion,i);
            i++;
            while(Queue.size()!=0){
                current = Queue.get(0);
                current.setVisited(true);
                if(current.GetAnswers()!=null){
                    for (int j=0;j<current.GetAnswers().size();j++){
                        if(!current.isVisited()) {
                            printArray.add(current.GetAnswers().get(j).GetNextQuestion(), i);
                            Queue.add(current.GetAnswers().get(j).GetNextQuestion());
                        }
                    }
                }
                i++;
                Queue.remove(0);
            }
        }
    }

    public CStats getGameChar() {
        return GameChar;
    }
}
