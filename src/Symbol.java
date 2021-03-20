import java.util.ArrayList;
import java.util.List;

public class Symbol {
    SymbolType symbolType;
    String value;

    public Symbol(SymbolType symbolType, Character value) {
        this.symbolType = symbolType;
        this.value = value.toString();
    }

    public Symbol(SymbolType symbolType, String value) {
        this.symbolType = symbolType;
        this.value = value.toString();
    }

    public static List<Symbol> analyze(String string) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        int pos = 0;
        while (pos < string.length()) {
            char c = string.charAt(pos);
            switch (c) {
                case '[':
                    symbols.add(new Symbol(SymbolType.LEFT_BRACKET, c));
                    pos++;
                    continue;
                case ']':
                    symbols.add(new Symbol(SymbolType.RIGHT_BRACKET, c));
                    pos++;
                    continue;
                default:
                    if (c <= 'z' && c >= 'a') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= string.length()) {
                                break;
                            }
                            c = string.charAt(pos);
                        }
                        while (c <= 'z' && c >= 'a');
                        symbols.add(new Symbol(SymbolType.TEXT, sb.toString()));
                    } else if (c <= '9' && c >= '0') {
                        StringBuilder sbNum = new StringBuilder();
                        do {
                            sbNum.append(c);
                            pos++;
                            if (pos >= string.length()) {
                                break;
                            }
                            c = string.charAt(pos);
                        } while (c <= '9' && c >= '0');
                        symbols.add(new Symbol(SymbolType.NUMBER, sbNum.toString()));
                    }
                    else {
                        if (c != ' ') {
                            throw new RuntimeException("Unexpected character: " + c);
                        }
                        pos++;
                    }
            }
        }
        symbols.add(new Symbol(SymbolType.EOF, ""));
        return symbols;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbolType=" + symbolType +
                ", value='" + value + '\'' +
                '}';
    }
}

