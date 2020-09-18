package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class Main extends Application {

    Stage stage = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.getIcons().add(new Image("/imgs/logo3.png"));
        stage.setResizable(false);
        stage.show();
    }
}
