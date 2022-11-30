package classes;
import java.sql.PreparedStatement;

import db.DAO;

public class Helicoptero extends Aeromodelo {
    private int capacidade;
    private String cor;

    public Helicoptero(int id, String marca, String modelo, int capacidade, String cor) throws Exception {
        super(id, marca, modelo);
        this.capacidade = capacidade;
        this.cor = cor;

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO helicoptero (modelo, marca, capacidade, cor) VALUES (?, ?, ?, ?)");
        stmt.setString(1, getModelo());
        stmt.setString(2, getMarca());
        stmt.setInt(3, getCapacidade());
        stmt.setString(4, getCor());
        stmt.execute();
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public static void imprimirHelicopteros() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM helicoptero");
        stmt.execute();
    }

    public static void getHelicopteroById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM helicoptero WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }
    /* AJUSTAR AS ALTERAÇÕES - USAR UM TIPO GENERICO */
    public static void alterarHelicoptero(int id, String modelo) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE helicoptero SET modelo = ? WHERE id = ?");
        stmt.setString(1, modelo);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void deletarHelicoptero(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM helicoptero WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }
}
