import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        //кладем данные в строку
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine();

        List<Symbol> symbols = Symbol.analyze(inputString);
        System.out.println(unpackingString(symbols));

    }

    public static String unpackingString(List<Symbol> list) {
        int i = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (Symbol symbol : list) {
            if (symbol.symbolType == SymbolType.NUMBER) {
                i = Integer.parseInt(symbol.value);
            }
            if (symbol.symbolType == SymbolType.TEXT) {
                stringBuilder = stringBuilder.append(symbol.value.repeat(i));

            }
        }
        return stringBuilder.toString();
    }

}

