
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class Place {

    private String file;
    private int line;
    private int col;
    
    /**
     * Creates a blank place with and empty file name, line number=0
     * and column number=0
     */
    public Place(){
        this.file = "";
        this.line = 0;
        this.col = 0;
    }
    
    /**
     * Creates a place based on the selected file and the current line and cloumn number
     * @param file file name
     * @param line line number in the file
     * @param col column number in the line
     */
    public Place(String file, int line, int col){
        this.file = file;
        this.line = line;
        this.col = col;
    }
    
    /**
     * Uses a place as an argument and retrieves its file name, line number and column number
     * @param place Used to provide a file name, line number and column number
     */
    public Place(Place place){
        this.file = place.getFile();
        this.line = place.getLine();
        this.col = place.getCol();
    }
    
    /**
     * Retrieves the file name
     * @return returns a string with the file name
     */
    public String getFile(){
        return this.file;
    }
    
    /**
     * Retrieves the line number
     * @return Returns the specific line that is being looked at
     */
    public int getLine(){
        return this.line;
    }
    
    /**
     * Retrieves the column number
     * @return Returns the specific Column number that is being looked at
     */
    public int getCol(){
        return this.col;
    }
    
    @Override
    public String toString(){
        return "Filename: " + file + "\nLine Number: " + line + "\nColumn Number: " + col;
    }
    
    public boolean lineColEqual(Place p) {
        return p.col == col && p.line == line;
    }
}

