/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

/**
 *
 * @author Casey
 */
public class Lib {
//0 = false
    public static Value ifMusic(ValNum test, Thunk t, Thunk e){
        if(test.val != 0){
            return t.eval();
        }
        else
            return e.eval();
    }

    public static double plus(double x, double y){
        return x + y;
    }

    public static double minus(double x, double y){
        return x - y;
    }

    public static double compare(double x, double y){
        if(x != y)
            return 0;
        else return 1;
    }

}
