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
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Symbol.init();
//        File musProg = new File("C:\\Documents and Settings\\Casey\\My Documents\\Western\\MusicLand\\musProg.txt");
//        File musProg = new File("s:\\music\\musProg.txt");
        String prog = "l = up 3 c3;";
//        String prog = "f x = up 3 x; \ng = f c3;\n";
//        String prog = "m = if (1 == 2) c3 d3;";
//        String prog = "l = up (3 + 2) c3;";

//        String prog = "f c m = if (c == 5) m (f (c + 1) (m & up m 2)); \n m = f 0 c3;";

        StringReader converter = new StringReader(prog);
//        StringReader converter = new StringReader(musProg);
//        BufferedReader reader = new BufferedReader(new FileReader(musProg));
        BufferedReader in = new BufferedReader(converter);
//        Trace.trace = true;
        Env top = Prims.topEnv();
        EnvHash userEnv = new EnvHash(4000, top);
        TokenStream t = new TokenStream();

//            ArrayList<LexerError> errs = Lexer.lexFile("s:\\music\\musProg.txt");
        //used for single line tesxting
        ArrayList<LexerError> errs = Lexer.lexString(prog, "music error");
        // Check errs and continue if empty
        
        ArrayList<Decl> binds = Parser.parseDecls(Lexer.tokens);

        for ( Decl d : binds){
            System.out.println(d.LHS.asVar().getBody() + " = " + d.RHS.print());
        }
        // Catch parse errors
        // Env userEnv = Env.recBinds(top, binds);
        // Make a thunk out of the rhs

        // Create top level env
        for (Decl d : binds) {
            userEnv.add(d.LHS.asVar(), new Thunk(userEnv, d.RHS));
        }
        // Need to remember the names on the lhs of the decls
        // Get first declaraction and find rhs
        try {
            for (Decl d : binds) {
                Value result = userEnv.eval(d.LHS.asVar());
                // if result is valMusic write a midi file other wise skip over
                if (result.isMusic()) {
                    //file name should be the same as the variable name in the ENV preformer
                    ValMusic temp = ((ValMusic) result);
                    Performance performer = temp.val.perform(0, new Modifier());
                    performer.writeToFile("c:\\jcp\\music\\" + d.LHS.asVar().getBody() + ".midi");
                    System.out.println("Music: " + d.LHS.asVar().getBody() + "\n " + temp.val.prettyPrint());
//                  performer.writeToFile("c:\\" + d.LHS.asVar().getBody() + ".midi");
                } else {
                    //skip over to next piece of result
                    System.out.println(d.LHS.asVar().getBody() + " Not Music");
                }
            }
        } catch (ExecutionError e) {
            System.out.println("Execution Error: " + e.msg);
        }
    }
}