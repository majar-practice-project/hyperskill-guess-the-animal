package animals.language.processor;

public class HyphenTrimmer implements StringProcessor{
    private final char HYPHEN = '-';
    @Override
    public String process(String text) {
        if(text.charAt(0)==HYPHEN || text.charAt(text.length()-1)==HYPHEN){
            return text.replaceAll("^-+|-+$", "");
        }
        return text;
    }
}
