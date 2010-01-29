package music;

import java.util.ArrayList;

public class Prims {
    private static final double WHOLE = 1.0;
    public static Env topEnv() {
        EnvHash result = new EnvHash(1000, null);
        // 1st argument should be roughly four times the number of
        // primatives in the system
        
        Prim upfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValNum arg1 = args.get(0).asNum();
                ValMusic arg2 = args.get(1).asMusic();
                return new ValMusic(Music.up(arg2.val, (int)arg1.val));
            }
        };

        //ENTER HERE


  Prim downfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValMusic arg0 = args.get(0).asMusic();
                ValNum arg1 = args.get(1).asNum();
                return new ValMusic(Music.down(arg0.val, arg1.val));
            }
       };
result.add(Symbol.toSymbol("down"), new Thunk(new ValFuncPrim(2,downfn)));

  Prim volumefn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValMusic arg0 = args.get(0).asMusic();
                ValNum arg1 = args.get(1).asNum();
                return new ValMusic(Music.withVelocity(arg0.val, arg1.val));
            }
       };
result.add(Symbol.toSymbol("volume"), new Thunk(new ValFuncPrim(2,volumefn)));

  Prim ifMusicfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                 Thunk arg1 = args.get(1);
                 Thunk arg2 = args.get(2);
                return (Lib.ifMusic(arg0, arg1, arg2));
            }
       };
result.add(Symbol.toSymbol("if"), new Thunk(new ValFuncPrim(3,ifMusicfn)));

  Prim plusfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.plus(arg0.val, arg1.val));
            }
       };
result.add(Symbol.toSymbol("+"), new Thunk(new ValFuncPrim(2,plusfn)));

  Prim minusfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.minus(arg0.val, arg1.val));
            }
       };
result.add(Symbol.toSymbol("-"), new Thunk(new ValFuncPrim(2,minusfn)));

  Prim EqualsEqualsfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                ValNum arg0 = args.get(0).asNum();
                ValNum arg1 = args.get(1).asNum();
                return new ValNum(Lib.compare(arg0.val, arg1.val));
            }
       };
result.add(Symbol.toSymbol("=="), new Thunk(new ValFuncPrim(2,EqualsEqualsfn)));

  Prim emptyfn = new Prim() {
            public Value call(ArrayList<Thunk> args) {
                return new ValMusic(MusNote.empty());
            }
       };
result.add(Symbol.toSymbol("empty"), new Thunk(new ValFuncPrim(0,emptyfn)));



        //END

        result.add(Symbol.toSymbol("up"), new Thunk(new ValFuncPrim(2,upfn)));
        String[] letters = {"c","d","e","f","g","a","b"};
        for (int i = 0; i < letters.length; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 0; k < 3; k++) {
                    String name = letters[i]+j;
                    if (k==1) name += "s";
                    else if (k==2) name += "f";
                    result.add(Symbol.toSymbol(name), new Thunk(new ValMusic(Music.note(name, WHOLE, 50, "Piano"))));
                }
            }
        }

        result.add(Symbol.toSymbol("c3"), new Thunk(new ValMusic(Music.note("c3", WHOLE, 50, "Piano"))));
        //c3 doesn't take any arguments because it is just a note.

        return result;
    }
}
