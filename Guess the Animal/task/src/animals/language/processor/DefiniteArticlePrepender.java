package animals.language.processor;

public class DefiniteArticlePrepender implements StringProcessor{
    private final String invalidArticle = "(?i)^(an? )?";
    @Override
    public String process(String text) {
        return text.replaceAll(invalidArticle, "the ");
    }
}
