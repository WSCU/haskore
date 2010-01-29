
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class HTMLMusic {
    
    
    private static String color = "#FF7F24";  // orange
    
    public static String generate(Token t)
    {
        return "<span style=\"color:" + color + ";\"><a href='" + t.body + "'>" + t.body + "</a></span>";
    }

}
