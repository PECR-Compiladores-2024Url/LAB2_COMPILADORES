import java.util.ArrayList;
import java.util.Collections;

public class Production {
    private String nonTerminalKey;
    private String[] symbols;
    private final ArrayList<String> actions = new ArrayList<>();

    public Production(String nonTerminalKey, String... symbols) {
        this.nonTerminalKey = nonTerminalKey;  // Asignar directamente en lugar de usar setter
        this.symbols = symbols;  // Asignar directamente en lugar de usar setter
    }

    public String getNonTerminalKey() {
        return nonTerminalKey;
    }

    public void setNonTerminalKey(String nonTerminalKey) {
        this.nonTerminalKey = nonTerminalKey;
    }

    public String[] getSymbols() {
        return symbols;
    }

    public void setSymbols(String[] symbols) {
        this.symbols = symbols;
    }

    public ArrayList<String> getActions() {
        return actions;
    }

    public void setActions(String... _actions) {
        Collections.addAll(actions, _actions);
    }
}
