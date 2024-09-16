public class SymbolItem {

    public static final int VARIABLE = 0;
    public static final int METHOD = 1;
    public static final int INT = 2;
    public static final int BOOLEAN = 3;
    public static final int GLOBAL = 4;
    public static final int LOCAL = 5;

    private String id;
    private int function;
    private int type;
    private int scope;
    private int memoryAddress;
    private Object value;

    public SymbolItem(String id, int function, int type, int scope, int memoryAddress, Object value){
        setId(id);
        setFunction(function);
        setType(type);
        setScope(scope);
        setMemoryAddress(memoryAddress);
        setValue(value);
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getFunction() {
        return function;
    }
    public void setFunction(int function) {
        this.function = function;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getScope() {
        return scope;
    }
    public void setScope(int scope) {
        this.scope = scope;
    }
    public int getMemoryAddress() {
        return memoryAddress;
    }
    public void setMemoryAddress(int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public Object getValue(){
        return value;
    }
    public void setValue(Object value){
        this.value = value;
    }
    
}
