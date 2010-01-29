
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */

public class HTMLComment {
    
    private static String color = "#BABABA";  // grey
    
    public static String generate(Token t)
    {
        if(!t.body.equals(" "))
         return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
        return t.body;
    }
    
}
