public class Operation {
    public static final int SHIFT = 0;
    public static final int REDUCE = 1;
    public static final int GOTO = 2;
    public static final int ACCEPT = 4;
    public static final int ERROR = 5;

    private int type;
    private int state;
    private int production;

    public Operation(int _type, int stateOrProduction){
        setType(_type);
        if ((_type == SHIFT) ||  (_type == GOTO))
            setState(stateOrProduction);
        else
            setProduction(stateOrProduction);
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getProduction() {
        return production;
    }
    public void setProduction(int production) {
        this.production = production;
    }
    
}
