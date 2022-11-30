package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DAO;
import utils.DefineTipoUpdate;

public class Companhia {
    private int id;
    private String nome;
    private String cnpj;

    public Companhia(int id, String nome, String cnpj) throws Exception {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO companhia (nome, cnpj) VALUES (?, ?)");
        stmt.setString(1, getNome());
        stmt.setString(2, getCnpj());
        stmt.execute();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public static void imprimirCompanhias() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM companhia");
        stmt.execute();
    }

    public static Companhia getCompanhiaById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM companhia WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            Companhia companhia = new Companhia(
                rs.getInt("id"), 
                rs.getString("nome"), 
                rs.getString("cnpj"));
            return companhia;
        } else {
            return null;
        }
    }
    
    public static void alterarCompanhia(int id, String input, int tipoDado) throws Exception {

        String[] dados = DefineTipoUpdate.getTipoDado(tipoDado, input, getCompanhiaById(id));

        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE companhia SET nome = ?, cnpj = ? WHERE id = ?");
        stmt.setString(1, dados[0]);
        stmt.setString(2, dados[1]);
        stmt.setInt(3, id);
        stmt.execute();
    }

    public static void excluirCompanhia(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM companhia WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return "Companhia: ID: " + getId() + ", Nome: " + getNome() + ", CNPJ: " + getCnpj();
    }
}
