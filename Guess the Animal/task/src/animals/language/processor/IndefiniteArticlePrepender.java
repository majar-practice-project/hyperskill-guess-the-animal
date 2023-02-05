package animals.language.processor;

import java.util.regex.Pattern;

public class IndefiniteArticlePrepender implements StringProcessor{
    private final String invalidArticle = "(?i)^(the )?";
    private final String indefiniteArticle = "(?i)^an? .+";

    private final String vowelStartingNoun = "(?i)^(the )?[aeiou].+";

    @Override
    public String process(String text) {
        boolean indefiniteArticleExist = Pattern.compile(indefiniteArticle).matcher(text).matches();
        if(indefiniteArticleExist) return text;

        boolean nounStartingWithVowel = Pattern.compile(vowelStartingNoun).matcher(text).matches();
        String article = nounStartingWithVowel ? "an " : "a ";

        return text.replaceAll(invalidArticle, article);
    }
}
