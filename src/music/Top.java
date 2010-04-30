package music;

import java.util.ArrayList;

public class Top {

    public static void Initialize()
    {
        String defs = "q x = faster 4 x\n";
        envDefs = Top.evaluateProgram(defs, Prims.topEnv()).second;
    }
    public static Env envDefs;
    //TODO
    //RE DO PRIMS
    //CHECK FOR LEXER ERRORS - LOWER DOWN
    /**
     * Runs a program and returns an ArrayList of Pair(variable name, value)
     * @param program
     * @return List of definition names and their evaluated
     */
    public static Pair<ArrayList<Pair<String, Value>>,Env> evaluateProgram(String program, Env top) {
        ArrayList<Pair<String, Value>> results = new ArrayList<Pair<String, Value>>();
        EnvHash userEnv = new EnvHash(4000, top);
        //Pay attention to lex errors and to parse errors.
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.lexString(program));
        binds = Desugaring.desugar(binds);
        for (Decl d : binds) {
            userEnv.add(d.LHS.asVar(), new Thunk(userEnv, d.RHS));
        }
        try {
            //assumes that every left hand side is a sple variable.
            for (Decl d : binds) {

                Value result = userEnv.eval(d.LHS.asVar());                
                results.add(new Pair(d.LHS.asVar().getBody(), result));
            }
        } catch (ExecutionError e) {                
            System.out.println("Execution Error: " + e.msg);
        }
        return new Pair<ArrayList<Pair<String, Value>>,Env>(results,userEnv);
    }
    public static ArrayList<Pair<String, Value>> evaluateProgram(String program) {
        return evaluateProgram(program,envDefs).first;
    }

    
    
}
