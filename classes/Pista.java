package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DAO;
import utils.DefineTipoUpdate;
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
    public static void alterarPista(int id, String input, int tipoDado) throws Exception {
        String campo = DefineTipoUpdate.defineCampoUpdate(tipoDado, getPistaById(id));

        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE pista SET "+campo+" = ? WHERE id = ?");
        stmt.setString(1, input);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void deletarPista(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM pista WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    public static Pista getPistaById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM pista WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            Pista pista = new Pista(
                rs.getString("numero"));
            return pista;
        } else {
            return null;
        }

    }

    @Override
    public String toString() {
        return "Pista: " + "ID: " + id + ", Numero: " + numero;
    }
}
