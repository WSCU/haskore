package music;

/**
 * The Functional Music project
 * 
 * This is the super-class relating to the Parser from which every Expression, 
 * Declaration and Pattern are derived.  It contains basic methods that every
 * Object in the parser needs, including typecasting, comparison and printing.
 * @author Western State College, CIS412 class
 */
public abstract class ParsedObject {

    /**
     * Token corresponding to the beginning of the Object being parsed.
     */
    Token firstToken;
    /**
     * Token corresponding to the end of the Object being parsed.
     */
    Token lastToken;

    /**
     * Typecasting validity Method.  This allows safe typecasting since every
     * subclass overrides the default as necessary.
     * @return Default is false unless overrided by a subclass
     */
    public boolean isPat() {
        return false;
    }

    /**
     * Typecasting validity Method.  This allows safe typecasting since every
     * subclass overrides the default as necessary.
     * @return Default is false unless overrided by a subclass
     */
    public boolean isDecl() {
        return false;
    }

    /**
     * Typecasting validity Method.  This allows safe typecasting since every
     * subclass overrides the default as necessary.
     * @return Default is false unless overrided by a subclass
     */
    public boolean isExp() {
        return false;
    }

    /**
     * Basic Comparison method for testing equality.  Checks to see if the 
     * typecasting methods are all true before testing for equality of contents
     * @param p The ParsedObject that will be compared for equality
     * @return true if each level of is____() succeeds and the contents are 
     * "equal", false if any typecasting fails along the way, or if they're
     * not "equal".
     */
    public abstract boolean compareTo(ParsedObject p);

    /**
     * Printing method for easy debugging.  Not necessarily consistent level of
     * information from class to class.
     * @return arbitrary String representation of the Object.
     */
    public abstract String print();
    //Possible second Print method for more complex debugging.
    //public abstract String deepPrint();
}

