/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

/**
 *
 * @author stu619194
 */
public class ValBool extends Value {

    public boolean val;

    @Override
    public boolean isBool() {
        return true;
    }

    public ValBool(boolean b){
        this.val = b;
    }

    /**
     *
     * @param s will set the ValBool to true if the string is "true" (ignores case), anything else will be false
     */
    public ValBool(String s){
        this.val = Boolean.parseBoolean(s);
    }

    public ValBool (int i){
        if (i == 0){
            val = false;
        } else val = true;
    }
   
//    public static ValNum convertToNum(ValBool v){
//        if(v.val){
//            return new ValNum(1);
//        }
//        return new ValNum(0);
//    }

    @Override
    public String toString() {
        return "" + val;
    }
}