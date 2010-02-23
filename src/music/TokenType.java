package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public enum TokenType {

    /**
     * Indicates a number token.
     */
    numberToken,
    /**
     * Indicates a variable token.
     */
    varToken,
    /**
     * Indicates a puncuation token
     */
    puncToken,
    /**
     * Indicates an operator token.
     */
    opToken,
    /**
     * Indicates whitespace or a comment
     */
    whiteToken,
    /**
     * Indicates End of File;
     */
    eofToken,
    /**
     * Indicates dummy semicolon
     */
    semiToken;

}
