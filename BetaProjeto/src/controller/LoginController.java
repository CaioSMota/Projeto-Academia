package controller;

import conn.AlunoDAO;
import conn.ProfessorDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import main.TelaAluno;
import main.TelaProfessor;
import models.Aluno;
import models.Professor;

public class LoginController
        implements Initializable {

    AlunoDAO conA = new AlunoDAO();
    ProfessorDAO conP = new ProfessorDAO();
    Image logo = new Image("/imgs/logoSemFundo.png");
    Image back = new Image("/imgs/acad2.jpg");
    boolean logar;

    @FXML
    private ImageView imgViewLogin;
    @FXML
    private ImageView imgViewBackLogin;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField passSenha;
    @FXML
    private Button btnLogar;

    Aluno al = new Aluno();
    Professor pr = new Professor();
    PrincipalProfessorController pController = new PrincipalProfessorController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imgViewLogin.setImage(logo);
        imgViewBackLogin.setImage(back);
    }

    @FXML
    public void ActionLogar(ActionEvent e) throws IOException, SQLException {
        logar = (conA.logarAluno(txtUser.getText(), passSenha.getText()));
        if (logar) {
            al = conA.selectAluno(txtUser.getText(), passSenha.getText());
            TelaAluno a = new TelaAluno();
            Stage stagep = (Stage) txtUser.getScene().getWindow();
            a.start(new Stage(), al);
            stagep.close();

        } else if (!logar) {
            logar = (conP.logarProf(txtUser.getText(), passSenha.getText()));
            if (logar) {
                pr = conP.selectProf(txtUser.getText(), passSenha.getText());
                TelaProfessor p = new TelaProfessor();
                Stage stagep = (Stage) txtUser.getScene().getWindow();
                p.start(new Stage(), pr);
                stagep.close();
            } else {
                JOptionPane.showMessageDialog(null, "Usuario ou senha errado!");
            }
        }
    }
}
