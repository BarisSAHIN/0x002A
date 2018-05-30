package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

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
        Object[] options = {"Maker", "Player"};
        switch(JOptionPane.showOptionDialog(null, "Starting app as a", "Starter",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]))
        {
            case 0:
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Maker_Frame nlt = new Maker_Frame();
                f.add(nlt);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                break;
            case 1:

                break;
            default:
                System.err.println("Option error!");
                System.exit(-1);
        }

        try {
            //Player a = new Player();
            //a.play();
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
