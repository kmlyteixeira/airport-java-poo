package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DAO;
import utils.DefineTipoUpdate;

public class Jato extends Aeromodelo {
    private String cor;
    private int velocidade;

    public Jato(int id, String marca, String modelo, String cor, int velocidade) throws Exception {
        super(id, marca, modelo);
        this.cor = cor;
        this.velocidade = velocidade;

        Jato jato = getJatoById(id);
        if (jato == null) {
            PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO jato (modelo, marca, cor, velocidade) VALUES (?, ?, ?, ?)");
            stmt.setString(1, getModelo());
            stmt.setString(2, getMarca());
            stmt.setString(3, getCor());
            stmt.setInt(4, getVelocidade());
            stmt.execute();
            DAO.closeConnection();
        }
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public static void imprimirJatos() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM jato");
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            Jato jato = new Jato(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"), rs.getString("cor"), rs.getInt("velocidade"));
            System.out.println(jato);
        }
    }

    public static Jato getJatoById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM jato WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            Jato jato = new Jato(
                rs.getInt("id"), 
                rs.getString("marca"), 
                rs.getString("modelo"), 
                rs.getString("cor"), 
                rs.getInt("velocidade"));
            return jato;
        } else {
            return null;
        }
    }
    
    public static void alterarJato(int id, String input, int tipoDado) throws Exception {

        String campo = DefineTipoUpdate.defineCampoUpdate(tipoDado, getJatoById(id));

        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE jato SET "+campo+" = ? WHERE id = ?");
        stmt.setString(1, input);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void deletarJato(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM jato WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return super.toString() + 
        "\n | Cor: " + getCor() + 
        "\n | Velocidade: " + getVelocidade();
    }
}
