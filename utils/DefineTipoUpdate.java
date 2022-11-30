package utils;
import classes.Aviao;
import classes.Companhia;
import classes.Helicoptero;
import classes.Jato;
import classes.Voo;

public class DefineTipoUpdate {
    
    public static <T> String[] getTipoDado(int tipo, String dado, T obj) throws Exception {

        if (obj instanceof Aviao) {
            String[] dados = new String[5];
            dados[tipo] = dado;
            return dados;
        } else if (obj instanceof Voo) {
            String[] dados = new String[6];
            dados[tipo] = dado;
            return dados;
        } else if (obj instanceof Companhia) {
            String[] dados = new String[2];
            dados[tipo] = dado;
            return dados;
        } else if (obj instanceof Helicoptero) {
            String[] dados = new String[5];
            dados[tipo] = dado;
            return dados;
        } else if (obj instanceof Jato) {
            String[] dados = new String[5];
            dados[tipo] = dado;
            return dados;
        } else {
            throw new Exception("Objeto não definido");
        }
    }
}
