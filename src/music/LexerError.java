package music;/** * The Functional Music project * @author Western State College, CIS412 class */public class LexerError extends RuntimeException {    public String recieved;    public String expected;    public Place place;      public LexerError(String recieved, String expected, int line, int col, String file) {        this.recieved = recieved;        this.expected = expected;        place = new Place(file,line,col);    }       @Override    public String toString(){        return "Error reading the program on line " + place.getLine() + " character number " + place.getCol() +                     ".  Found '" + recieved + "' when expected " + expected + ".";    }}