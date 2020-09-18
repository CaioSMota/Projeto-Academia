package conn;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Treino;
import models.TreinoAluno;

public class TreinoDAO {

    private Connection con;

    public TreinoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarTreino(int idProf, String nomeTreino) {

        String sql = "call CadastraTreino('" + idProf + "','" + nomeTreino + "');";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Treino Cadastrado");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Treino> returnTreinos(int idProf) {
        List<Treino> treinos = new ArrayList();
        String sql = "call SelectTreino(" + idProf + ");";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Treino t = new Treino();
                t.setIdTreino(rs.getInt("idTreino"));
                t.setNomeTreino(rs.getString("NomeTreino"));
                t.setProfessor(rs.getInt("idProfessor"));
                treinos.add(t);
            }

            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO: Lista não Retornada");
            return null;
        }
        return treinos;
    }

    public List<TreinoAluno> getTreino(int id) {

        List<TreinoAluno> ta = new ArrayList();
        String sql = "call RetornaTreino(" + id + ");";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TreinoAluno al = new TreinoAluno();
                al.setTreino(rs.getString("Treino"));
                al.setIdTreino(rs.getString("idTreinoExercicio"));
                al.setAnotacao(rs.getString("Anotações"));
                al.setAparelho(rs.getString("Aparelho"));
                al.setExercicio(rs.getString("Exercicio"));
                al.setSerie(rs.getString("Series"));
                al.setRepeticoes(rs.getString("Repetições"));
                ta.add(al);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Lista Não foi Retornada ");
            return null;
        }
        return ta;
    }

    public void atribuiTreino(int idA, int idT) {
        String sql = "call AtribuiTreino(" + idA + "," + idT + ");";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Treino não inserido ");
        }
    }

    public void alterarTreino(int idA, int idT) {
        String sql = "call AlterarTreino(" + idA + "," + idT + ");";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Treino não alterado ");
        }
    }

    public boolean getTreinoAluno(int idA) {
        String sql = " call getTreinoAluno(" + idA + ");";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("ERRO: Treino não alterado ");
        }
        return false;
    }
}
