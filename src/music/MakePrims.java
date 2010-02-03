package music;
/*
 * Author: Prof Peterson
 *
 * MakePrims.java
 *
 * Created on December 15, 2007, 3:25 PM     
 *
 */
//import java.math.*;

public class MakePrims {

    static int serial = 0;
    static String[][] prims = {{"!", "MMM", "MusTogether.musTogether"},
        {"up", "NMM", "thing"},
        {"c3", "M", "thingy"}};
//    static String [] unaryMathFns =
//    {"abs", "acos", "atan", "ceil", "cos", "exp", "floor", "log",
//     "round", "sin", "sqrt", "tan", "toDegrees", "toRadians"};
//    static String [] binaryMathFns = 
//    {"atan2", "max", "min", "pow"};
//    static String [] infixMathFns = {"+", "*", "/"};
//    static String [] infixCompare = {">", "<", "<=", ">="};

    public static void main(String args[]) {
        writePrims(prims);
//        writePrims(unaryMathFns, 0);
//        writePrims(binaryMathFns, 1);
//        writePrims(infixMathFns, 2);
//        writePrims(infixCompare, 3);
    }

    private static void writePrims(String[][] names) {
        String program = "";
        for (String[] name : names) {
            program = "Prim prim" + serial + "= new Prim() {\n";
            program += "    public Value call(ArrayList<Thunk> args) {\n";
            String type = name[1];
            String arguments = "";
            for (int i = 0; i < type.length() - 1; i++) {
                switch (type.charAt(i)) {
                    case 'M':
                        program += "        Music temp" + i + "= args.get(" + i + ").asMusic();\n";
                        break;
                    case 'N':
                        program += "        double temp" + i + "= args.get(" + i + ").asNum();\n";
                        break;
                }
                arguments += "temp" + i + (i + 1 < type.length() ? ", " : "");
            }
            program += "        return Value.value(" + name[2] + "(" + arguments + "));\n";
            program += "    public String name() {return \"" + name[0] + "\";};};\n";
            program += "result.addVar(\"" + name[0] + "\", new Thunk(new ValFunc(prim" + serial + ")));\n";
            serial++;
            System.out.println(program);
        }
    }
}
