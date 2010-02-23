package music;import java.io.*;import java.util.ArrayList;import static music.TokenType.*;/** * The Functional Music project * @author Western State College, CIS412 class */public class Lexer {    public static TokenStream tokens;    public static ArrayList<LexerError> error = new ArrayList<LexerError>();    public static final String opChars = "!&|=><+-.*/^%";    /**     * lexfile     * @param String filepath     * @return ArrayList<LexerError>     * The path to the Haskore source file is given which then creates     * an FileReader Object to open the file from the disk.     * The filepath variable is given to the FileReader constructor, which tries to     * open the file from the disk. If the file is not found, this method catches the IO     * exception and returns an empty ArrayList<LexerError> Object.     * The BufferedReader constructor then takes the open Haskore source file.     * This BufferedReader can be output strings from the source file line by line     * and this is exactly what is acheived. In a while loop that terminates when the current     * line is null, the string returned from the readline method in the BufferedReader is given to     * the <b>String</b> line. Every iteration of the while loop String line is getting a new value. The <b>String</b>     * content takes the value of <b>String</b> line on each iteration and appends it to itself plus a newline('\n').     * This creates one large string with newline chars ('\n') in between the lines.     * If any exceptions are caused during the loop, the exception is caught and an empty ArrayList<LexerError>.     * lexString is then called with the <b>String</b> content and the <b>String</b> filepath.     */    public static ArrayList<LexerError> lexFile(String filepath) {        FileReader f;        try {            f = new FileReader(filepath);        } catch (FileNotFoundException ex) {            return error;        }        BufferedReader in = new BufferedReader(f);        String line = "";        String content = "";        try {            while ((line = in.readLine()) != null) {                //Content appends the prevous line to itself with a '\n' in between.                content = content + line + "\n";            }        } catch (Exception exception) {            return error;        }        return lexString(content, filepath);    }    /**     * @param program     * @param fname     * @param string The string that is lexed     * @param fname the filename of the string beign lexed     * Takes the string and looks at every bit of it breaking it down into various     * tokens depending on what type they are.     * takes string and splits them into multiple segments using \n to differientate     * lineNum iterates through the lines seperated by \n then iterates through the     * characters in that line creating tokens     * the types of characters that are being searched for are:     * whitespace including "-", being saved in a token stream called whitetoken     * letters being saved in a token stream called vartoken     * digits being saved in a token stream called numbertoken     * punctuation such as [](),;{}_ being saved in a token stream called puncToken     * oporator such as !&|=><+- saved in the variable opChars being saved in a token     * @return     * stream called opToken     * if a character is found that does not fit any of those classes it creates a new     * array list called lexer error that stores the unknown characters     * at the end it will return the character \o or null     */    public static ArrayList<LexerError> lexString(String program, String fname) {        tokens = new TokenStream();        String[] lines = program.split("\n");        String line;        int lineNum = 0;        while (lineNum < lines.length) {            line = lines[lineNum];            int currentColumn = 0;            while (currentColumn < line.length()) {                char currentChar = line.charAt(currentColumn);                // Checks to see if this character is whitespace and creates a new token, the lexer then continues                // to look ahead in the string and advances the line and column numbers untill a charater is found                 // that isnt whitespace                if (Character.isWhitespace(currentChar)) {                    int whiteCol = currentColumn;                    String white = "" + currentChar;                    while (Character.isWhitespace(currentChar = peek(line, currentColumn))) {                        currentColumn++;                        white = white + currentChar;                    }                    tokens.addToken(new Token(Symbol.toSymbol(""), white, new Place(fname, lineNum, whiteCol), whiteToken));                } // Multiple lines of comments are represented by multiple lines of tokens                // each full line will be one token                else if (currentChar == '-' && peek(line, currentColumn) == '-') {                    String com = "";                    int col = currentColumn;                    while (currentColumn < line.length()) {                        com = com + line.charAt(currentColumn);                        currentColumn++;                    }                    tokens.addToken(new Token(null, com, new Place(fname, lineNum, col), whiteToken));                    //explain break                    break;                } //                 else if (Character.isLetter(currentChar)) {                    int varCol = currentColumn;                    String var = "" + currentChar;                    while (Character.isLetter(peek(line, currentColumn)) || Character.isDigit(peek(line, currentColumn))) {                        currentColumn++;                        currentChar = line.charAt(currentColumn);                        var = var + currentChar;                    }                                        tokens.addToken(new Token(Symbol.toSymbol(var), var, new Place(fname, lineNum, varCol), varToken));                }                else if (Character.isDigit(currentChar)) {                    //allow 2.2 cant do 2.                     int digitCol = currentColumn;                    String digit = "" + currentChar;                    //This boolean logs if dec has ever been true                    boolean hasDecimal = false;                    while (Character.isDigit(peek(line, currentColumn)) || '.' == peek(line, currentColumn)) {                        //Setting true the fact that the number has at least one decimal                        currentColumn++;                        currentChar = line.charAt(currentColumn);                        digit = digit + currentChar;                        boolean dot = '.' == peek(line, currentColumn);                        if (hasDecimal && dot) {                            //error.add(new LexerError(currentChar + "", "that Decimal number has only one '.' ", lineNum, currentChar,fname));                            break;                        }                        if (dot) {                            hasDecimal = true;                            currentColumn++;                            currentChar = line.charAt(currentColumn);                            digit = digit + currentChar;                            if (!Character.isDigit(peek(line, currentColumn))) {                                error.add(new LexerError(currentChar + "", "that a Decimal must follow in format example '3.3' not '3.' .", lineNum, currentChar, fname));                            }                        }                    }                    if (Character.isLetter(peek(line, currentColumn))) {                        error.add(new LexerError(line.charAt(currentColumn + 1) + "", "a space, operator, or punctuation", lineNum, currentColumn + 1, fname));                    }                    tokens.addToken(new Token(Symbol.toSymbol(digit), digit, new Place(fname, lineNum, digitCol), numberToken));                } else if ("[](),;{}".contains(Character.toString(currentChar))) {                    tokens.addToken(new Token(Symbol.toSymbol(Character.toString(currentChar)), Character.toString(currentChar), new Place(fname, lineNum, currentColumn), puncToken));                } else if (opChars.contains(Character.toString(currentChar))) {                    String op = Character.toString(currentChar);                    while (opChars.contains(Character.toString(peek(line, currentColumn)))) {                        currentColumn++;                        currentChar = line.charAt(currentColumn);                        op += currentChar;                    }                    tokens.addToken(new Token(Symbol.toSymbol(op), op, new Place(fname, lineNum, currentColumn), opToken));                } else {                    error.add(new LexerError(line.charAt(currentColumn) + "", "a valid character", lineNum, currentColumn + 1, fname));                }                currentColumn++;            }            lineNum++;        }        tokens.addToken(Token.eof);        return error;    }    private static char peek(String line, int colNum) {        if (colNum + 1 < line.length()) {            return line.charAt(colNum + 1);        }        return '\0';    }}