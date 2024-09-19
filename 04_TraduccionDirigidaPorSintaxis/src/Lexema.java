public class Lexema extends GrammarSymbol {

    private String value;

    public Lexema(String symbol, String value){
        super(TERMINAL);
        setSymbol(symbol);  // Usar setSymbol() en lugar de acceso directo
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
