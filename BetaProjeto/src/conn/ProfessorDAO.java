package conn;

import JDBC.ConnectionFactory;
import controller.PrincipalProfessorController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Aluno;
import models.Exercicio;
import models.Professor;

public class ProfessorDAO {

    private Connection con;

    public ProfessorDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    Professor p = new Professor();
    PrincipalProfessorController pcontrol = new PrincipalProfessorController();

    public boolean logarProf(String user, String password) {
        try {
            String sql = "SELECT * FROM professor WHERE usuario = '" + user + "' AND senha = '" + password + "';";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco");
        }
        return false;
    }

    public Professor selectProf(String user, String password) throws SQLException {
        String sql = "SELECT * FROM professor WHERE usuario = '" + user + "' AND senha = '" + password + "';";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            p.setIdProf(rs.getInt(1));
            p.setpNomeProf(rs.getString(2));
            p.setuNomeProf(rs.getString(3));
            p.setDataNascProf(rs.getString(4));
            p.setTelefoneProf(rs.getString(5));
            p.setSexoProf(rs.getString(6));
            p.setImgProf(rs.getString(7));
            p.setArea(rs.getString(8));
            p.setSenhaProf(rs.getString(9));
            p.setUsuarioProf(rs.getString(10));
            return p;
        }
        return p;
    }

    public List<Aluno> getTodosAlunosProf(int id) {

        List<Aluno> alunos = new ArrayList();
        String sql = "call alunos(" + id + ");";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno al = new Aluno();
                al.setIdAluno(rs.getInt("idAluno"));
                al.setTelefoneAluno(rs.getString("Telefone"));
                al.setNomeCompleto(rs.getString("Nome Aluno"));
                al.setImc(rs.getString("IMC"));
                al.setObjetivo(rs.getString("Objetivo do aluno"));
                al.setProblemaSaude(rs.getString("Problema de Saúde"));
                al.setSexoAluno(rs.getString("sexo"));
                alunos.add(al);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Lista Não foi Retornada ");
            return null;
        }
        return alunos;
    }

    public List<Exercicio> getTodosExercicio() {

        List<Exercicio> exe = new ArrayList();
        String sql = "SELECT * FROM MontarExercicio";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Exercicio al = new Exercicio();
                al.setAparelho(rs.getString("Aparelho"));
                al.setNomeExercicio(rs.getString("Exercicio"));
                al.setIdExercicio(Integer.parseInt(rs.getString("idExercicio")));
                exe.add(al);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Lista Não foi");
            return null;
        }
        return exe;
    }

    public void cadastrarProf(String pNome, String uNome, String dataNasc, String tele, String sexo, String area, String usuario, String senha, String imgP) throws SQLException {

        String sql = "call InsereProfessor('" + pNome + "','" + uNome + "','" + dataNasc + "','" + tele + "','" + sexo + "','" + imgP + "','" + area + "','" + senha + "','" + usuario + "');";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void cadastrarAparelho(String nomeAp) throws SQLException {

        String sql = "call InsereAparelho('" + nomeAp + "');";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public List<Aluno> getBuscarAlunosProf(String nomebusca, int id) {

        List<Aluno> alunos = new ArrayList();
        String sql = "call BuscarAlunos('" + nomebusca + "'," + id + ");";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno al = new Aluno();
                al.setTelefoneAluno(rs.getString("Telefone"));
                al.setNomeCompleto(rs.getString("Nome Aluno"));
                al.setImc(rs.getString("IMC"));
                al.setObjetivo(rs.getString("Objetivo do aluno"));
                al.setProblemaSaude(rs.getString("Problema de Saúde"));
                al.setSexoAluno(rs.getString("sexo"));
                alunos.add(al);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lista não Retornada", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return alunos;
    }

    public void cadastrarTreino(String nomeTreino, int idP) {

        String sql = " INSERT INTO treino (idProfessor, NomeTreino) VALUES (" + idP + ",'" + nomeTreino + "');";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha no Cadastro");
        }
    }

    public void cadastrarExercicio(int aparelho, String nomeEx, String imgEx) {

        String sql = "call InsereExercicio('" + nomeEx + "','" + imgEx + "'," + aparelho + ")";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha no Cadastro");
        }
    }

}
