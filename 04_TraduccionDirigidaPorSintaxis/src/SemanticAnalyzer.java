import java.util.ArrayList;

public class SemanticAnalyzer {

    public void ExecuteOperation(String operation, ArrayList<GrammarSymbol> lexemas, GrammarSymbol symbol) throws Exception {
        switch (operation) {
            case "sum_e_and_t_save_value" -> executeSum(lexemas, symbol);
            case "mult_t_and_f_save_value" -> executeMultiplication(lexemas, symbol);
            default -> throw new Exception("Operación no definida: " + operation);
        }
    }

    private void executeSum(ArrayList<GrammarSymbol> lexemas, GrammarSymbol symbol) throws Exception {
        Lexema lexema1 = (Lexema) lexemas.get(0);
        Lexema lexema2 = (Lexema) lexemas.get(2);

        int value1 = Integer.parseInt(lexema1.getValue());
        int value2 = Integer.parseInt(lexema2.getValue());

        int result = value1 + value2;

        symbol.setValue(String.valueOf(result));
    }

    private void executeMultiplication(ArrayList<GrammarSymbol> lexemas, GrammarSymbol symbol) throws Exception {
        Lexema lexema1 = (Lexema) lexemas.get(0);
        Lexema lexema2 = (Lexema) lexemas.get(2);

        int value1 = Integer.parseInt(lexema1.getValue());
        int value2 = Integer.parseInt(lexema2.getValue());

        int result = value1 * value2;

        symbol.setValue(String.valueOf(result));
    }
}
