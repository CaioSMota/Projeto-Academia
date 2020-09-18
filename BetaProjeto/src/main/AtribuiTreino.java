package main;

import controller.AtribuiTreinoController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AtribuiTreino extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static Stage stage;

    public void start(Stage stage, int id) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        root = loader.load(getClass().getResource("/gui/AtribuiTreino.fxml").openStream());
        AtribuiTreinoController atrTreino = (AtribuiTreinoController) loader.getController();
        atrTreino.setAtribuiTreino(id);
        stage.setTitle("Atribui Treino");
        stage.setScene(new Scene(root));
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        AtribuiTreino.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    }

}
