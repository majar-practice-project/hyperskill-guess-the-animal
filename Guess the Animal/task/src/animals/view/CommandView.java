package animals.view;

import animals.domain.AnimalDistinguisher;
import animals.domain.AnimalProperty;
import animals.domain.FactVerb;
import animals.domain.decision.DecisionTree;
import animals.domain.decision.DecisionTreePrinter;
import animals.domain.decision.TreeStatistics;
import animals.language.generator.*;
import animals.language.intepreter.AnimalBehaviorInterpreter;
import animals.language.intepreter.BooleanInterpreter;
import animals.language.intepreter.LanguageInterpreter;
import animals.language.processor.*;
import animals.localization.ResourceBundleFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandView {
    private final Scanner scanner = new Scanner(System.in);
    private final ResourceBundle res = ResourceBundleFactory.getResource();
    private final BooleanInterpreter booleanInterpreter = (BooleanInterpreter) res.getObject("booleanInterpreter");
    private final GreetingGenerator greetingGenerator = (GreetingGenerator) res.getObject("greetingGenerator");
    private final GoodbyeGenerator goodbyeGenerator = (GoodbyeGenerator) res.getObject("goodbyeGenerator");;
    private final LanguageGenerator reconfirmationGenerator = (LanguageGenerator) res.getObject("reconfirmationGenerator");
    private final AnimalBehaviorInterpreter animalBehaviorInterpreter = new AnimalBehaviorInterpreter();
    private final StringProcessor definiteArticlePrepender = (StringProcessor) res.getObject("definiteArticlePrepender");
    private final StringProcessor indefiniteArticlePrepender = (StringProcessor) res.getObject("indefiniteArticlePrepender");
    private final StringProcessor articlePrefixRemover = (StringProcessor) res.getObject("articlePrefixRemover");
    private final StringCapitalizer capitalizer = new StringCapitalizer();
    private final DecisionTreePrinter treePrinter = new DecisionTreePrinter();

    private void prompt() {
        System.out.print("> ");
    }

    public int getChoice(){
        System.out.println(res.getObject("menu"));
        Set<String> validChoices = IntStream.range(0, 6).mapToObj(String::valueOf).collect(Collectors.toSet());
        String input = scanner.nextLine();
        while(!validChoices.contains(input)){
            prompt();
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

    public void showWelcome(){
        System.out.println(res.getString("welcome"));
    }

    public void showGreeting() {
        System.out.println(greetingGenerator.generate());
    }

    public void showGoodbye() {
        System.out.println(goodbyeGenerator.generate());
    }

    public String getFavoriteAnimal() {
        System.out.println(res.getString("askAnimal"));
        prompt();
        String animal = processAnimal(scanner.nextLine());
        System.out.println(res.getString("learnAnimals"));
        return animal;
    }

    public void showGameStart() {
        System.out.println(res.getString("gameStartPrompt"));
        prompt();
        scanner.nextLine();
    }

    public String getNewAnimal() {
        System.out.println(res.getString("askNewAnimal"));
        prompt();
        return processAnimal(scanner.nextLine());
    }

    public boolean confirmAnimal(String noun) {
        System.out.printf(FactVerb.IS.getInterrogativeText()+" %s?%n", indefiniteArticlePrepender.process(noun));
        prompt();
        return getBoolean();
    }

    public boolean confirmCorrectAnimal(String animal) {
        System.out.printf(res.getString("confirmStatement"), indefiniteArticlePrepender.process(animal));
        prompt();
        return getBoolean();
    }

    private boolean reconfirmBoolean() {
        while (true) {
            try {
                System.out.println(reconfirmationGenerator.generate());
                prompt();
                return booleanInterpreter.interpret(scanner.nextLine().trim());
            } catch (IllegalArgumentException ignored) {
            }
        }

    }

    public AnimalProperty getProperty(String animal1, String animal2) {
        System.out.printf(res.getString("askFact"),
                indefiniteArticlePrepender.process(animal1),
                indefiniteArticlePrepender.process(animal2));
        System.out.println(res.getString("formatPrompt"));
        prompt();
        return animalBehaviorInterpreter.interpret(scanner.nextLine());
    }

    public void showFacts(AnimalDistinguisher facts) {
        System.out.println(res.getString("learnedFollowingFact"));

        String availableAnimal = definiteArticlePrepender.process(facts.getAvailableAnimal());
        String unavailableAnimal = definiteArticlePrepender.process(facts.getUnavailableAnimal());
        AnimalProperty property = facts.getProperty();

        String availableFact = property.toAffirmativeString(availableAnimal);
        list(capitalizer.process(availableFact));

        String unavailableFact = property.toNegativeString(unavailableAnimal);
        list(capitalizer.process(unavailableFact));
        System.out.println(res.getString("learnAnimals"));
    }

    public void showAnimalProperties(Map<AnimalProperty, Boolean> properties, String animal){
        animal = definiteArticlePrepender.process(animal);
        if(properties.isEmpty()) {
            System.out.printf(res.getString("noFactsAvailable"), animal);
            return;
        }

        System.out.printf(res.getString("factsHeading"), animal);
        properties.forEach((property, ability) -> {
            String propertyText = ability ? property.toAffirmativeString(res.getString("it")) : property.toNegativeString(res.getString("it"));
            list(capitalizer.process(propertyText));
        });
    }

    public void showAnimals(List<String> animals){
        System.out.println(res.getString("listAnimalHeading"));
        animals.stream().sorted().forEach(this::list);
    }

    private void list(String element) {
        System.out.println(" - " + element);
    }

    public void showFactExamples() {
        System.out.println(res.getString("factExamples"));
    }

    public boolean getPlayAgain() {
        System.out.println(res.getString("playAgain?"));
        prompt();
        return getBoolean();
    }

    public boolean getQuestionResult(String question) {
        System.out.println(capitalizer.process(question));
        prompt();
        return getBoolean();
    }

    private boolean getBoolean() {
        try {
            return booleanInterpreter.interpret(scanner.nextLine().trim());
        } catch (IllegalArgumentException e) {
            return reconfirmBoolean();
        }
    }

    public void printTree(DecisionTree<AnimalProperty , String> tree){
        treePrinter.print(tree);
    }

    public String getAnimal(){
        System.out.println(res.getString("askSearchAnimal"));
        prompt();
        return processAnimal(scanner.nextLine());
    }

    public String processAnimal(String input){
        return articlePrefixRemover.process(input.trim().toLowerCase());
    }

    public void showTreeStats(TreeStatistics stats, String rootValue) {
        System.out.printf(res.getString("treeStats"),
                rootValue,
                stats.getNumLeafs()+stats.getNumInternalNodes(),
                stats.getNumLeafs(),
                stats.getNumInternalNodes(),
                stats.getHeight(),
                stats.getMinLeafDepth(),
                stats.getAverageLeafDepth());
    }
}