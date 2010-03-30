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

    public static Value ifMusic(ValNum test, Thunk t, Thunk e) {
        if (!test.val.isZero()) {
            return t.eval();
        } else {
            return e.eval();
        }
    }

    public static double plus(double x, double y) {
        return x + y;
    }

    public static double minus(double x, double y) {
        return x - y;
    }

    public static double multiply(double x, double y) {
        return x * y;
    }

    public static double divide(double x, double y) {
        return x / y;
    }

    public static double mod(double x, double y) {
        return x % y;
    }

    public static double power(double x, double y) {
        return Math.pow(x, y);
    }


    public static double compare(double x, double y) {
        if (x != y) {
            return 0;
        } else {
            return 1;
        }
    }

    public static BigRational plus(BigRational x, BigRational y) {
        return x.plus(y);
    }

    public static BigRational minus(BigRational x, BigRational y) {
        return x.minus(y);
    }

    public static BigRational multiply(BigRational x, BigRational y) {
        return x.times(y);
    }

    public static BigRational divide(BigRational x, BigRational y) {
        return x.divides(y);
    }

   public static BigRational mod(BigRational x, BigRational y) {
       return x;
    }

    public static BigRational power(BigRational x, BigRational y) {
        return x.power(y);
    }


    public static boolean compare(BigRational x, BigRational y) {
        return x.equals(y);
    }

    public static boolean compare(ValBool x, ValBool y) {
        return x.val==y.val;
    }


}
