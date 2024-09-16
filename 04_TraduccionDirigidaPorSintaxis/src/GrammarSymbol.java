import java.util.HashMap;
import java.util.ArrayList;

public class GrammarSymbol {
    public static final int TERMINAL = 0;
    public static final int NONTERMINAL = 1;
    public static final int STATE = 2;

    private HashMap<String, Object> attributes;
    private ArrayList<Integer> types;
    private int type;
    private String symbol;

    public HashMap<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, Object> attributes) {
        this.attributes = attributes;
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
        attributes = new HashMap<String, Object>();
        types = new ArrayList<Integer>();
        setType(type);
    }

    public GrammarSymbol(int type, String symbol){
        attributes = new HashMap<String, Object>();
        types = new ArrayList<Integer>();
        setType(type);
        setSymbol(symbol);
    }
}
