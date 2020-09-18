package main;

import controller.AlterarDadosController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Aluno;

public class AtualizaAluno extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private static Stage stage;

    public void start(Stage stage, Aluno a) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        root = loader.load(getClass().getResource("/gui/AlterarDados.fxml").openStream());
        AlterarDadosController aAltera = (AlterarDadosController) loader.getController();
        aAltera.setAlunoAtt(a);
        stage.setTitle("Atualiza Aluno");
        stage.setScene(new Scene(root));
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AtualizaAluno.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

}
