package music;/** * The Functional Music project * @author Western State College, CIS412 class */public abstract class Env {    public abstract Value eval(Symbol s);  // Return null if not found    public static EnvHash primEnv;}