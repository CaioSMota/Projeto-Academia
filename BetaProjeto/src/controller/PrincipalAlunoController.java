package controller;

import conn.AlunoDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.AtualizaAluno;
import models.Aluno;
import models.Suplemento;
import models.TreinoAluno;

public class PrincipalAlunoController implements Initializable {

    Aluno a = new Aluno();
    String nome;
    Image logo = new Image("/imgs/smalllogo.png");
    int id, idSel;

    @FXML
    private Label lblNome;
    @FXML
    private Label lblDataNasc;
    @FXML
    private Label lblIMC;
    @FXML
    private Label lblSexo;
    @FXML
    private Label lblnomeSup;
    @FXML
    private Label lbldosaSup;
    @FXML
    private Label lbldesSup;
    @FXML
    private Label lblrepeticao;
    @FXML
    private Label lblseries;
    @FXML
    private Label lblnomeAparelho;
    @FXML
    private Label lblnomeEx;
    @FXML
    private Label lblProfessor;
    @FXML
    private ImageView imgLogo;
    @FXML
    private ImageView imgAluno;
    @FXML
    private ImageView imgEx;
    @FXML
    private ImageView imgSup;
    @FXML
    private TableView<TreinoAluno> tvTreino;
    @FXML
    private TableView<TreinoAluno> tvTreinoMenu;
    @FXML
    private TableColumn<TreinoAluno, String> treinoCol;
    @FXML
    private TableColumn<TreinoAluno, String> serieCol;
    @FXML
    private TableColumn<TreinoAluno, String> repCol;
    @FXML
    private TableColumn<TreinoAluno, String> repColMenu;
    @FXML
    private TableColumn<TreinoAluno, String> seriesColMenu;
    @FXML
    private TableColumn<TreinoAluno, String> exColMenu;
    @FXML
    private TableColumn<TreinoAluno, String> exerCol;
    @FXML
    private TableColumn<TreinoAluno, String> aparelhoCol;
    @FXML
    private TableColumn<TreinoAluno, String> anotCol;
    @FXML
    private Tab treinosAba;
    @FXML
    private Tab suplementoAba;
    @FXML
    private TableView<Suplemento> tvSup;
    @FXML
    private TableColumn<Suplemento, String> descCol;
    @FXML
    private TableColumn<Suplemento, String> nomeSupCol;
    @FXML
    private TableColumn<Suplemento, String> dosagemCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    //Set Informações Aluno
    public void setAluno(Aluno a) {
        this.a = a;
        System.out.println(a.getImgAluno());
        Image imgAl = new Image(a.getImgAluno());
        lblNome.setText(a.getpNomeAluno() + " " + a.getuNomeAluno());
        lblDataNasc.setText(a.getDataNascAluno());
        lblIMC.setText(a.getImc());
        lblSexo.setText(a.getSexoAluno());
        imgLogo.setImage(logo);
        imgAluno.setImage(imgAl);
        id = a.getIdAluno();
        initTableTreinoMenu();
    }

    public void clickTreinoAl() {
        initTableTreino();
    }

    public void clickSuplemento() {
        initTableSup();
    }

    @FXML
    public void VoltarLogin(ActionEvent e) throws IOException {

        Stage stage = (Stage) lblDataNasc.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();

    }

    @FXML
    public void AlterarDados(ActionEvent e) throws IOException {
        AtualizaAluno attAl = new AtualizaAluno();
        attAl.start(new Stage(), a);
    }

    public void initTableTreino() {
        serieCol.setCellValueFactory(new PropertyValueFactory("serie"));
        treinoCol.setCellValueFactory(new PropertyValueFactory("treino"));
        repCol.setCellValueFactory(new PropertyValueFactory("repeticoes"));
        exerCol.setCellValueFactory(new PropertyValueFactory("exercicio"));
        aparelhoCol.setCellValueFactory(new PropertyValueFactory("aparelho"));
        anotCol.setCellValueFactory(new PropertyValueFactory("anotacao"));
        tvTreino.setItems(treinoAluno());
    }

    public void initTableTreinoMenu() {
        seriesColMenu.setCellValueFactory(new PropertyValueFactory("serie"));
        repColMenu.setCellValueFactory(new PropertyValueFactory("repeticoes"));
        exColMenu.setCellValueFactory(new PropertyValueFactory("exercicio"));
        tvTreinoMenu.setItems(treinoAluno());
    }

    public void initTableSup() {
        descCol.setCellValueFactory(new PropertyValueFactory("descSuplemento"));
        nomeSupCol.setCellValueFactory(new PropertyValueFactory("nomeSuplemento"));
        dosagemCol.setCellValueFactory(new PropertyValueFactory("dosagemSuplemento"));
        tvSup.setItems(suplemento());
    }

    public ObservableList<TreinoAluno> treinoAluno() {
        AlunoDAO dao = new AlunoDAO();
        return FXCollections.observableArrayList(dao.getTreinoAlunos(id));
    }

    public ObservableList<Suplemento> suplemento() {
        AlunoDAO dao = new AlunoDAO();
        return FXCollections.observableArrayList(dao.getSuplementos());
    }

    public void SelectRowSup() {
        AlunoDAO dao = new AlunoDAO();
        int id = tvSup.getSelectionModel().getSelectedItem().getIdSuplemento();
        Suplemento sup = new Suplemento();

        sup = dao.getSuplementoClick(id);
        Image imgSu = new Image(sup.getImgSuplemnto());

        lblnomeSup.setText(sup.getNomeSuplemento());
        lbldosaSup.setText(sup.getDosagemSuplemento());
        imgSup.setImage(imgSu);
    }

    public void SelectRowTreino() {
        AlunoDAO dao = new AlunoDAO();
        TreinoAluno TrAl = new TreinoAluno();

        String id = tvTreino.getSelectionModel().getSelectedItem().getIdTreino();
        TrAl = dao.getTreinoAlunosClick(Integer.parseInt(id));
        Image imgExe = new Image(TrAl.getImgEx());

        lblrepeticao.setText(TrAl.getRepeticoes());
        lblseries.setText(TrAl.getSerie());
        lblnomeAparelho.setText(TrAl.getAparelho());
        lblnomeEx.setText(TrAl.getExercicio());
        imgEx.setImage(imgExe);
    }
}
