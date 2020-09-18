package conn;

import java.sql.Connection;
import JDBC.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Aluno;
import models.Suplemento;
import models.TreinoAluno;

public class AlunoDAO {

    private Connection con;

    public AlunoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public boolean logarAluno(String user, String password) {
        try {
            String sql = "SELECT * FROM aluno WHERE usuario = '" + user + "' AND senha = '" + password + "';";
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

    public Aluno selectAluno(String user, String password) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE usuario = '" + user + "' AND senha = '" + password + "';";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        Aluno a = new Aluno();
        if (rs.next()) {
            a.setIdAluno(Integer.parseInt(rs.getString("idAluno")));
            a.setUsuarioAluno(rs.getString("usuario"));
            a.setpNomeAluno(rs.getString("pNomeAluno"));
            a.setuNomeAluno(rs.getString("uNomeAluno"));
            a.setProblemaSaude(rs.getString("problemaSaude"));
            a.setObjetivo(rs.getString("objetivo"));
            a.setDataNascAluno(rs.getString("dataNasc"));
            a.setPesoAluno(rs.getDouble("peso"));
            a.setSexoAluno(rs.getString("sexo"));
            a.setAlturaAluno(rs.getDouble("altura"));
            a.setImc(rs.getString("imc"));
            a.setSenha(rs.getString("senha"));
            a.setTelefoneAluno(rs.getString("telefone"));
            a.setImgAluno(rs.getString("imgAluno"));
            return a;
        }
        stmt.close();
        rs.close();
        con.close();
        return a;
    }

    public List<Aluno> getTodosAlunos() {

        List<Aluno> alunos = new ArrayList();
        String sql = "Select * from aluno;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno al = new Aluno();
                al.setIdAluno(Integer.parseInt(rs.getString("idAluno")));
                al.setpNomeAluno(rs.getString("pNomeAluno"));
                al.setuNomeAluno(rs.getString("uNomeAluno"));
                al.setImc(rs.getString("imc"));
                al.setSexoAluno(rs.getString("sexo"));
                al.setDataNascAluno(rs.getString("dataNasc"));
                al.setObjetivo(rs.getString("objetivo"));
                al.setProblemaSaude("problemaSaude");
                al.setSenha(rs.getString("senha"));
                al.setImgAluno(rs.getString("imgAluno"));
                al.setIdprof(Integer.parseInt(rs.getString("idProf")));
                al.setUsuarioAluno(rs.getString("usuario"));
                al.setAlturaAluno(Double.parseDouble(rs.getString("altura")));
                al.setPesoAluno(Double.parseDouble(rs.getString("peso")));
                al.setTelefoneAluno(rs.getString("telefone"));
                al.setNomeCompleto(al.getuNomeAluno(), al.getpNomeAluno());
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

    public List<TreinoAluno> getTreinoAlunos(int id) {

        List<TreinoAluno> ta = new ArrayList();
        String sql = "call alunoTreino(" + id + ");";

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

    public List<Suplemento> getSuplementos() {

        List<Suplemento> sup = new ArrayList();
        String sql = "SELECT * FROM MostrarSuplementos;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Suplemento al = new Suplemento();
                al.setIdSuplemento(rs.getInt("idSuplemento"));
                al.setDosagemSuplemento(rs.getString("Dosagem do Suplemento"));
                al.setDescSuplemento(rs.getString("Descrição de Suplemento"));
                al.setNomeSuplemento(rs.getString("Nome Suplemento"));
                sup.add(al);
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Lista Não foi Retornada ");
            return null;
        }
        return sup;
    }

    public void CadastrarAluno(String usuario, String pnome, String unome, String probSaude, String objetivo, String dataNasc, String telef, String sexo, double peso, double altura, String imc, String senha, int idProf, String imgAluno) {
        String sql = "call InsereAluno('" + usuario + "','" + pnome + "','" + unome + "','" + probSaude + "','" + objetivo + "','" + dataNasc + "','" + telef + "','" + sexo + "','" + peso + "','" + altura + "','" + imc + "','" + senha + "'," + idProf + ",'" + imgAluno + "');";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public Suplemento getSuplementoClick(int id) {

        Suplemento sup = new Suplemento();
        String sql = "call MostrarSuplementosClick(" + id + ");";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sup.setNomeSuplemento(rs.getString("nomeSuplemento"));
                sup.setDescSuplemento(rs.getString("descSuplemento"));
                sup.setDosagemSuplemento(rs.getString("dosagemSuplemento"));
                sup.setImgSuplemnto(rs.getString("imgSup"));
            }
            stmt.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Lista Não foi Retornada");
            return null;
        }
        return sup;
    }

    public TreinoAluno getTreinoAlunosClick(int id) {

        TreinoAluno ta = new TreinoAluno();
        String sql = "call alunoTreinoClick(" + id + ");";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ta.setTreino(rs.getString("Treino"));
                ta.setExercicio(rs.getString("Exercicio"));
                ta.setAparelho(rs.getString("Aparelho"));
                ta.setSerie(rs.getString("Series"));
                ta.setRepeticoes(rs.getString("Repetições"));
                ta.setAnotacao(rs.getString("Anotações"));
                ta.setImgEx(rs.getString("imgExercicio"));
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

    public void atualizaAluno(int idA, float peso, float altura, String senha, String tel, String problema) {
        String sql = "call AtualizarAluno(" + idA + "," + peso + "," + altura + ",'" + senha + "','" + tel + "','" + problema + "');";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (SQLException ex) {
            System.out.println("ERRO: Lista Não foi Retornada ");
        }

    }
}
