package main;

import controller.PrincipalAlunoController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Aluno;

public class TelaAluno extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Stage stage;

    public void start(Stage stage, Aluno a) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        root = loader.load(getClass().getResource("/gui/PrincipalUsuario.fxml").openStream());
        PrincipalAlunoController acont = (PrincipalAlunoController) loader.getController();
        acont.setAluno(a);
        stage.setTitle("Tela Aluno");
        stage.setScene(new Scene(root));

        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TelaAluno.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

}
