import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    
    public static void main(String[] args) {
        LexicalAnalyzer analizadorLexico = new LexicalAnalyzer();
        SyntaxAnalyzer analizadorSintactico = new SyntaxAnalyzer();

        try {
            System.out.println("Ingrese declaracion de variables: ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            ArrayList<Lexema> tokens = analizadorLexico.getLexicalTokens(input);
            int result = analizadorSintactico.parse(tokens);

            if (result == SyntaxAnalyzer.RESULT_ACCEPT){
                System.out.println("Escaneo exitoso");
            } else {
                System.out.println("Ocurrió un error");
            }
            
            
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}
