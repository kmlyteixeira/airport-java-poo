package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

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

        Companhia companhia = getCompanhiaById(id);
        if (companhia == null) {
            PreparedStatement stmt = DAO.createConnection()
                    .prepareStatement("INSERT INTO companhia (nome, cnpj) VALUES (?, ?)");
            stmt.setString(1, getNome());
            stmt.setString(2, getCnpj());
            stmt.execute();
            DAO.closeConnection();
        }
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

    public static void ListarCompanhias() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM companhia");
        stmt.execute();

        System.out.println("====== COMPANHIAS ======");
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            Companhia companhia = new Companhia(rs.getInt("id"), rs.getString("nome"), rs.getString("cnpj"));
            System.out.println(companhia);
        }
    }

    public static void CadastrarCompanhia(Scanner sc) throws Exception {
        System.out.println("====== CADASTRAR COMPANHIA ======");
        System.out.print("Nome: ");
        String nome = sc.next();
        System.out.print("CNPJ: ");
        String cnpj = sc.next();

        Companhia companhia = new Companhia(0, nome, cnpj);
        System.out.println("Companhia " + companhia.getNome() + " cadastrada com sucesso!");
    }

    public static void AlterarCompanhia(Scanner sc) throws Exception {
        System.out.println("====== ALTERAR COMPANHIA ======");
        System.out.println("Digite o ID da companhia que deseja alterar: ");
        int id = sc.nextInt();
        Companhia companhia = getCompanhiaById(id);
        if (companhia == null) {
            throw new Exception("Companhia não encontrada!");
        }

        System.out.println("Qual informação deseja alterar?");
        System.out.println("1 - Nome" +
                "\n2 - CNPJ");
        int opcaoCompanhia = sc.nextInt();
        System.out.println("Digite o novo valor: ");
        String novoValor = sc.next();

        updateCompanhia(id, novoValor, opcaoCompanhia);
        System.out.println("Companhia alterada com sucesso!");
    }

    public static void DeletarCompanhia(Scanner sc) throws Exception {
        System.out.println("====== DELETAR COMPANHIA ======");
        System.out.println("Digite o ID da companhia que deseja deletar: ");
        int id = sc.nextInt();
        Companhia companhia = getCompanhiaById(id);
        if (companhia == null) {
            throw new Exception("Companhia não encontrada!");
        } else {
            deleteCompanhia(id);
            System.out.println("Companhia deletada com sucesso!");
        }
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

    public static void updateCompanhia(int id, String input, int tipoDado) throws Exception {

        String campo = DefineTipoUpdate.defineCampoUpdate(tipoDado, getCompanhiaById(id));

        PreparedStatement stmt = DAO.createConnection()
                .prepareStatement("UPDATE companhia SET " + campo + " = ? WHERE id = ?");
        stmt.setString(1, input);
        stmt.setInt(2, id);
        stmt.execute();

        throw new Exception("Alteração não realizada!");
    }

    public static void deleteCompanhia(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM companhia WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();
    }

    @Override
    public String toString() {
        return "\n | ID: " + getId() +
                "\n | Nome: " + getNome() +
                "\n | CNPJ: " + getCnpj();
    }
}
