package utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Mascara {

    String getInput();

    public static boolean isValida(String input, String regex) {
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}