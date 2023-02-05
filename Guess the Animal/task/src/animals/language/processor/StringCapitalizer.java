package animals.language.processor;

public class StringCapitalizer implements StringProcessor{
    @Override
    public String process(String text) {
        char firstChar = text.charAt(0);
        if(Character.isUpperCase(firstChar)) {
            return text;
        } else {
            return text.replaceFirst(Character.toString(firstChar), Character.toString(Character.toUpperCase(firstChar)));
        }
    }
}
