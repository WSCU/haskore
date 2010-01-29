package music;

import java.util.ArrayList;

/**
 *
 * @author stu714162
 */
public class ValFuncPrim extends ValFunc{
    int arity;
    ArrayList<Thunk> args;
    Prim fn;

    public ValFuncPrim(int arity, Prim fn)
    {
        this.arity = arity;
        this.fn = fn;
        args = new ArrayList<Thunk>();
    }
    public ValFuncPrim(ValFuncPrim orig)
    {
        arity = orig.arity;
        fn = orig.fn;
        args = new ArrayList<Thunk>();
        if (orig.args != null)
            for (int i = 0; i < orig.args.size(); i++)
                args.add(orig.args.get(i));
    }

    @Override
    public Value apply(Thunk t) {
        ValFuncPrim temp = new ValFuncPrim(this);
        temp.args.add(t);
        return temp.args.size() == arity ? temp.fn.call(temp.args) : temp;
    }


}
