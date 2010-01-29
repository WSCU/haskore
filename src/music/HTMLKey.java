
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */

public class HTMLKey {
    
    private static String color = "#0000FF";  // blue 
    
    public static String generate(Token t)
    {
        return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
    }

}
