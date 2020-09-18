package controller;

import conn.ProfessorDAO;
import conn.TreinoDAO;
import conn.TreinoExercicioDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import models.Aluno;
import models.Treino;

public class AtribuiTreinoController implements Initializable {

    int idP;
    String nomeAl;
    @FXML
    private TableView<Aluno> tvAlunos;
    @FXML
    private TableColumn<Aluno, String> nomeAlunoCol;
    @FXML
    private TableColumn<Aluno, String> telAlunoCol;
    @FXML
    private ComboBox comboTreino;
    @FXML
    private TextField txtPesquisa;

    public void setAtribuiTreino(int id) {
        idP = id;
        initTableAlunosProf();
        setData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void pesquisarAluno(ActionEvent e) {
        nomeAl = txtPesquisa.getText();
        if (nomeAl == "") {
            initTableAlunosProf();
        } else {
            initTableAlunosProfPesquisa();
        }
    }

    public void atribuirTreino(ActionEvent e) throws SQLException {
        TreinoDAO dao = new TreinoDAO();

        TreinoExercicioDAO treinoExDAO = new TreinoExercicioDAO();
        int idAl = tvAlunos.getSelectionModel().getSelectedItem().getIdAluno();
        String treino = comboTreino.getSelectionModel().getSelectedItem().toString();
        int idT = treinoExDAO.returnID(treino);

        System.out.println(idAl + " " + idT);

        boolean alTreino;

        alTreino = dao.getTreinoAluno(idAl);

        if (alTreino) {

            dao.alterarTreino(idAl, idT);
            JOptionPane.showMessageDialog(null, "Treino do Aluno foi Alterado");
        } else {

            dao.atribuiTreino(idAl, idT);
            JOptionPane.showMessageDialog(null, "Treino do Aluno foi Adicionado");
        }

        Stage stagep = (Stage) txtPesquisa.getScene().getWindow();
        stagep.close();
    }

    public void setData() {
        comboTreino.getItems().clear();

        List<Treino> treino = new ArrayList<>();
        TreinoDAO treinoDAO = new TreinoDAO();
        treino = treinoDAO.returnTreinos(idP);

        for (int j = 0; j < treino.size(); j++) {
            Treino tr = new Treino();
            tr = treino.get(j);
            comboTreino.getItems().add(tr.getNomeTreino());
        }

    }

    public void initTableAlunosProf() {
        nomeAlunoCol.setCellValueFactory(new PropertyValueFactory("nomeCompleto"));
        telAlunoCol.setCellValueFactory(new PropertyValueFactory("telefoneAluno"));
        tvAlunos.setItems(listaAlunosProf());
    }

    public void initTableAlunosProfPesquisa() {
        nomeAlunoCol.setCellValueFactory(new PropertyValueFactory("nomeCompleto"));
        telAlunoCol.setCellValueFactory(new PropertyValueFactory("telefoneAluno"));
        tvAlunos.setItems(listaAlunosProfPesquisa());
    }

    public ObservableList<Aluno> listaAlunosProf() {
        ProfessorDAO dao = new ProfessorDAO();
        return FXCollections.observableArrayList(dao.getTodosAlunosProf(idP));
    }

    public ObservableList<Aluno> listaAlunosProfPesquisa() {
        ProfessorDAO dao = new ProfessorDAO();
        return FXCollections.observableArrayList(dao.getBuscarAlunosProf(nomeAl, idP));
    }

}
