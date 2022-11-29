import java.sql.PreparedStatement;

import db.DAO;

public class Voo {
    private int id;
    private String numero;
    private String observacao;
    private String piloto;
    private String copiloto;
    private String origem;
    private String destino;
    private String data;
    private String hora;
    private Aeromodelo aeromodelo;
    private Pista pista;

    public Voo(int id, String numero, String observacao, String piloto, String copiloto, String origem, String destino, String data, String hora, Aeromodelo aeromodelo, Pista pista) throws Exception {
        this.id = id;
        this.numero = numero;
        this.observacao = observacao;
        this.piloto = piloto;
        this.copiloto = copiloto;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.hora = hora;
        this.aeromodelo = aeromodelo;
        this.pista = pista;

        int[] definicaoAeromodelo = getAeromodelo(aeromodelo);

        PreparedStatement stmt = DAO.createConnection().prepareStatement(
            "INSERT INTO voo (numero, observacao, piloto, copiloto, origem, destino, data, hora, pista_id, aviao_id, helicoptero_id, jato_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, getNumero());
        stmt.setString(2, getObservacao());
        stmt.setString(3, getPiloto());
        stmt.setString(4, getCopiloto());
        stmt.setString(5, getOrigem());
        stmt.setString(6, getDestino());
        stmt.setString(7, getData());
        stmt.setString(8, getHora());
        stmt.setInt(9, getPista().getId());
        stmt.setInt(10, definicaoAeromodelo[0]);
        stmt.setInt(11, definicaoAeromodelo[1]);
        stmt.setInt(12, definicaoAeromodelo[2]);
        stmt.execute();
    }

    public static <T> int[] getAeromodelo(T aeromodelo) throws Exception {
        if (aeromodelo instanceof Aviao) {
            return new int[] { ((Aviao) aeromodelo).getId(), 0, 0 };
        } else if (aeromodelo instanceof Helicoptero) {
            return new int[] { 0, ((Helicoptero) aeromodelo).getId(), 0 };
        } else if (aeromodelo instanceof Jato) {
            return new int[] { 0, 0, ((Jato) aeromodelo).getId() };
        } else {
            throw new Exception("Aeromodelo não definido");
        }
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getCopiloto() {
        return copiloto;
    }

    public void setCopiloto(String copiloto) {
        this.copiloto = copiloto;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    @Override
    public String toString() {
        return "Voo: id:" + id + ", numero:" + numero + ", observacao:" + observacao + ", piloto:" + piloto + ", copiloto:"
                + copiloto + ", origem:" + origem + ", destino:" + destino + ", data:" + data + ", hora:" + hora
                + ", aeromodelo:" + aeromodelo + ", pista:" + pista;
    }

}