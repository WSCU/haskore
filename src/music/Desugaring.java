package music;

import java.util.ArrayList;

public class Desugaring {

    public static ArrayList<Decl> desugar(ArrayList<Decl> decls) {
        ArrayList<Decl> desugaredDecls = new ArrayList<Decl>();

        for (int i = 0; i < decls.size(); i++) {
            if (i < decls.size() - 1 && decls.get(i).LHS.asVar() == decls.get(i + 1).LHS.asVar()) {
                Pat pat1 = decls.get(i).LHS;
                ArrayList<Decl> repeats = new ArrayList<Decl>();
                repeats.add(decls.get(i));
                i++;
                while (i < decls.size() && decls.get(i).LHS.asVar() == pat1.asVar()) {
                    repeats.add(decls.get(i));
                    i++;
                }
                desugaredDecls.addAll(desugar2(repeats));
                i--;
            } else {
                desugaredDecls.add(decls.get(i));
            }
        }

        for (Decl d1 : desugaredDecls) {

            DeclVal dv = (DeclVal) d1;
            if (dv.args.size() > 0) {
                for (int j = dv.args.size() - 1; j >= 0; j--) {
                    if (dv.args.get(j).isVar()) {
                        dv.RHS = new ExpLambda(dv.args.get(j), dv.RHS);
                    }
                }
            }
            dv.args = new ArrayList<Pat>();
        }
        return desugaredDecls;
    }

    private static ArrayList<Decl> desugar2(ArrayList<Decl> decls) {
//Checking for correct arity
        int arity = -1;
        for (Decl d : decls) {
            DeclVal d1 = (DeclVal) d;
            if (arity == -1) {
                arity = d1.args.size();
            }
            if (d1.args.size() != arity) {
                throw new RuntimeException("You have a function with mismatched arity");
            }
        }
//Checking the last decl's args contain no constants
        DeclVal lastDecl = (DeclVal) decls.get(decls.size() - 1);
        Pat originalName = new PatVar(easyToken(lastDecl.LHS.asVar().toString()));
        ArrayList<Pat> args = lastDecl.args;
        for (Pat p : lastDecl.args) {
            if (p.isConst()) {
                throw new RuntimeException("The last function must have only variables for its arguments");
            }
        }
//Rename all functions
        int nameIterator = 0;
        for (Decl d : decls) {
            DeclVal dv = (DeclVal) d;
            PatVar p = (PatVar) dv.LHS;
            p.body.body = "$" + nameIterator + "_" + p.body.body;
            p.body.symbol = Symbol.toSymbol(p.body.body);
            nameIterator++;
        }
//Create boolean expression
        ExpIf ifStatement = bigIfStatement(decls);
        ArrayList<Pat> newArgs = new ArrayList<Pat>();
        for (int i = 0; i < arity; i++) {
            newArgs.add(new PatVar(easyToken("%_" + i)));
        }
//Create the new Decl
        Decl desugaredDecl = new DeclVal(((PatVar) originalName).body, originalName, ifStatement, newArgs);
        decls.add(desugaredDecl);
        return decls;
    }

    private static Token easyToken(String s) {
        try {
            Integer.parseInt(s);
            return new Token(Symbol.toSymbol(s), s, new Place(-99, -99), TokenType.numberToken);
        } catch (NumberFormatException e) {
            return new Token(Symbol.toSymbol(s), s, new Place(-99, -99), TokenType.varToken);
        }
    }

    private static ExpIf bigIfStatement(ArrayList<Decl> decls) {
        ExpIf result = new ExpIf(null, null, null);

        for (int j = decls.size() - 1; j >= 0; j--) {
            DeclVal dv = (DeclVal) decls.get(j);
            Exp ifexp = getIf(dv);
            Exp callexp = CallDecl(dv);
            if (result.elseExp == null) {
                result.elseExp = callexp;
            } else {
                if (result.thenExp == null) {
                    result.testExp = ifexp;
                    result.thenExp = callexp;
                } else {
                    result = new ExpIf(ifexp, callexp, result);
                }
            }
        }
        return result;
    }

    //Iterate through the args for dv and create one expcall to test them all
    private static Exp getIf(DeclVal dv) {
        Exp prevExp = null;
        for (int i = dv.args.size() - 1; i >= 0; i--) {
            Pat currentArg = dv.args.get(i);
            if (currentArg.isConst()) {
                ExpVar c1 = new ExpVar(easyToken("=="));
                // %_# is our name for the actual value given to the function
                ExpVar c2 = new ExpVar(easyToken("%_" + i));
                ExpConst c3 = new ExpConst(((PatConst) currentArg).body);
                ExpCall c4 = new ExpCall(c1, c2);
                ExpCall c5 = new ExpCall(c4, c3);
                if (prevExp == null) {
                    prevExp = c5;
                } else {
                    ExpVar c6 = new ExpVar(easyToken("&&"));
                    ExpCall c7 = new ExpCall(c6, prevExp);
                    prevExp = new ExpCall(c7, c5);
                }
            }
        }
        return prevExp;
    }

    private static ExpVar ArgAsExp(int i) {
        return new ExpVar(easyToken("%_" + i));
    }

// Gives the LHS with all the args for the new Decl being created
    private static Exp CallDecl(DeclVal d) {
        Exp result = new ExpVar(((PatVar) d.LHS).body);
        for (int i = 0; i < d.args.size(); i++) {
            Pat p1 = d.args.get(i);
            if (p1.isVar()) {
                result = new ExpCall(result, ArgAsExp(i));
            }
        }
        return result;
    }
}