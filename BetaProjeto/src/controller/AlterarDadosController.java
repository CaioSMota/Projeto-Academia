package controller;

import conn.AlunoDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Aluno;

public class AlterarDadosController implements Initializable {

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtProblema;

    @FXML
    private TextField txtAltura;

    @FXML
    private PasswordField passSenha;

    int idA;
    Aluno al = new Aluno();
    String user, pass, senha, tel, problema;
    float peso, altura;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void LimparCampos(ActionEvent e) {
        txtAltura.setText("");
        txtPeso.setText("");
        passSenha.setText("");
        txtTel.setText("");
        txtProblema.setText("");
    }

    public void setAlunoAtt(Aluno a) {
        idA = a.getIdAluno();
        txtPeso.setText(Double.toString(a.getPesoAluno()));
        txtAltura.setText(Double.toString(a.getAlturaAluno()));
        passSenha.setText(a.getSenha());
        txtTel.setText(a.getTelefoneAluno());
        txtProblema.setText(a.getProblemaSaude());
        user = a.getUsuarioAluno();
        pass = a.getSenha();
    }

    @FXML
    public void SalvarDados(ActionEvent e) throws SQLException {
        if (txtAltura.getText().equals("") || txtPeso.getText().equals("") || txtProblema.getText().equals("") || txtTel.getText().equals("") || passSenha.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos em branco.\n\tSe não for alterar deixe o atual");
        } else {
            senha = passSenha.getText();
            tel = txtTel.getText();
            problema = txtProblema.getText();
            peso = Float.parseFloat(txtPeso.getText());
            altura = Float.parseFloat(txtAltura.getText());
        }
        try {
            AlunoDAO dao = new AlunoDAO();
            dao.atualizaAluno(idA, peso, altura, senha, tel, problema);

            al = dao.selectAluno(user, pass);

            PrincipalAlunoController aluno = new PrincipalAlunoController();
            System.out.println(al.getpNomeAluno());

            aluno.setAluno(al);
            
            Stage stage = (Stage) txtAltura.getScene().getWindow();
            stage.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
