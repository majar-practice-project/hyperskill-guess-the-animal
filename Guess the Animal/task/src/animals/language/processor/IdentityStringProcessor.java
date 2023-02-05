package animals.language.processor;

public class IdentityStringProcessor implements StringProcessor{
    @Override
    public String process(String text) {
        return text;
    }
}
