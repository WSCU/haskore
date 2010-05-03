package music;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Top {

    public static Env envDefs;

    public static void Initialize() {
        //String defs = "q x = faster 4 x\n";
        //String defs = "foo = 1;\n";
        String defs = "";
        try {
            FileInputStream fstream = new FileInputStream("prelude.hs");
            BufferedReader in = new BufferedReader(new InputStreamReader(fstream));
            String currentLine = in.readLine();
            while (currentLine != null) {
                defs += currentLine + "\n";
                currentLine = in.readLine();
            }
            System.out.println(defs);
        } catch (java.io.IOException e) {
            System.out.println("Can't find the prelude file");
        }


        Pair p0 = Top.evaluateProgram(defs, Prims.topEnv(), true);
        envDefs = (Env) p0.second;
        ArrayList<Pair<String, Value>> p01 = (ArrayList<Pair<String, Value>>) p0.first;
        for(Pair<String, Value> p: p01){
            System.out.println(p.first +" = "+  p.second);
        
        }
        //System.out.println(p01.);
        //envDefs = Top.evaluateProgram(defs, Prims.topEnv()).second;

    }
    //TODO
    //RE DO PRIMS
    //CHECK FOR LEXER ERRORS - LOWER DOWN

    /**
     * Runs a program and returns an ArrayList of Pair(variable name, value)
     * @param program
     * @return List of definition names and their evaluated
     */
    public static Pair<ArrayList<Pair<String, Value>>, Env> evaluateProgram(String program, Env top, Boolean prelude) {
        ArrayList<Pair<String, Value>> results = new ArrayList<Pair<String, Value>>();
        EnvHash userEnv = new EnvHash(4000, top);
        //Pay attention to lex errors and to parse errors.
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.lexString(program));
        binds = Desugaring.desugar(binds);
        for (Decl d : binds) {
            System.out.println(d.print());
            userEnv.add(d.LHS.asVar(), new Thunk(userEnv, d.RHS));
        }
        if (!prelude){
            try {
                //assumes that every left hand side is a simple variable.
                for (Decl d : binds) {

                    Value result = userEnv.eval(d.LHS.asVar());
                    results.add(new Pair(d.LHS.asVar().getBody(), result));
                }
            } catch (ExecutionError e) {
                System.out.println("Execution Error: " + e.msg);
            }
        }
        return new Pair<ArrayList<Pair<String, Value>>, Env>(results, userEnv);
    }

    public static ArrayList<Pair<String, Value>> evaluateProgram(String program) {
        return evaluateProgram(program, envDefs, false).first;
    }
}
