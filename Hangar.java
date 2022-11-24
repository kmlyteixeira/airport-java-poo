import java.sql.PreparedStatement;

import db.DAO;

public class Hangar {
    
    private int id;
    private String local;

    public Hangar(String local) throws Exception {
        this.local = local;

        PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO hangar (local) VALUES (?)");
        stmt.setString(1, getLocal());
        stmt.execute();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public static void ImprimirHangares() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM hangar");
        stmt.execute();
    }

    public static void AlterarHangar(int id, String local) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE hangar SET local = ? WHERE id = ?");
        stmt.setString(1, local);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void ExcluirHangar(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM hangar WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return "Hangar: " + "ID: " + id + ", Local: " + local;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Hangar)) {
            return false;
        }
        final Hangar other = (Hangar) object;

        return this.id == other.id;
    }

}
