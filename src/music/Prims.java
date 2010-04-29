package music;

import java.util.ArrayList;

public class Prims {

    private static final BigRational WHOLE = new BigRational("1");

    public static Env topEnv() {
        EnvHash result = new EnvHash(1000, null);
        // 1st argument should be roughly four times the number of
        // primatives in the system

        Prim upfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg1 = args.get(0).asNum();
                ValMusic arg2 = args.get(1).asMusic();
                return new ValMusic(Music.up(arg2.val, arg1.val.toInt()));
            }
        };
        result.add(Symbol.toSymbol("up", true), new Thunk(new ValFuncPrim(2, upfn)));
        //ENTER HERE


        Prim downfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValMusic arg1 = args.get(1).asMusic();
                ValNum arg0 = args.get(0).asNum();
                return new ValMusic(Music.down(arg1.val, arg0.val.toInt()));
            }
        };
        result.add(Symbol.toSymbol("down", true), new Thunk(new ValFuncPrim(2, downfn)));

        Prim hnfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {

                ValMusic arg1 = args.get(0).asMusic();
                return new ValMusic(Music.changeDuration(arg1.val, new BigRational("1/2")));
            }
        };
        result.add(Symbol.toSymbol("h", true), new Thunk(new ValFuncPrim(1, hnfn)));
        Prim dhnfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {

                ValMusic arg1 = args.get(0).asMusic();
                return new ValMusic(Music.changeDuration(arg1.val, new BigRational("3/4")));
            }
        };
        result.add(Symbol.toSymbol("h", true), new Thunk(new ValFuncPrim(1, dhnfn)));

        Prim qnfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {

                ValMusic arg1 = args.get(0).asMusic();
                return new ValMusic(Music.changeDuration(arg1.val, new BigRational("1/4")));
            }
        };
        result.add(Symbol.toSymbol("q", true), new Thunk(new ValFuncPrim(1, qnfn)));

        Prim enfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {

                ValMusic arg1 = args.get(0).asMusic();
                return new ValMusic(Music.changeDuration(arg1.val, new BigRational("1/8")));
            }
        };
        result.add(Symbol.toSymbol("e", true), new Thunk(new ValFuncPrim(1, enfn)));
        Prim snfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {

                ValMusic arg1 = args.get(0).asMusic();
                return new ValMusic(Music.changeDuration(arg1.val, new BigRational("1/16")));
            }
        };
        result.add(Symbol.toSymbol("s", true), new Thunk(new ValFuncPrim(1, snfn)));
        Prim tnfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {

                ValMusic arg1 = args.get(0).asMusic();
                return new ValMusic(Music.changeDuration(arg1.val, new BigRational("1/32")));
            }
        };
        result.add(Symbol.toSymbol("t", true), new Thunk(new ValFuncPrim(1, tnfn)));


        Prim volumefn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValMusic arg1 = args.get(1).asMusic();
                ValNum arg0 = args.get(0).asNum();
                System.out.println("Prims-Volume: " + arg1.val + " " + arg0.val.toInt());
                return new ValMusic(Music.withVelocity(arg1.val, arg0.val.toInt()));
            }
        };
        result.add(Symbol.toSymbol("volume", true), new Thunk(new ValFuncPrim(2, volumefn)));

        Prim ifMusicfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                Thunk arg1 = args.get(1);
                Thunk arg2 = args.get(2);
                return (Lib.ifMusic(arg0, arg1, arg2));
            }
        };
        result.add(Symbol.toSymbol("if"), new Thunk(new ValFuncPrim(3, ifMusicfn)));

        Prim plusfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.plus(arg0.val, arg1.val));
            }
        };
        result.add(Symbol.toSymbol("+"), new Thunk(new ValFuncPrim(2, plusfn)));

        Prim minusfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.minus(arg0.val, arg1.val));
            }
        };
        result.add(Symbol.toSymbol("-"), new Thunk(new ValFuncPrim(2, minusfn)));


        Prim multiplyfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.multiply(arg0.val, arg1.val));
            }
        };
        result.add(Symbol.toSymbol("*"), new Thunk(new ValFuncPrim(2, multiplyfn)));

        Prim dividefn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.divide(arg0.val, arg1.val));
            }
        };
        result.add(Symbol.toSymbol("/"), new Thunk(new ValFuncPrim(2, dividefn)));

        Prim powerfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.power(arg0.val, arg1.val));
            }
        };
        result.add(Symbol.toSymbol("^"), new Thunk(new ValFuncPrim(2, powerfn)));

        Prim modfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.mod(arg0.val, arg1.val));
            }
        };
        result.add(Symbol.toSymbol("%"), new Thunk(new ValFuncPrim(2, modfn)));

        Prim EqualsEqualsfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                Thunk arg0 = args.get(0);
                Thunk arg1 = args.get(1);
                // this evaluates arguments so we can see the values that are in the thunks
                // must call eval before checking value types.
                arg0.eval();
                arg1.eval();
                if (arg0.v.isBool() && arg1.v.isBool()) {
                    return new ValBool(Lib.compare(args.get(0).asBool(), args.get(1).asBool()));
                } else if (arg0.v.isNum() && arg1.v.isNum()) {
                    return new ValBool(Lib.compare(arg0.asNum().val, arg1.asNum().val));
                } else {
                    throw new ExecutionError("Use bools or num, never mix them.");
                }
            }
        };
        result.add(Symbol.toSymbol("=="), new Thunk(new ValFuncPrim(2, EqualsEqualsfn)));

        Prim andfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                Thunk arg0 = args.get(0);
                Thunk arg1 = args.get(1);
                // this evaluates arguments so we can see the values that are in the thunks
                // must call eval before checking value types.
                arg0.eval();

                if (arg0.v.isBool()) {
                    if (!((ValBool) arg0.v).val) {
                        return arg0.v;
                    } else {
                        arg1.eval();
                        if (arg1.v.isBool()) {
                            if (!((ValBool) arg1.v).val) {
                                return arg1.v;
                            }
                            return arg1.v;
                        } else {
                            throw new ExecutionError("Use bools or num, never mix them.");
                        }
                    }
                }
                else {
                    throw new ExecutionError("Use bools or num, never mix them.");
                }
            }
        };
        result.add(Symbol.toSymbol("&&"), new Thunk(new ValFuncPrim(2, andfn)));


        Prim orfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                Thunk arg0 = args.get(0);
                Thunk arg1 = args.get(1);
                // this evaluates arguments so we can see the values that are in the thunks
                // must call eval before checking value types.
                arg0.eval();

                if (arg0.v.isBool()) {
                    if (!((ValBool) arg0.v).val) {
                        arg1.eval();
                        if (arg1.v.isBool()) {
                            if (!((ValBool) arg1.v).val) {
                                return arg1.v;
                            }
                            return arg1.v;
                        }
                        else {
                          throw new ExecutionError("Use bools or num, never mix them.");
                        }
                    }
                    return arg0.v;
                }
                else {
                  throw new ExecutionError("Use bools or num, never mix them.");
                }
            }
            
        };
        result.add(Symbol.toSymbol("||"), new Thunk(new ValFuncPrim(2, orfn)));

        Prim emptyfn = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                return new ValMusic(MusNote.empty());
            }
        };
        result.add(Symbol.toSymbol("empty", true), new Thunk(new ValFuncPrim(0, emptyfn)));

        Prim musafter = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValMusic arg1 = args.get(0).asMusic();
                ValMusic arg2 = args.get(1).asMusic();
                return new ValMusic(new MusAfter(arg1.val, arg2.val));
            }
        };
        result.add(Symbol.toSymbol("&"), new Thunk(new ValFuncPrim(2, musafter)));

        Prim mustogether = new Prim() {

            public Value call(ArrayList<Thunk> args) {
                ValMusic arg1 = args.get(0).asMusic();
                ValMusic arg2 = args.get(1).asMusic();
                return new ValMusic(new MusTogether(arg1.val, arg2.val));
            }
        };
        result.add(Symbol.toSymbol("!"), new Thunk(new ValFuncPrim(2, mustogether)));

        //END


        String[] letters = {"c", "d", "e", "f", "g", "a", "b"};
        for (int i = 0; i < letters.length; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 0; k < 3; k++) {
                    String name = letters[i] + j;
                    if (k == 1) {
                        name += "s";
                    } else if (k == 2) {
                        name += "f";
                    }

                    result.add(Symbol.toSymbol(name), new Thunk(new ValMusic(Music.note(name, WHOLE, 70, "Piano"))));
                }
            }
        }
        result.add(Symbol.toSymbol("r"), new Thunk(new ValMusic(Music.note("r", WHOLE, 70, "Piano"))));

        //result.add(Symbol.toSymbol("c3"), new Thunk(new ValMusic(Music.note("c3", WHOLE, 50, "Piano"))));
        //c3 doesn't take any arguments because it is just a note.

        return result;
    }
}

