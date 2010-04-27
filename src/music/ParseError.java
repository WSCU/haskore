/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

public class ParseError extends Exception {

    public String error;
    public Token token;

    public ParseError(String error, Token token) {
        this.error = error;
        this.token = token;
    }

    @Override
    public String toString() {
        return error + " " + token.place.toString();
    }
    @Override
    public String getMessage()
    {
        return this.toString();
    }
}
