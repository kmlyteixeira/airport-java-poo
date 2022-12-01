package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DAO;
import utils.DefineTipoUpdate;
import utils.Mascara;

public class Aviao extends Aeromodelo {
    private String prefixo;
    private int capacidade;
    private Companhia companhia;

    public Aviao(int id, String modelo, String marca, String prefixo, int capacidade, Companhia companhia) throws Exception {
        super(id, marca, modelo);
        this.prefixo = prefixo;
        this.capacidade = capacidade;
        this.companhia = companhia;

        if (!Mascara.isValida(prefixo, "[A-Z]{3}[0-9]{4}")) {
            throw new Exception("Prefixo inválido");
        }

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO aviao (modelo, marca, prefixo, capacidade, companhia_id) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, getModelo());
        stmt.setString(2, getMarca());
        stmt.setString(3, getPrefixo());
        stmt.setInt(4, getCapacidade());
        stmt.setInt(5, getCompanhia().getId());
        stmt.execute();
    }

    public String getPrefixo() {
        return prefixo;
    }

    public void setPrefixo(String prefixo) {
        this.prefixo = prefixo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public Companhia getCompanhia() {
        return companhia;
    }

    public void setCompanhia(Companhia companhia) {
        this.companhia = companhia;
    }

    public static void imprimirAvioes() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM aviao");
        stmt.execute();
    }

    public static Aviao getAviaoById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT id FROM aviao WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            Aviao aviao = new Aviao(
                rs.getInt("id"), 
                rs.getString("modelo"), 
                rs.getString("marca"), 
                rs.getString("prefixo"), 
                rs.getInt("capacidade"), 
                Companhia.getCompanhiaById(rs.getInt("companhia_id")));
            return aviao;
        } else {
            return null;
        }
    }

    public static void alterarAviao(int id, String input, int tipoDado) throws Exception {

        String campo = DefineTipoUpdate.defineCampoUpdate(tipoDado, getAviaoById(id));

        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE aviao SET "+campo+" = ? WHERE id = ?");
        stmt.setString(1, input);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void deletarAviao(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM aviao WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return "Aviao: ID: " + super.getId() + ", companhia:" + companhia + ", marca:" + super.getMarca()
                + ", modelo:" + super.getModelo() + ", prefixo:" + prefixo + ", capacidade:" + capacidade;
    }

}
