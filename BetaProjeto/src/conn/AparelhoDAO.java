package conn;

import JDBC.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Aparelho;

public class AparelhoDAO {

    private Connection con;

    public AparelhoDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public List<Aparelho> returnAparelho() {
        List<Aparelho> aparelho = new ArrayList();
        String sql = "Select * from aparelho;";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Aparelho a = new Aparelho();
                a.setNomeAparelho(rs.getString("nomeAparelho"));
                a.setIdAparelho(rs.getInt("idAparelho"));
                aparelho.add(a);
            }

            stm.close();
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("ERRO: Lista não Retornada");
            return null;
        }
        return aparelho;
    }

    public int returnIdAp(String nome) throws SQLException {
        Aparelho a = new Aparelho();
        String sql = "call RetornaIdAparelho('" + nome + "');";

        try {
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                a.setIdAparelho(rs.getInt("idAparelho"));
            }

        } catch (Exception e) {
            System.out.println("ERRO: Lista não Retornada");
        }

        return a.getIdAparelho();
    }

}
