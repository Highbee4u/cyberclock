/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.util;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author MUSTAFA
 */
public class InputsManager {

    private Pattern partern;
    private Matcher match;
    private static final String REG = "[^a-zA-Z2-9]";
    private String inString;
    private boolean check;
    private boolean inputStat;
    
    public InputsManager(String in){
        inString = in;
        checkValid();
    }
    
    public InputsManager(int in){
        inString = String.valueOf(in);
        checkValid();
    }

    private void checkValid(){
        partern = Pattern.compile(REG);
        match = partern.matcher(inString);
        check = match.find();
        if(check){
            inputStat = false;
        }
        else{
            inputStat = true;
        }
    }

    public boolean isGoodInput(){
        return inputStat;
    }
}
