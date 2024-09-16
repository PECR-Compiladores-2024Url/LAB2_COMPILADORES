public class Lexema extends GrammarSymbol{
    
    private String value;

    public Lexema(String symbol, String value){
        super(TERMINAL);
        setSymbol(symbol);
        setValue(value);
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    
}
