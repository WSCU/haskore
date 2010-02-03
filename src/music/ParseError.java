/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

public class ParseError extends RuntimeException {

    public String error;
    public Token token;

    public ParseError(String error, Token token) {
        this.error = error;
        this.token = token;
    }

    @Override
    public String toString() {
        return error + "\n" + token.place.toString();
    }
}
