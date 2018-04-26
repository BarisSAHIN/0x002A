package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

    }


    public static void main(String[] args) {
        // sample.Application a = new sample.Application();


        //launch(args); //TODO JAVAFX
        try {
            Story temp = new Story("test1");
/*            Scanner file = new Scanner(new FileInputStream("./saved/test1"));
            while (file.hasNextLine()) {
                String k = file.nextLine();
                System.out.println(k);
            }
 */       } catch (Exception a) {
            System.err.println(a.toString());
        }
            System.exit(0);
    }
}
