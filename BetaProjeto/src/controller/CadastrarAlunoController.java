package controller;

import conn.AlunoDAO;
import conn.ProfessorDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class CadastrarAlunoController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtAltura;
    @FXML
    private TextField txtPeso;
    @FXML
    private DatePicker dateDataNasc;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtProbSaude;
    @FXML
    private TextField txtObjetivo;
    @FXML
    private ImageView imageAluno;
    @FXML
    private ComboBox combObjetivo;
    @FXML
    private ComboBox combSexo;

    Image i = new Image("/imgs/image.jpg");
    ProfessorDAO conn = new ProfessorDAO();
    int id;
    String path;
    String absolutPath;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
    }

    //ComboBox
    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "F", "M"
            );
    ObservableList<String> objetivos
            = FXCollections.observableArrayList(
                    "Ganhar Massa", "Perder Peso", "BodyBuild", "Definir"
            );

    public void setData() {
        combSexo.getItems().addAll(options);
        combObjetivo.getItems().addAll(objetivos);
    }

    public void setProf(int idp) {
        id = idp;
    }



    public void LimparCampos(ActionEvent e) {
        txtNome.setText("");
        txtAltura.setText("");
        combObjetivo.getSelectionModel().clearSelection();
        txtPeso.setText("");
        txtSenha.setText("");
        txtSobrenome.setText("");
        txtTelefone.setText("");
        txtUser.setText("");
        imageAluno.setImage(i);
    }

    public void Cadastrar(ActionEvent e) {
        if (txtAltura.getText().isEmpty() || txtNome.getText().isEmpty() || combObjetivo.getSelectionModel().getSelectedIndex() < 0 || txtPeso.getText().isEmpty() || txtSenha.getText().isEmpty()
                || dateDataNasc.getValue() == null || txtSobrenome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtUser.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo em branco", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String pnome = txtNome.getText();
                String unome = txtSobrenome.getText();
                double altura = Double.parseDouble(txtAltura.getText());
                double peso = Double.parseDouble(txtPeso.getText());
                String sx = combSexo.getSelectionModel().getSelectedItem().toString();
                LocalDate nasc = dateDataNasc.getValue();
                String tel = txtTelefone.getText();
                String usuario = txtUser.getText();
                String senha = txtSenha.getText();
                path =  pnome + unome + ".jpg";
                String img = "/imgsAluno/" + path;
                String objetivo = combObjetivo.getSelectionModel().getSelectedItem().toString();
                String problemasSaude = txtProbSaude.getText().toString();
                double imc = peso / (altura * altura);
                DecimalFormat df = new DecimalFormat("#.00");
                String imcString = df.format(imc);
                
                
                path = txtNome.getText() + txtSobrenome.getText() + ".jpg";
                File imgPath = new File(absolutPath);
                BufferedImage bImage = ImageIO.read(imgPath);
                
               
                ImageIO.write(bImage, "jpg", new File("C:\\Users\\CAIO\\Desktop\\LP3\\BetaProjeto\\src\\imgsAluno\\" + path));
                AlunoDAO alDAO = new AlunoDAO();
                alDAO.CadastrarAluno(usuario, pnome, unome, problemasSaude, objetivo, nasc.toString(), tel, sx, peso, altura, imcString, senha, id, img);
                LimparCampos(null);
                JOptionPane.showMessageDialog(null, "Aluno Cadastrado com sucesso!");
                Stage stagep = (Stage) txtUser.getScene().getWindow();
                stagep.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha no Cadastro!");
            }
        }

    }
        public void CarregarImagem(ActionEvent e) throws FileNotFoundException, IOException {
        try {
            FileChooser fl = new FileChooser();
            fl.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", " *.png", "*.jpeg"));
            File file = fl.showOpenDialog(new Stage());
            imageAluno.setImage(new Image("file:///" + file.getAbsolutePath()));
            
            absolutPath = file.getAbsolutePath();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhuma Imagem Selecionada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}
