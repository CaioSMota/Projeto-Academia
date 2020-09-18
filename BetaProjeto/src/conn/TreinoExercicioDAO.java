package conn;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Treino;

public class TreinoExercicioDAO {

    private Connection con;

    public TreinoExercicioDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void inserirExercicioTreino(int reps, int serie, int idTreino, String anotacoes, int idEx) {
        String sql = "call InsereExercicioTreino(" + reps + "," + serie + ",'" + anotacoes + "'," + idEx + "," + idTreino + ");";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int returnID(String nome) throws SQLException {
        Treino t = new Treino();
        String sql = "call RetornaIdTreino('" + nome + "');";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                t.setIdTreino(rs.getInt("idTreino"));
            }

        } catch (Exception e) {
            System.out.println("ERRO: Lista não Retornada");
        }

        return t.getIdTreino();
    }

    public void removeEx(int id) throws SQLException {
        String sql = "call RemoveExercicio(" + id + ");";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Exercicio Retirado do Treino");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
