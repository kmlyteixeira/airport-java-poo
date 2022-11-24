import java.sql.PreparedStatement;

import db.DAO;

public class Pista {
    private int id;
    private String nome;

    public Pista(String nome) throws Exception {
        this.nome = nome;

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO pista (nome) VALUES (?)");
        stmt.setString(1, getNome());
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

    public static void ImprimirPistas() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM pista");
        stmt.execute();
    }

    public static void AlterarPista(int id, String nome) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE pista SET nome = ? WHERE id = ?");
        stmt.setString(1, nome);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void DeletarPista(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM pista WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }
}
