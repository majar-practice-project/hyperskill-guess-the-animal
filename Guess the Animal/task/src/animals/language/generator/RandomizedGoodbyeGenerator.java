package animals.language.generator;

import animals.language.generator.util.RandomArray;

public class RandomizedGoodbyeGenerator implements GoodbyeGenerator {
    private final RandomArray<String> goodByePhrases;
    public RandomizedGoodbyeGenerator(String[] goodbyes){
        goodByePhrases = new RandomArray<>(goodbyes);
    }
    @Override
    public String generate() {
        return goodByePhrases.get();
    }
}
