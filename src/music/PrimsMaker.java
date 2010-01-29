/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package music;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stu714162
 */
public class PrimsMaker {

    public static void generatePrims(File fileIn, File fileOut){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileIn));
            writer = new BufferedWriter(new FileWriter(fileOut));
            String input = reader.readLine();
            while(input != null){
                String[] pieces = input.split(",");
                String result = "  Prim "+pieces[0]+"fn = new Prim() { \n" +
                        "            public Value call(ArrayList<Thunk> args) { \n";
                for (int i = 0; i < pieces[2].length(); i++) {
                    //Strings and other types would go in here!
                    if (pieces[2].charAt(i) == 'N')
                        result += "                ValNum arg"+i+" = args.get("+i+").asNum(); \n";
                    else if(pieces[2].charAt(i) == 'M')
                        result += "                ValMusic arg"+i+" = args.get("+i+").asMusic(); \n";
                    else if(pieces[2].charAt(i) == 'T')
                        result += "                 Thunk arg"+i+" = args.get("+i+"); \n";
                }
                if (pieces[3].charAt(0) == 'M')
                    result +=  "                return new ValMusic("+pieces[1]+"(";
                else if (pieces[3].charAt(0) == 'N')
                    result +=  "                return new ValNum("+pieces[1]+"(";
                else if (pieces[3].charAt(0) == 'T')
                    result +=  "                return ("+ pieces[1]+"(";

                for (int i = 0; i < pieces[2].length(); i++){
                    if (pieces[3].charAt(0) == 'T'){
                        result += "arg"+i;
                    }
                    else{
                        result += "arg"+i+".val";
                    }
                    if (i < pieces[2].length()-1) result += ", ";
                }
                result += ")); \n"+
                "            } \n"+
                "       };\n"+
                "result.add(Symbol.toSymbol(\""+pieces[0]+"\"), new Thunk(new ValFuncPrim("+pieces[2].length()+","+pieces[0]+"fn)));\n";
                System.out.println(result);
                writer.write(result);
                writer.newLine();
            input = reader.readLine();
            }
            reader.close();
            writer.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getClass());
            throw new ExecutionError("Cannot read file!!");
        }
   }
    public static void main(String[] args)
    {
        generatePrims(new File("S:\\music\\vocabulary.csv"), new File("S:\\music\\java.txt"));
//        generatePrims(new File("C:\\Documents and Settings\\Casey\\My Documents\\Western\\MusicLand\\vocabulary.csv"),
//                new File("C:\\Documents and Settings\\Casey\\My Documents\\Western\\MusicLand\\java.txt"));
    }

}
