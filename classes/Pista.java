package classes;
import java.sql.PreparedStatement;

import db.DAO;
import utils.Mascara;

public class Pista {
    private int id;
    private String numero;

    public Pista(String numero) throws Exception {
        this.numero = numero;

        if (!Mascara.isValida(numero, "[A-Z]{1}[0-9]{3}")) {
            throw new Exception("Numero de pista inválido");
        }

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO pista (numero) VALUES (?)");
        stmt.setString(1, getNumero());
        stmt.execute();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public static void imprimirPistas() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM pista");
        stmt.execute();
    }

    /* AJUSTAR AS ALTERAÇÕES - USAR UM TIPO GENERICO */
    public static void alterarPista(int id, String numero) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE pista SET numero = ? WHERE id = ?");
        stmt.setString(1, numero);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void deletarPista(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM pista WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    public static void getPistaById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM pista WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return "Pista: " + "ID: " + id + ", Numero: " + numero;
    }
}
