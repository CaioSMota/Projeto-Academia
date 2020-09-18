package controller;

import conn.AlunoDAO;
import conn.ProfessorDAO;
import conn.TreinoDAO;
import conn.TreinoExercicioDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import main.CadastraAluno;
import main.AtribuiTreino;
import models.Aluno;
import models.Exercicio;
import models.Professor;
import models.Treino;
import models.TreinoAluno;

public class PrincipalProfessorController implements Initializable {

    @FXML
    private Label lblNome;
    @FXML
    private Label lblSexo;
    @FXML
    private Label lblArea;
    @FXML
    private TableView<Aluno> tvAlunos;
    @FXML
    private TableColumn<Aluno, String> nomeCol;
    @FXML
    private TableColumn<Aluno, String> telefoneCol;
    @FXML
    private TableView<Aluno> tvAlunosFull;
    @FXML
    private TableView<TreinoAluno> tvVerTreino;
    @FXML
    private TableColumn<TreinoAluno, String> anotColVer;
    @FXML
    private TableColumn<TreinoAluno, String> repColVer;
    @FXML
    private TableColumn<TreinoAluno, String> serieColVer;
    @FXML
    private TableColumn<TreinoAluno, String> exColVer;
    @FXML
    private TableColumn<Aluno, String> nomeAlCol;
    @FXML
    private TableColumn<Aluno, String> sexoAlCol;
    @FXML
    private TableColumn<Aluno, String> obAlCol;
    @FXML
    private TableColumn<Aluno, String> telAlCol;
    @FXML
    private TableColumn<Aluno, String> saudeAlCol;
    @FXML
    private TableColumn<Aluno, String> imcAlCol;
    @FXML
    private TableView<Exercicio> tvExercicio;
    @FXML
    private TableColumn<Exercicio, String> nomeExCol;
    @FXML
    private TableColumn<Exercicio, String> aparelhoCol;
    @FXML
    private ComboBox combSexo;
    @FXML
    private ComboBox combArea;
    @FXML
    private ComboBox combbReps;
    @FXML
    private ComboBox combbSeries;
    @FXML
    private ComboBox combbTreino;
    @FXML
    private ComboBox combSelectTreino;
    @FXML
    private ImageView imgProf;
    @FXML
    private ImageView imgProfCadastro;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextArea areaAnotacoes;
    @FXML
    private TextField txtArea;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtTreino;
    @FXML
    private DatePicker dateDataNasc;
    @FXML
    private Tab cadastraProf;
    @FXML
    private TextField txtBusca;

    Image i = new Image("/imgs/image.jpg");
    String path = null, absolutPath = null;
    String nomeAl = null;
    Professor p = new Professor();
    int id, idSelect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTableAlunos();
        imgProfCadastro.setImage(i);
    }

    //ComboBox
    ObservableList<String> sexo
            = FXCollections.observableArrayList(
                    "F",
                    "M"
            );
    ObservableList<String> reps
            = FXCollections.observableArrayList(
                    "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"
            );
    ObservableList<String> series
            = FXCollections.observableArrayList(
                    "1", "2", "3", "4", "5"
            );
    ObservableList<String> areas
            = FXCollections.observableArrayList(
                    "Musculacao", "Aerobico", "BodyBuilding"
            );

    //Set Informações Professor
    public void setProf(Professor prof) {
        this.p = prof;
        String teste = prof.getImgProf();
        try {
            Image imgProfi = new Image(prof.getImgProf());
            lblNome.setText(p.getpNomeProf() + " " + p.getuNomeProf());
            lblSexo.setText(p.getSexoProf());
            lblArea.setText(p.getArea());
            id = p.getIdProf();
            imgProf.setImage(imgProfi);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar dados do banco", "Aviso", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        if (id != 1) {
            cadastraProf.setDisable(true);
        }
    }

    public void cadastroProf(ActionEvent e) {
        if (combArea.getSelectionModel().getSelectedIndex() < 0 || txtNome.getText().isEmpty() || txtSenha.getText().isEmpty() || txtSobrenome.getText().isEmpty()
                || txtTelefone.getText().isEmpty() || txtUser.getText().isEmpty() || dateDataNasc.getValue() == null || imgProfCadastro.getImage().equals(i)) {
            JOptionPane.showMessageDialog(null, "Campo em branco", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String pnome = txtNome.getText();
                String unome = txtSobrenome.getText();
                LocalDate nasc = dateDataNasc.getValue();
                String tel = txtTelefone.getText();
                String sx = combSexo.getSelectionModel().getSelectedItem().toString();
                String area = combArea.getSelectionModel().getSelectedItem().toString();
                System.out.println(area);
                String usuario = txtUser.getText();
                String senha = txtSenha.getText();
                
                path = txtNome.getText() + txtSobrenome.getText() + ".jpg";
                String img = "/imgsProf/" + path;
                File imgPath = new File(absolutPath);
                BufferedImage bImage = ImageIO.read(imgPath);

                ImageIO.write(bImage, "jpg", new File("C:\\Users\\CAIO\\Desktop\\LP3\\BetaProjeto\\src\\imgsProf\\" + path));
                ProfessorDAO dao = new ProfessorDAO();
                dao.cadastrarProf(pnome, unome, nasc.toString(), tel, sx, area, usuario, senha, img);
                JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
                limparCampos(null);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void limparCampos(ActionEvent e) {
        txtNome.setText("");
        txtSobrenome.setText("");
        txtArea.setText("");
        txtSenha.setText("");
        txtTelefone.setText("");
        txtUser.setText("");
        imgProfCadastro.setImage(i);
        dateDataNasc.setValue(LocalDate.now());
    }

    public void cadastrarExercicio(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarExercicio.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Cadastro Aparelho");
        stage.show();
    }

    public void pesquisaAluno(ActionEvent e) throws IOException {
        nomeAl = txtBusca.getText();
        if (nomeAl == "") {
            initTableAlunosProf();
        } else {
            initTableAlunosProfPesquisa();
        }
    }

    public void cadastrarAparelho(ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/CadastrarAparelho.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Cadastro Exercicio");
        stage.show();
    }

    public void inserirAluno(ActionEvent e) throws IOException {
        CadastraAluno cdsAl = new CadastraAluno();
        cdsAl.start(new Stage(), id);
    }

    public void voltarLogin(ActionEvent e) throws IOException {
        Stage stage = (Stage) lblNome.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Login");
        stage.show();
    }

    public void carregarImagem(ActionEvent e) {

        try {
            FileChooser fl = new FileChooser();
            fl.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", " *.png", "*.jpeg"));
            File file = fl.showOpenDialog(new Stage());
            imgProfCadastro.setImage(new Image("file:///" + file.getAbsolutePath()));
            
            absolutPath = file.getAbsolutePath();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhuma Imagem Selecionada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void setData() {
        combSexo.getItems().clear();
        combbTreino.getItems().clear();
        combSelectTreino.getItems().clear();
        combbReps.getItems().clear();
        combbSeries.getItems().clear();
        combArea.getItems().clear();

        combSexo.getItems().addAll(sexo);
        combbReps.getItems().addAll(reps);
        combbSeries.getItems().addAll(series);
        combArea.getItems().addAll(areas);

        List<Treino> treino = new ArrayList<>();
        TreinoDAO treinoDAO = new TreinoDAO();
        treino = treinoDAO.returnTreinos(p.getIdProf());

        for (int j = 0; j < treino.size(); j++) {
            Treino tr = new Treino();
            tr = treino.get(j);
            combbTreino.getItems().add(tr.getNomeTreino());
            combSelectTreino.getItems().add(tr.getNomeTreino());
        }
    }

    public void criarTreino() {
        if (txtTreino.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite um nome para o treino", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            String nTreino = txtTreino.getText();
            ProfessorDAO dao = new ProfessorDAO();
            dao.cadastrarTreino(nTreino, id);
            setData();
            txtTreino.setText("");
        }
    }

    public void clickTreinos() {
        setData();
    }
    
    public void clickProf(){
        setData();
    }

    public void clickListaAlunos() {
        initTableAlunosProf();
    }

    public void clickExercicio() {
        initTableExercicio();
        setData();
    }

    public void atribuiTreino(ActionEvent e) throws IOException {
        AtribuiTreino atTreino = new AtribuiTreino();
        atTreino.start(new Stage(), id);
    }

    public void removeExercicio(ActionEvent e) throws SQLException {
        try {
            int idEx = Integer.parseInt(tvVerTreino.getSelectionModel().getSelectedItem().getIdTreino());
            TreinoExercicioDAO dao = new TreinoExercicioDAO();
            dao.removeEx(idEx);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Selecione um exercicio!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void atualizarListaAlunos(ActionEvent e) {
        initTableAlunosProf();
    }

    public void atualizarListaExercicios(ActionEvent e) throws SQLException {
        TreinoExercicioDAO treinoExDAO = new TreinoExercicioDAO();
        String treino = combSelectTreino.getSelectionModel().getSelectedItem().toString();
        int idT = treinoExDAO.returnID(treino);
        initTableVerTreino(idT);
    }
    
    public void atualizarListaTreino(ActionEvent e){
            initTableExercicio();
    }

    public void initTableAlunosProf() {
        nomeAlCol.setCellValueFactory(new PropertyValueFactory("nomeCompleto"));
        telAlCol.setCellValueFactory(new PropertyValueFactory("telefoneAluno"));
        sexoAlCol.setCellValueFactory(new PropertyValueFactory("sexoAluno"));
        obAlCol.setCellValueFactory(new PropertyValueFactory("objetivo"));
        saudeAlCol.setCellValueFactory(new PropertyValueFactory("problemaSaude"));
        imcAlCol.setCellValueFactory(new PropertyValueFactory("imc"));
        tvAlunosFull.setItems(listaAlunosProf());
    }

    public void initTableAlunosProfPesquisa() {
        nomeAlCol.setCellValueFactory(new PropertyValueFactory("nomeCompleto"));
        telAlCol.setCellValueFactory(new PropertyValueFactory("telefoneAluno"));
        sexoAlCol.setCellValueFactory(new PropertyValueFactory("sexoAluno"));
        obAlCol.setCellValueFactory(new PropertyValueFactory("objetivo"));
        saudeAlCol.setCellValueFactory(new PropertyValueFactory("problemaSaude"));
        imcAlCol.setCellValueFactory(new PropertyValueFactory("imc"));
        tvAlunosFull.setItems(listaAlunosProfPesquisa());
    }

    public void initTableAlunos() {
        nomeCol.setCellValueFactory(new PropertyValueFactory("nomeCompleto"));
        telefoneCol.setCellValueFactory(new PropertyValueFactory("telefoneAluno"));
        tvAlunos.setItems(listaAlunos());
    }

    public void initTableExercicio() {
        nomeExCol.setCellValueFactory(new PropertyValueFactory("nomeExercicio"));
        aparelhoCol.setCellValueFactory(new PropertyValueFactory("aparelho"));
        tvExercicio.setItems(listaExercicio());
    }

    public void initTableVerTreino(int id) {
        serieColVer.setCellValueFactory(new PropertyValueFactory("serie"));
        repColVer.setCellValueFactory(new PropertyValueFactory("repeticoes"));
        exColVer.setCellValueFactory(new PropertyValueFactory("exercicio"));
        aparelhoCol.setCellValueFactory(new PropertyValueFactory("aparelho"));
        anotColVer.setCellValueFactory(new PropertyValueFactory("anotacao"));
        tvVerTreino.setItems(listaExTreino(id));
    }

    public ObservableList<TreinoAluno> listaExTreino(int id) {
        TreinoDAO dao = new TreinoDAO();
        return FXCollections.observableArrayList(dao.getTreino(id));
    }

    public ObservableList<Aluno> listaAlunos() {
        AlunoDAO dao = new AlunoDAO();
        return FXCollections.observableArrayList(dao.getTodosAlunos());
    }

    public ObservableList<Aluno> listaAlunosProf() {
        ProfessorDAO dao = new ProfessorDAO();
        return FXCollections.observableArrayList(dao.getTodosAlunosProf(id));
    }

    public ObservableList<Aluno> listaAlunosProfPesquisa() {
        ProfessorDAO dao = new ProfessorDAO();
        return FXCollections.observableArrayList(dao.getBuscarAlunosProf(nomeAl, id));
    }

    public ObservableList<Exercicio> listaExercicio() {
        ProfessorDAO dao = new ProfessorDAO();
        return FXCollections.observableArrayList(dao.getTodosExercicio());
    }

    public void adicionarExercicio(ActionEvent e) throws SQLException {
        if (combbReps.getSelectionModel().getSelectedIndex() < 0 || combbSeries.getSelectionModel().getSelectedIndex() < 0 || combbTreino.getSelectionModel().getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(null, "Campos em branco");
        } else {
            try {
                TreinoExercicioDAO treinoExDAO = new TreinoExercicioDAO();
                int repes = combbReps.getSelectionModel().getSelectedIndex() + 1;
                int serie = combbSeries.getSelectionModel().getSelectedIndex() + 1;
                String anotacoes = areaAnotacoes.getText();
                String treino = combbTreino.getSelectionModel().getSelectedItem().toString();
                int idT = treinoExDAO.returnID(treino);
                int idEx = tvExercicio.getSelectionModel().getSelectedItem().getIdExercicio();
                treinoExDAO.inserirExercicioTreino(repes, serie, idT, anotacoes, idEx);
                JOptionPane.showMessageDialog(null, "Exercicio Cadastrado");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "O exercicio nao foi selecionado");
            }
        }

    }

    public void verTreino(ActionEvent e) throws SQLException {
        try {
            TreinoExercicioDAO treinoExDAO = new TreinoExercicioDAO();
            String treino = combSelectTreino.getSelectionModel().getSelectedItem().toString();
            int idT = treinoExDAO.returnID(treino);
            initTableVerTreino(idT);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Selecione um Treino", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

}
