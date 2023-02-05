package animals.language.intepreter;

import java.util.regex.Pattern;

public class BooleanInterpreter implements LanguageInterpreter<Boolean> {
    Pattern truePattern;
    Pattern falsePattern;

    public BooleanInterpreter(String trues, String falses){
        truePattern = Pattern.compile("(?i)"+trues+"[.!]?");
        falsePattern = Pattern.compile("(?i)"+falses+"[.!]?");
    }
    @Override
    public Boolean interpret(String text) {
        if(truePattern.matcher(text).matches()) return true;
        if(falsePattern.matcher(text).matches()) return false;
        throw new IllegalArgumentException("Invalid boolean text");
    }
}
