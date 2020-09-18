package main;

import controller.PrincipalProfessorController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Professor;

public class TelaProfessor extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    static Stage stage;

    public void start(Stage stage, Professor p) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        root = loader.load(getClass().getResource("/gui/PrincipalProfessor.fxml").openStream());
        PrincipalProfessorController pcont = (PrincipalProfessorController) loader.getController();
        pcont.setProf(p);
        stage.setTitle("Tela professor");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        //pcont.setData();
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TelaProfessor.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

}
