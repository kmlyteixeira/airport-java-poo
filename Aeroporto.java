import java.util.Scanner;

import classes.Aeromodelo;
import classes.Companhia;
import classes.Hangar;
import classes.Pista;
import classes.Voo;

public class Aeroporto {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        do {

            System.out.println(" ============================================== ");
            System.out.println(" \n\n====== Bem vindo ao Aeroporto Jatinho Feliz ====== ");
            System.out.println(" ====== 01 - Cadastrar Aeromodelo            ====== ");
            System.out.println(" ====== 02 - Cadastrar Companhia             ====== ");
            System.out.println(" ====== 03 - Cadastrar Hangar                ====== ");
            System.out.println(" ====== 04 - Cadastrar Voo                   ====== ");
            System.out.println(" ====== 05 - Deletar Aeromodelo              ====== ");
            System.out.println(" ====== 06 - Deletar Companhia               ====== ");
            System.out.println(" ====== 07 - Deletar Hangar                  ====== ");
            System.out.println(" ====== 08 - Deletar Voo                     ====== ");
            System.out.println(" ====== 09 - Alterar Aeromodelo              ====== ");
            System.out.println(" ====== 10 - Alterar Companhia               ====== ");
            System.out.println(" ====== 11 - Alterar Hangar                  ====== ");
            System.out.println(" ====== 12 - Alterar Voo                     ====== ");
            System.out.println(" ====== 13 - Listar Aeromodelo               ====== ");
            System.out.println(" ====== 14 - Listar Companhia                ====== ");
            System.out.println(" ====== 15 - Listar Hangar                   ====== ");
            System.out.println(" ====== 16 - Listar Voo                      ====== ");
            System.out.println(" ====== 17 - Listar Pista                    ====== ");
            System.out.println(" ====== 18 - Cadastrar Pista                 ====== ");
            System.out.println(" ====== 19 - Alterar Pista                   ====== ");
            System.out.println(" ====== 20 - Deletar Pista                   ====== ");
            System.out.println(" ====== 21 - Sair                            ====== ");
            System.out.println(" ============================================== ");
            System.out.println(" \n\nDigite a opção desejada: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    Aeromodelo.CadastrarAeromodelo(sc);
                    break;
                case 2:
                    Companhia.CadastrarCompanhia(sc);
                    break;
                case 3:
                    Hangar.CadastrarHangar(sc);
                    break;
                case 4:
                    Voo.CadastrarVoo(sc);
                    break;
                case 5:
                    Aeromodelo.DeletarAeromodelo(sc);
                    break;
                case 6:
                    Companhia.DeletarCompanhia(sc);
                    break;
                case 7:
                    Hangar.DeletarHangar(sc);
                    break;
                case 8:
                    Voo.DeletarVoo(sc);
                    break;
                case 9:
                    Aeromodelo.AlterarAeromodelo(sc);
                    break;
                case 10:
                    Companhia.AlterarCompanhia(sc);
                    break;
                case 11:
                    Hangar.AlterarHangar(sc);
                    break;
                case 12:
                    Voo.AlterarVoo(sc);
                    break;
                case 13:
                    Aeromodelo.ListarAeromodelos();
                    break;
                case 14:
                    Companhia.ListarCompanhias();
                    break;
                case 15:
                    Hangar.ListarHangares();
                    break;
                case 16:
                    Voo.ListarVoo();
                    break;
                case 17:
                    Pista.ListarPistas();
                    break;
                case 18:
                    Pista.CadastrarPista(sc);
                    break;
                case 19:
                    Pista.AlterarPista(sc);
                    break;
                case 20:
                    Pista.DeletarPista(sc);
                    break;

                case 21:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 21);
    }
}
