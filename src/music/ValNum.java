package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ValNum extends Value {

    public BigRational val;

    public ValNum(double d) {   // Replace this if representation changes!
        val = new BigRational(d);
    }

    public ValNum(BigRational d) {   // Replace this if representation changes!
        this.val = d;//could cause aliasing errors
        //this.val = new BigRational(d);
    }
    public ValNum(String d) {   // Replace this if representation changes!
        //could cause aliasing errors
        this.val = new BigRational(d);
    }

    



    public ValNum(int num, int denom) {
        val = new BigRational(num,denom);
    }

    @Override
    public boolean isNum() {
        return true;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
