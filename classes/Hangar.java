package classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import db.DAO;

public class Hangar {
    
    private int id;
    private String local;

    public Hangar(int id, String local) throws Exception {
        this.local = local;

        Hangar hangar = getHangarById(id);
        if (hangar == null) {
            PreparedStatement stmt = DAO.createConnection().prepareStatement("INSERT INTO hangar (local) VALUES (?)");
            stmt.setString(1, getLocal());
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public static void ListarHangares() throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM hangar");
        stmt.execute();

        System.out.println("====== HANGARES ======");
        ResultSet rs = stmt.getResultSet();
        while (rs.next()) {
            Hangar hangar = new Hangar(rs.getInt("id"), rs.getString("local"));
            System.out.println(hangar);
        }
    }

    public static void AlterarHangar(Scanner sc) throws Exception {
        System.out.println("====== ALTERAR HANGAR ======");
        System.out.println("Digite o ID do hangar que deseja alterar:");
        int id = sc.nextInt();
        System.out.println("Qual informação deseja alterar?");
        System.out.println(
            "1 - Local" +
            "\n2 - Adicionar aeronave" +
            "\n3 - Remover aeronave");
        int opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo local:");
                String local = sc.next();
                updateHangar(id, local);
                break;
            
            case 2:
                System.out.println("Digite o ID da aeronave que deseja adicionar:");
                int idAviaoAdd = sc.nextInt();
                Aviao aviaoAdd = Aviao.getAviaoById(idAviaoAdd);
                if (aviaoAdd == null) {
                    throw new Exception("Aeronave não encontrada!");
                }
                adicionarAviaoAoHangar(id, aviaoAdd);
                System.out.println("Aeronave adicionada com sucesso!");
                break;
            
            case 3:
                System.out.println("Digite o ID da aeronave que deseja remover:");
                int idAviaoRemover = sc.nextInt();
                removerAviaoDoHangar(idAviaoRemover);
                System.out.println("Aeronave removida com sucesso!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public static void CadastrarHangar(Scanner sc) throws Exception {
        System.out.println("====== CADASTRAR HANGAR ======");
        System.out.println("Digite o local do hangar:");
        String local = sc.next();
        Hangar hangar = new Hangar(0, local);
        System.out.println("Hangar "+hangar.getId()+" cadastrado com sucesso!");
    }

    public static void DeletarHangar(Scanner sc) throws Exception {
        System.out.println("====== DELETAR HANGAR ======");
        System.out.println("Digite o ID do hangar que deseja deletar:");
        int id = sc.nextInt();
        Hangar hangar = getHangarById(id);
        if (hangar == null) {
            throw new Exception("Hangar não encontrado!");
        }
        excluirHangar(id);
        System.out.println("Hangar deletado com sucesso!");
    }

    public static void adicionarAviaoAoHangar(int idHangar, Aviao aviao) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE hangar SET aviao_id = ? WHERE id = ?");
        stmt.setInt(1, aviao.getId());
        stmt.setInt(2, idHangar);
        stmt.execute();
    }

    public static void removerAviaoDoHangar(int idHangar) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE hangar SET aviao_id = NULL WHERE id = ?");
        stmt.setInt(1, idHangar);
        stmt.execute();
    }

    public static void updateHangar(int id, String input) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("UPDATE hangar SET local = ? WHERE id = ?");
        stmt.setString(1, input);
        stmt.setInt(2, id);
        stmt.execute();
    }

    public static void excluirHangar(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("DELETE FROM hangar WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        throw new Exception("Hangar não encontrado!");
    }

    public static Hangar getHangarById(int id) throws Exception {
        PreparedStatement stmt = DAO.createConnection().prepareStatement("SELECT * FROM hangar WHERE id = ?");
        stmt.setInt(1, id);
        stmt.execute();

        ResultSet rs = stmt.getResultSet();
        if (rs.next()) {
            Hangar hangar = new Hangar(
                rs.getInt("id"),
                rs.getString("local"));
            return hangar;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "\n | ID: " + getId() + 
               "\n | Local: " + getLocal();
    }
}
