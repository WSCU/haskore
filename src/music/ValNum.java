
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ValNum extends Value {
    public double val;
    public ValNum(double d) {   // Replace this if representation changes!
        val = d;
    }
    public ValNum(int num, int denom) {
        val = ((double) num) / ((double) denom);
    }
    @Override
    public boolean isNum() {
        return true;
    }
    @Override
    public String toString() 
    {
        return ("VN: " + val);
    }

}
