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

    /*driver*/
    public static void main(String[] args) {
        //launch(args); //TODO JAVAFX
        try {
            sample.Application app = new sample.Application();
            app.mainMenu();
            System.out.println("fin");
        } catch (Exception a) {
            System.err.println(a.toString());
            System.err.println(a.getMessage());
        }

        System.exit(0);
    }
}
