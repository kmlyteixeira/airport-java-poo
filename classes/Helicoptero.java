package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DAO;
import utils.DefineTipoUpdate;

public class Helicoptero extends Aeromodelo {
    private int capacidade;
    private String cor;

    public Helicoptero(int id, String marca, String modelo, int capacidade, String cor) throws Exception {
        super(id, marca, modelo);
        this.capacidade = capacidade;
        this.cor = cor;

        Helicoptero helicoptero = getHelicopteroById(id);
        if (helicoptero == null) {
            PreparedStatement stmt = DAO.createConnection()
                    .prepareStatement("INSERT INTO helicoptero (modelo, marca, capacidade, cor) VALUES (?, ?, ?, ?)");
            stmt.setString(1, getModelo());
            stmt.setString(2, getMarca());
            stmt.setInt(3, getCapacidade());
            stmt.setString(4, getCor());
            stmt.execute();
            DAO.closeConnection();
        }
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

        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            Helicoptero helicoptero = new Helicoptero(rs.getInt("id"), rs.getString("marca"), rs.getString("modelo"),
                    rs.getInt("capacidade"), rs.getString("cor"));
            System.out.println(helicoptero);
        }
    }

    public static Helicoptero getHelicopteroById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM helicoptero WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            Helicoptero helicoptero = new Helicoptero(
                    rs.getInt("id"),
                    rs.getString("modelo"),
                    rs.getString("marca"),
                    rs.getInt("capacidade"),
                    rs.getString("cor"));
            return helicoptero;
        } else {
            return null;
        }
    }

    /* AJUSTAR AS ALTERAÇÕES - USAR UM TIPO GENERICO */
    public static void alterarHelicoptero(int id, String input, int tipoDado) throws Exception {

        String campo = DefineTipoUpdate.defineCampoUpdate(tipoDado, getHelicopteroById(id));

        PreparedStatement stmt = DAO.createConnection()
                .prepareStatement("UPDATE helicoptero SET " + campo + " = ? WHERE id = ?");
        stmt.setString(1, input);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void deletarHelicoptero(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM helicoptero WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n | Cor: " + getCor() +
                "\n | Capacidade: " + getCapacidade();
    }
}
