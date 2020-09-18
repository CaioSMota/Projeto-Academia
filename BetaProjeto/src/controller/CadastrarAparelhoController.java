package controller;

import conn.ProfessorDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class CadastrarAparelhoController implements Initializable {

    @FXML
    private TextField txtNomeAparelho;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void Cadastrar(ActionEvent e) throws SQLException {
        Stage stagep = (Stage) txtNomeAparelho.getScene().getWindow();
        if (txtNomeAparelho.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campo em branco", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String nomeApa = txtNomeAparelho.getText();
                ProfessorDAO pdao = new ProfessorDAO();
                pdao.cadastrarAparelho(nomeApa);
                JOptionPane.showMessageDialog(null, "Cadastrado Com Sucesso!");
                stagep.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha no Cadastro!");
            }
        }

    }

    public void LimparCampos(ActionEvent e) {
        txtNomeAparelho.setText("");
    }

}
