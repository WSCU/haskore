package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class HTMLVar {

    private static String color = "#228B22";  // green

    public static String generate(Token t) {
        return "<span style=\"color:" + color + ";\">" + t.body + "</span>";
    }
}
