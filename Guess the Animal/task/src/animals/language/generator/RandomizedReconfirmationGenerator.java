package animals.language.generator;

import animals.language.generator.util.RandomArray;

public class RandomizedReconfirmationGenerator implements LanguageGenerator{
    private final RandomArray<String> reconfirmations;

    public RandomizedReconfirmationGenerator(String[] texts) {
        reconfirmations = new RandomArray<>(texts);
    }

    @Override
    public String generate() {
        return reconfirmations.get();
    }
}
