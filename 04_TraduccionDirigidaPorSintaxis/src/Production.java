import java.util.ArrayList;

public class Production {
    private String nonTerminalKey;
    private String[] symbols;
    private ArrayList<String> actions = new ArrayList<String>();

    public Production(String nonTerminalKey, String... symbols){
        setNonTerminalKey(nonTerminalKey);
        setSymbols(symbols);
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

    public ArrayList<String> getActions(){
        return actions;
    }

    public void setActions(String... _actions){
        for (String action : _actions){
            actions.add(action);
        }
    }
}
