package music;

import java.util.ArrayList;

public class Top {

    //TODO
    //RE DO PRIMS
    //CHECK FOR LEXER ERRORS - LOWER DOWN
    /**
     * Runs a program and returns an ArrayList of Pair(variable name, value)
     * @param program
     * @return List of definition names and their evaluated
     */
    public static ArrayList<Pair<String, Value>> evaluateProgram(String program) {
        ArrayList<Pair<String, Value>> results = new ArrayList<Pair<String, Value>>();
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        //Pay attention to lex errors and to parse errors.
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.lexString(program));
        binds = Desugaring.desugar(binds);
        for (Decl d : binds) {
            userEnv.add(d.LHS.asVar(), new Thunk(userEnv, d.RHS));
        }
        try {
            //assumes that every left hand side is a simple variable.
            for (Decl d : binds) {
                Value result = userEnv.eval(d.LHS.asVar());                
                results.add(new Pair(d.LHS.asVar().getBody(), result));
            }
        } catch (ExecutionError e) {                
            System.out.println("Execution Error: " + e.msg);
        }
        return results;
    }

    
    
}
