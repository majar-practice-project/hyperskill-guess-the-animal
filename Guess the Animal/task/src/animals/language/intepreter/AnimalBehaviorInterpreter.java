package animals.language.intepreter;

import animals.domain.AnimalProperty;
import animals.domain.FactVerb;
import animals.localization.ResourceBundleFactory;

import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalBehaviorInterpreter implements LanguageInterpreter<AnimalProperty> {
    private final Pattern factPattern = createFactPattern();

    @Override
    public AnimalProperty interpret(String text) {
        Matcher matcher = factPattern.matcher(text.trim());
        if(!matcher.matches()) throw new IllegalArgumentException("Invalid fact!");
        FactVerb propertyVerb = Arrays.stream(FactVerb.values())
                .filter(verb -> verb.getText().equals(matcher.group(1).toLowerCase()))
                .findFirst().orElseThrow();
        return new AnimalProperty(propertyVerb, matcher.group(2).toLowerCase());
    }

    private Pattern createFactPattern(){
        ResourceBundle res = ResourceBundleFactory.getResource();
        StringBuilder regexBuilder = new StringBuilder("(?i)");
        regexBuilder.append(res.getString("it"));
        regexBuilder.append(" (");
        Arrays.stream(FactVerb.values()).forEach(verb -> {
            regexBuilder.append(verb.getText());
            regexBuilder.append('|');
        });
        regexBuilder.append(") ([^?.]+)[?.]?");
        return Pattern.compile(regexBuilder.toString());
    }
}