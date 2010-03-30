package music;/** * The Functional Music project * @author Western State College, CIS412 class */import java.util.ArrayList;import static music.TokenType.*;public class Parser {        public static ArrayList<Decl> parseDecls(TokenStream t) {        ArrayList<Decl> res = new ArrayList<Decl>();        TokenStream toks = t;        while (!toks.peek().sameToken(Token.eof)) {            if (toks.peek().isPunct(Symbol.semicolon)) {                toks.next();            } else {                try{                    res.add(parseDecl(toks));                }                catch(ParseError e)                {                    Token thistok = e.token;                    thistok.type = errorToken;                    t.addToken(thistok);                }            }        }        return res;    }    public static Decl parseDecl(TokenStream toks) throws ParseError {        Token firstToken = toks.peek();        ArrayList<Pat> lhs = parselhs(toks);        requirePunct(Symbol.equals, toks);        Exp rhs = parseExp(toks);        requirePunct(Symbol.semicolon, toks);        if (lhs.size() == 1) {            return new DeclVal(firstToken, lhs.get(0), rhs);        } else {            //for loop from back to front for recursive lambdas against entire            //length of lhs.            Pat fn = lhs.get(0);            for (int i = lhs.size() - 1; i > 0; i--) {                rhs = new ExpLambda(lhs.get(i), rhs);            }            return new DeclVal(firstToken, fn, rhs);        }    }    /*     * LHS stands for Left-hand-side.     * As the name thus implies, we are parsing the left hand side of equations.     * for example if we have "phrase4 ="  this method will return "phrase4 ".     * in this case, phrase4 is a pat, because it is the variable on the left     * side of an '='     */    public static ArrayList<Pat> parselhs(TokenStream toks) throws ParseError {        ArrayList<Pat> lhs = new ArrayList<Pat>();        while (!toks.peek().isPunct(Symbol.equals)) {            lhs.add(parseAPat(toks));        }        return lhs;    }    public static Pat parseAPat(TokenStream toks) throws ParseError {        return parsePat(toks);    }    /*     * this method looks at what is on the left side of an "=" and then figures     * out what it is, turning it into a pat of the respective type.     */    public static Pat parsePat(TokenStream toks) throws ParseError {        Token t = toks.next();        if (t.isNumber()) {            return new PatConst(t);        } else if (t.isVar()) {            return new PatVar(t);        } else if (t.isPunct(Symbol.underscore)) {            return new PatAny();        } else if (t.isPunct(Symbol.openParen)) {            ArrayList<Pat> pats = new ArrayList<Pat>();            while (true) {                pats.add(parsePat(toks));                if (toks.peek().isPunct(Symbol.closedParen)) {                    break;                }                requirePunct(Symbol.comma, toks);            }            return pats.size() == 1 ? pats.get(0) : new PatTuple(pats);        } else if (t.isPunct(Symbol.openBracket)) {            ArrayList<Pat> pats = new ArrayList<Pat>();            while (true) {                pats.add(parsePat(toks));                if (toks.peek().isPunct(Symbol.closedBracket)) {                    break;                }                requirePunct(Symbol.comma, toks);            }            return new PatList(pats);        } else {            //error.add(new ParseError("Illegal character: " + t.toString(), t));            throw new ParseError("Illegal character: " + t.toString(), t);        }    }    // Ignore unary operators for now ...    public static Exp parseExp(TokenStream toks) throws ParseError {        ArrayList<Exp> exps = new ArrayList<Exp>();        ArrayList<Token> ops = new ArrayList<Token>();        Exp e1 = parseFexp(toks);   // we'll miss a unary -        exps.add(e1);        while (toks.peek().isOp()) {            Token op = toks.next();            prattReduce(exps, ops, op);            ops.add(op);            Exp e2 = parseFexp(toks);            exps.add(e2);        }        prattReduce(exps, ops, null);        return exps.get(0);    }    public static void prattReduce(ArrayList<Exp> exps, ArrayList<Token> ops, Token op) throws ParseError {        if (ops.size() > 0) {  // nothing to reduce if no operators left            if (op != null) {  // The following code will exit if no reduce                // null means to always reduce                String leftPrec = getPrec(ops.get(ops.size() - 1).symbol);                String rightPrec = getPrec(op.symbol);                if (leftPrec.charAt(0) < rightPrec.charAt(0)) {                    return;                }                if (leftPrec.charAt(0) == rightPrec.charAt(0)) {                    if (leftPrec.charAt(1) == 'R' && rightPrec.charAt(1) == 'R') {                        return;                    }                    if (!(leftPrec.charAt(1) == 'L' && rightPrec.charAt(1) == 'L')) {                        throw new ParseError("Precedence Conflict", op);                    }                }            }            int le = exps.size() - 1;            int lo = ops.size() - 1;            Token reducedOp = ops.get(lo);            ops.remove(lo);            Exp e1 = exps.get(le - 1);            Exp e2 = exps.get(le);            exps.remove(le);            Exp opv = new ExpVar(reducedOp);            Exp a1 = new ExpCall(opv, e1);            Exp res = new ExpCall(a1, e2);            exps.set(le - 1, res);            prattReduce(exps, ops, op);        }    }    public static Exp parseFexp(TokenStream toks) throws ParseError {        Exp result = parseAExp(toks);        while (startsExp(toks.peek())) {            Exp arg = parseAExp(toks);            result = new ExpCall(result, arg);        }        return result;    }    public static Exp parseAExp(TokenStream toks) throws ParseError {        Token t = toks.next();        if (t.isNumber()) {            return new ExpConst(t);        } else if (t.isVar()) {            if (Symbol.toSymbol(t.body) == Symbol.trueKeyword || Symbol.toSymbol(t.body) == Symbol.falseKeyword){                return new ExpBool(t);            }else{                return new ExpVar(t);            }        }        else if (t.isPunct(Symbol.openParen)) {            ArrayList<Exp> exps = new ArrayList<Exp>();            while (true) {                exps.add(parseExp(toks));                // trailing comma doesn't exps.print()                if (toks.peek().isPunct(Symbol.closedParen)) {                    break;                }                requirePunct(Symbol.comma, toks);            }            requirePunct(Symbol.closedParen,toks );            return exps.size() == 1 ? exps.get(0) : new ExpTuple(exps);        } else if (t.isPunct(Symbol.ifKeyword))        {            Exp exp1 = parseExp(toks);            requirePunct(Symbol.thenKeyword, toks);            Exp exp2 = parseExp(toks);            requirePunct(Symbol.elseKeyword, toks);            Exp exp3 = parseExp(toks);            return new ExpIf(exp1, exp2, exp3);        }else if (t.isPunct(Symbol.openBracket)) {            ArrayList<Exp> exps = new ArrayList<Exp>();            while (true) {                exps.add(parseExp(toks));                // trailing comma doesn't                if (toks.peek().isPunct(Symbol.closedBracket)) {                    break;                }                requirePunct(Symbol.comma, toks);            }            return new ExpList(exps);        } else {            //error.add(new ParseError("Illegal character: " + t.toString(), t));            //System.out.println(t);            throw new ParseError("Illegal character: " + t.toString(), t);        }    }        /*     * RequirePunct is a method that checks a stream for punctuation. When you     * provide it with a symbol, it will check the next token in the stream,     * to verify it is the provided symbol.     * -Adam A.     */    public static void requirePunct(Symbol s, TokenStream toks) throws ParseError {        Token t = toks.next();        if (!t.isPunct(s)) {            throw new ParseError("Expecting:" + s.getBody(), t);        }        if (t.isType(TokenType.eofToken)) {            //error.add(new ParseError("Unexpected End Of File reached.", t));            throw new ParseError("Unexpected End Of File reached.", t);        }        //error.add(new ParseError("Expecting:" + s.getBody(), t));    }    private static boolean startsExp(Token peek) {        //checks to see if the Token is something that starts an expression.        return (peek.isNumber() || peek.isVar() || peek.isPunct(Symbol.openBracket) || peek.isPunct(Symbol.openParen));    }    // Return the precedence of an infix operator.  Returned as a string    // containing a digit and L/R/or N.    public static String getPrec(Symbol op) {        if( op == Symbol.powop)        {            return "9L";        }        if( op == Symbol.mulpop || op == Symbol.divop || op == Symbol.modop)        {            return "8L";        }        if (op == Symbol.plusop || op == Symbol.subop) {            return "4L";        }        if (op == Symbol.ampop) {            return "6L";        }        if (op == Symbol.bangop) {            return "7L";        }        if (op == Symbol.equalop) {            return "4N";        }        return "9N";    }}