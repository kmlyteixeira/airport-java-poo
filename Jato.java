import java.sql.PreparedStatement;

import db.DAO;

public class Jato extends Aeromodelo {
    private String cor;
    private int velocidade;

    public Jato(int id, String marca, String modelo, String cor, int velocidade) throws Exception {
        super(id, marca, modelo);
        this.cor = cor;
        this.velocidade = velocidade;

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO jato (modelo, marca, cor, velocidade) VALUES (?, ?, ?, ?)");
        stmt.setString(1, getModelo());
        stmt.setString(2, getMarca());
        stmt.setString(3, getCor());
        stmt.setInt(4, getVelocidade());
        stmt.execute();
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
    }

    public static void getJatoById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM jato WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }
    /* AJUSTAR AS ALTERAÇÕES - USAR UM TIPO GENERICO */
    public static void alterarJato(int id, String modelo) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE jato SET modelo = ? WHERE id = ?");
        stmt.setString(1, modelo);
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
        return "Jato: cor:" + cor + ", velocidade:" + velocidade;
    }
}
