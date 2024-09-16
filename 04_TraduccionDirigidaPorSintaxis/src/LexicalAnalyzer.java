import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    // Terminal tokens defined in the grammar
     private static final String[] TERMINALS = {
        "num", "+", "*", "(", ")"
    };

    // Patterns for matching tokens
    private static final Pattern TOKEN_PATTERN = Pattern.compile(
        "\\b([0-9])\\b|[\\+\\*\\(\\)]"
    );

    /**
     * Parses the input string and returns lexical tokens based on Grammar No. 1.
     * @param input The string to be parsed.
     * @return ArrayList<String> containing the lexical tokens.
     */
    public static ArrayList<Lexema> getLexicalTokens(String input) {
        ArrayList<Lexema> tokens = new ArrayList<Lexema>();
        Matcher matcher = TOKEN_PATTERN.matcher(input);

        // Find all matching tokens
        while (matcher.find()) {

            String token = matcher.group();

            if (token.matches("\\(") ) {
                tokens.add(new Lexema("(", token));
            } else if (token.matches("\\)") ) {
                tokens.add(new Lexema(")", token));
            } else if (token.matches("\\+") ) {
                tokens.add(new Lexema("+", token));
            } else if (token.matches("\\-")) {
                tokens.add(new Lexema("-", token));
            } else if (token.matches("[0-9]+")) {
                tokens.add(new Lexema("num", token));
            } 
        }

        tokens.add(new Lexema("$", "eof"));

        return tokens;
    }


}
