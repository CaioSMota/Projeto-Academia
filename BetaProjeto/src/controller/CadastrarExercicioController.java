package controller;

import conn.AparelhoDAO;
import conn.ProfessorDAO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import models.Aparelho;

public class CadastrarExercicioController implements Initializable {

    @FXML
    private TextField txtNomeExercicio;
    @FXML
    private ImageView imageExercicio;
    @FXML
    private ComboBox combAparelho;

    String path = null, absolutPath = null;
    Image i = new Image("/imgs/image.jpg");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageExercicio.setImage(i);
        setData();
    }

    public void setData() {
        combAparelho.getItems().clear();

        List<Aparelho> aparelho = new ArrayList<>();
        AparelhoDAO apDAO = new AparelhoDAO();
        aparelho = apDAO.returnAparelho();

        for (int j = 0; j < aparelho.size(); j++) {
            Aparelho a = new Aparelho();
            a = aparelho.get(j);
            combAparelho.getItems().add(a.getNomeAparelho());
        }
    }

    public void cadastrarEx(ActionEvent e) throws SQLException {
        if (txtNomeExercicio.getText().equals("") || imageExercicio.getImage().equals(i)) {
            JOptionPane.showMessageDialog(null, "Campo em branco", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                String nomeEx = txtNomeExercicio.getText();
                ProfessorDAO pdao = new ProfessorDAO();
                path = txtNomeExercicio.getText() + ".jpg";
                
                String img = "/imgsEx/" + path;
                File imgPath = new File(absolutPath);
                System.out.println(absolutPath);
                System.out.println(imgPath);
                BufferedImage bImage = ImageIO.read(imgPath);
                System.out.println(bImage);
                
                ImageIO.write(bImage, "jpeg", new File("C:\\Users\\CAIO\\Desktop\\LP3\\BetaProjeto\\src\\imgs\\" + path));

                AparelhoDAO aparelhoDAO = new AparelhoDAO();

                String aparelho = combAparelho.getSelectionModel().getSelectedItem().toString();
                int idT = aparelhoDAO.returnIdAp(aparelho);

                pdao.cadastrarExercicio(idT, nomeEx, img);

                Stage stagep = (Stage) txtNomeExercicio.getScene().getWindow();
                stagep.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Falha no Cadastro!");
            }
        }

    }

    public void CarregarImagem(ActionEvent e) {
        try {
            FileChooser fl = new FileChooser();
            fl.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", " *.png", "*.jpeg"));
            File file = fl.showOpenDialog(new Stage());
            imageExercicio.setImage(new Image("file:///" + file.getAbsolutePath()));
            absolutPath = file.getAbsolutePath();
            System.out.println(absolutPath);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Nenhuma Imagem Selecionada", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    }

}
