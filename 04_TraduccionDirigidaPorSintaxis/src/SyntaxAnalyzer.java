import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SyntaxAnalyzer {

    public static final int RESULT_ACCEPT = 0;
    public static final int RESULT_FAILED = 1;

    private final HashMap<Integer, HashMap<String, Operation>> parsingTable;
    private HashMap<Integer, Production> productions;
    private final Stack<GrammarSymbol> parsingStack;
    public SemanticAnalyzer semanticAnalyzer;

    public SyntaxAnalyzer() {
        semanticAnalyzer = new SemanticAnalyzer();
        parsingTable = new HashMap<>();
        createProductions();
        createParsingTable();        
        parsingStack = new Stack<>();
    }

    private void createProductions() {
        productions = new HashMap<>();
        productions.put(1, new Production("<E>", "<E>", "+", "<T>"));
        productions.get(1).getActions().add("sum_e_and_t_save_value");

        productions.put(2, new Production("<T>", "<T>", "*", "<F>"));
        productions.get(2).getActions().add("mult_t_and_f_save_value");

        productions.put(3, new Production("<E>", "<T>"));
        productions.put(4, new Production("<T>", "<F>"));
        productions.put(5, new Production("<F>", "num"));
    }

    private void createParsingTable() {
        for (int i = 0; i <= 11; i++) {
            parsingTable.put(i, new HashMap<>());
        }

        // Estado 0
        parsingTable.get(0).put("num", new Operation(Operation.SHIFT, 5));
        parsingTable.get(0).put("(", new Operation(Operation.SHIFT, 4));
        parsingTable.get(0).put("<E>", new Operation(Operation.GOTO, 1));
        parsingTable.get(0).put("<T>", new Operation(Operation.GOTO, 2));
        parsingTable.get(0).put("<F>", new Operation(Operation.GOTO, 3));

        // Estado 1
        parsingTable.get(1).put("+", new Operation(Operation.SHIFT, 6)); // Manejar token +
        parsingTable.get(1).put("$", new Operation(Operation.ACCEPT, 0));

        // Estado 2
        parsingTable.get(2).put("+", new Operation(Operation.REDUCE, 3)); // Reducir a E -> T
        parsingTable.get(2).put("*", new Operation(Operation.SHIFT, 7)); // Manejar token *
        parsingTable.get(2).put(")", new Operation(Operation.REDUCE, 3));
        parsingTable.get(2).put("$", new Operation(Operation.REDUCE, 3));

        // Estado 5
        parsingTable.get(5).put("+", new Operation(Operation.REDUCE, 5)); // Reducir a F -> num
        parsingTable.get(5).put("*", new Operation(Operation.REDUCE, 5));
        parsingTable.get(5).put(")", new Operation(Operation.REDUCE, 5));
        parsingTable.get(5).put("$", new Operation(Operation.REDUCE, 5));

        // Estado 6: Después de encontrar el operador +
        parsingTable.get(6).put("num", new Operation(Operation.SHIFT, 5));
        parsingTable.get(6).put("(", new Operation(Operation.SHIFT, 4));
        parsingTable.get(6).put("<T>", new Operation(Operation.GOTO, 8));

        // Estado 7: Después de encontrar el operador *
        parsingTable.get(7).put("num", new Operation(Operation.SHIFT, 5));
        parsingTable.get(7).put("(", new Operation(Operation.SHIFT, 4));
        parsingTable.get(7).put("<F>", new Operation(Operation.GOTO, 9));

        // Estado 8: Finalizar la suma
        parsingTable.get(8).put("+", new Operation(Operation.REDUCE, 1)); // Reducir a E -> E + T
        parsingTable.get(8).put("*", new Operation(Operation.SHIFT, 7)); // Continuar con multiplicación
        parsingTable.get(8).put(")", new Operation(Operation.REDUCE, 1));
        parsingTable.get(8).put("$", new Operation(Operation.REDUCE, 1));

        // Estado 9: Finalizar la multiplicación
        parsingTable.get(9).put("+", new Operation(Operation.REDUCE, 2)); // Reducir a T -> T * F
        parsingTable.get(9).put("*", new Operation(Operation.REDUCE, 2));
        parsingTable.get(9).put(")", new Operation(Operation.REDUCE, 2));
        parsingTable.get(9).put("$", new Operation(Operation.REDUCE, 2));
    }

    public int parse(ArrayList<Lexema> tokens) {
        GrammarSymbol state0 = new GrammarSymbol(GrammarSymbol.STATE, "0");
        parsingStack.push(state0);

        for (int i = 0; i < tokens.size(); i++) {
            Lexema token = tokens.get(i);
            System.out.println("Procesando token: " + token.getSymbol()); // Depuración de tokens

            if (parsingStack.peek().getType() == GrammarSymbol.STATE) {
                Operation toDo = parsingTable.get(Integer.parseInt(parsingStack.peek().getSymbol())).get(token.getSymbol());

                if (toDo != null) {
                    System.out.println("Operación: " + toDo.getType()); // Depuración de operaciones

                    switch (toDo.getType()) {
                        case Operation.SHIFT -> {
                            parsingStack.push(token);
                            parsingStack.push(new GrammarSymbol(GrammarSymbol.STATE, String.valueOf(toDo.getState())));  // Corrección de conversión de String
                        }
                        case Operation.REDUCE -> {
                            Production prod = productions.get(toDo.getProduction());
                            System.out.println("Reduciendo con producción: " + prod.getNonTerminalKey()); // Depuración de reducción

                            int index = prod.getSymbols().length - 1;
                            i--;
                            ArrayList<GrammarSymbol> inputforSemanticActions = new ArrayList<>();

                            while (index >= 0) {
                                if (!prod.getSymbols()[index].equals("ε")) {
                                    parsingStack.pop();
                                    GrammarSymbol symbol = parsingStack.pop();

                                    if (symbol.getType() == GrammarSymbol.TERMINAL) {
                                        if (symbol.getSymbol().equals(prod.getSymbols()[index])) {
                                            GrammarSymbol semanticItem = new Lexema(
                                                ((Lexema) symbol).getSymbol(),
                                                ((Lexema) symbol).getValue()
                                            );
                                            inputforSemanticActions.add(semanticItem);
                                            index--;
                                        } else {
                                            return RESULT_FAILED;
                                        }
                                    } else if (symbol.getType() == GrammarSymbol.NONTERMINAL) {
                                        if (symbol.getSymbol().equals(prod.getSymbols()[index])) {
                                            inputforSemanticActions.add(symbol);
                                            index--;
                                        } else {
                                            return RESULT_FAILED;
                                        }
                                    }
                                }
                            }

                            GrammarSymbol nonTerminalKeyProd = new NonTerminalSymbol(prod.getNonTerminalKey());

                            if (!prod.getActions().isEmpty()) {
                                for (String action : prod.getActions()) {
                                    try {
                                        semanticAnalyzer.ExecuteOperation(action, inputforSemanticActions, nonTerminalKeyProd);
                                    } catch (Exception e) {
                                        return RESULT_FAILED;
                                    }
                                }
                            }

                            parsingStack.push(nonTerminalKeyProd);
                        }
                        case Operation.ACCEPT -> {
                            System.out.println("Aceptado!"); // Depuración de aceptación
                            return RESULT_ACCEPT;
                        }
                        default -> {
                            System.out.println("Error en la operación.");
                            return RESULT_FAILED;
                        }
                    }
                } else {
                    System.out.println("No se encontró operación para el token: " + token.getSymbol()); // Depuración de error
                    return RESULT_FAILED;
                }
            }
        }
        return RESULT_ACCEPT;
    }
}
