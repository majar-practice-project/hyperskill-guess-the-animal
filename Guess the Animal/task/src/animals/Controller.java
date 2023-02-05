package animals;

import animals.domain.AnimalDistinguisher;
import animals.domain.AnimalProperty;
import animals.domain.decision.DecisionTree;
import animals.domain.decision.DecisionTreeAnalyser;
import animals.persistence.DecisionTreeRepository;
import animals.view.CommandView;
import animals.view.util.ArgumentFinder;

import java.util.Map;

public class Controller {
    private static final Controller INSTANCE = new Controller();
    private CommandView view = new CommandView();
    private DecisionTreeRepository repository;
    private DecisionTree<AnimalProperty, String> decisionTree;

    public static Controller getInstance() {
        return INSTANCE;
    }

    public void start(String[] args) {
        view.showGreeting();
        decisionTree = init(args);

        processCommand();
        view.showGoodbye();
    }

    public void processCommand() {
        view.showWelcome();
        while (true) {
            switch (view.getChoice()) {
                case 0 -> {
                    return;
                }
                case 1 -> startGameCycle();
                case 2 -> printAnimals();
                case 3 -> searchForAnimal();
                case 4 -> displayStats();
                case 5 -> view.printTree(decisionTree);
                default -> throw new RuntimeException("Not yet implemented");
            }
        }
    }

    public void printAnimals() {
        view.showAnimals(decisionTree.getLeafs());
    }

    public void searchForAnimal() {
        String animal = view.getAnimal();
        Map<AnimalProperty, Boolean> facts = decisionTree.getConditions(animal);
        view.showAnimalProperties(facts, animal);
    }

    public void displayStats() {
        decisionTree.resetToRoot();
        AnimalProperty rootCondition = decisionTree.getInternalValue();
        String root = rootCondition == null ? decisionTree.getLeafValue() : rootCondition.toAffirmativeString("It");

        DecisionTreeAnalyser analyser = new DecisionTreeAnalyser();
        analyser.anaylse(decisionTree);
        view.showTreeStats(analyser, root);
    }

    public DecisionTree<AnimalProperty, String> init(String[] args) {
        ArgumentFinder argumentFinder = new ArgumentFinder(args);
        String dataFormat = argumentFinder.getProperty("type").orElse("json");
        repository = new DecisionTreeRepository(dataFormat, this::askForData);
        return repository.load();
    }

    public String askForData() {
        return view.getFavoriteAnimal();
    }

    public void startGameCycle() {
        boolean onGoing = true;
        while (onGoing) {
            boolean correctGuess = playGame(decisionTree);
            if (!correctGuess) {
                addNewAnimal(decisionTree);
            }
            onGoing = view.getPlayAgain();
        }
        repository.save(decisionTree);
    }

    public boolean playGame(DecisionTree<AnimalProperty, String> decisionTree) {
        decisionTree.resetToRoot();
        view.showGameStart();

        while (!decisionTree.reachTheEnd()) {
            boolean conditionMatching = view.getQuestionResult(decisionTree.getInternalValue().toInterrogativeString());
            decisionTree.move(conditionMatching);
        }

        return view.confirmAnimal(decisionTree.getLeafValue());
    }

    public void addNewAnimal(DecisionTree<AnimalProperty, String> decisionTree) {
        String prevAnimal = decisionTree.getLeafValue();
        String newAnimal = view.getNewAnimal();
        AnimalProperty property = confirmProperty(prevAnimal, newAnimal);
        boolean newAnimalHasProperty = view.confirmCorrectAnimal(newAnimal);

        decisionTree.addCondition(property, newAnimal, newAnimalHasProperty);

        AnimalDistinguisher fact = newAnimalHasProperty
                ? new AnimalDistinguisher(newAnimal, prevAnimal, property)
                : new AnimalDistinguisher(prevAnimal, newAnimal, property);

        view.showFacts(fact);
    }

    public AnimalProperty confirmProperty(String animal1, String animal2) {
        try {
            return view.getProperty(animal1, animal2);
        } catch (IllegalArgumentException e) {
            while (true) {
                try {
                    view.showFactExamples();
                    return view.getProperty(animal1, animal2);
                } catch (IllegalArgumentException ignored) {
                }
            }
        }
    }
}
