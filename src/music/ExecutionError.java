
package music;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class ExecutionError extends RuntimeException {
    public String msg;
    public ExecutionError(String msg) {
        this.msg = msg;
    }
}
