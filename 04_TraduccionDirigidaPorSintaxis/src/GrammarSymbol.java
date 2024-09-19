import java.util.ArrayList;
import java.util.HashMap;

public class GrammarSymbol {
    public static final int TERMINAL = 0;
    public static final int NONTERMINAL = 1;
    public static final int STATE = 2;

    private final HashMap<String, Object> attributes;
    private final ArrayList<Integer> types;
    private int type;
    private String symbol;
    private String value;

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public ArrayList<Integer> getAttributeTypes() {
        return types;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public GrammarSymbol(int type){
        attributes = new HashMap<>();
        types = new ArrayList<>();
        this.type = type; // Asignar directamente en lugar de usar método
    }

    public GrammarSymbol(int type, String symbol){
        attributes = new HashMap<>();
        types = new ArrayList<>();
        this.type = type; // Asignar directamente
        this.symbol = symbol; // Asignar directamente
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
