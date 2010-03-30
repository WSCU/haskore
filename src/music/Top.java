package music;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
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
        StringReader converter = new StringReader(program);
        ArrayList<Pair<String, Value>> results = new ArrayList<Pair<String, Value>>();
        BufferedReader in = new BufferedReader(converter);
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        ArrayList<LexerError> errs = Lexer.lexString(program, "music error");
        //Pay attention to lex errors and to parse errors.
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);
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
        return results;
    }
}