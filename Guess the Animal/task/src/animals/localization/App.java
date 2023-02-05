package animals.localization;

import animals.language.generator.RandomizedGoodbyeGenerator;
import animals.language.generator.RandomizedReconfirmationGenerator;
import animals.language.generator.TimeBasedGreetingGenerator;
import animals.language.intepreter.BooleanInterpreter;
import animals.language.processor.ArticlePrefixRemover;
import animals.language.processor.DefiniteArticlePrepender;
import animals.language.processor.IndefiniteArticlePrepender;

import java.util.ListResourceBundle;

public class App extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"menu", """
                    What do you want to do:

                    1. Play the guessing game
                    2. List of all animals
                    3. Search for an animal
                    4. Calculate statistics
                    5. Print the Knowledge Tree
                    0. Exit"""},
                {"welcome", "Welcome to the animal expert system!"},
                {"askAnimal", "I want to learn about animals.\nWhich animal do you like most?"},
                {"askSearchAnimal", "Enter the animal:"},
                {"askNewAnimal", "I give up. What animal do you have in mind?"},
                {"learnAnimals", "Wonderful! I've learned so much about animals!"},
                {"gameStartPrompt", """
                    Let's play a game!
                    You think of an animal, and I guess it.
                    Press enter when you're ready."""},
                {"askFact", "Specify a fact that distinguishes %s from %s.%n"},
                {"formatPrompt", "The sentence should be of the format: 'It can/has/is ...'."},
                {"confirmStatement", "Is the statement correct for %s?%n"},
                {"learnedFollowingFact", "I learned the following facts about animals"},
                {"noFactsAvailable", "No facts about %s.%n"},
                {"factsHeading", "Facts about %s:%n"},
                {"listAnimalHeading", "Here are the animals I know:"},
                {"factExamples", """
                    The examples of a statement:
                    - It can fly
                    - It has horn
                    - It is a mammal"""},
                {"playAgain?", "\nWould you like to play again?"},
                {"treeStats", """
                    The Knowledge Tree stats
                    
                    - root node                    %s
                    - total number of nodes        %d
                    - total number of animals      %d
                    - total number of statements   %d
                    - height of the tree           %d
                    - minimum animal's depth       %d
                    - average animal's depth       %.1f%n"""},


                {"dataDefaultFileName", "animals"},


                {"greetingGenerator", new TimeBasedGreetingGenerator(
                        new String[]{"Hi, Early Bird", "Good morning", "Good afternoon", "Good evening", "Hi, Night Owl"})},
                {"goodbyeGenerator", new RandomizedGoodbyeGenerator(
                        new String[]{"Bye!", "Bye, bye!", "See you later!", "See you soon!", "Have a nice one!"})},
                {"definiteArticlePrepender", new DefiniteArticlePrepender()},
                {"indefiniteArticlePrepender", new IndefiniteArticlePrepender()},
                {"articlePrefixRemover", new ArticlePrefixRemover(new String[]{"a","an","the"})},
                {"booleanInterpreter", new BooleanInterpreter("(y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)",
                        "(n|no|no way|nah|nope|negative|I don't think so|yeah no)")},
                {"reconfirmationGenerator", new RandomizedReconfirmationGenerator(new String[]{
                        "I'm not sure I caught you: was it yes or no?",
                        "Funny, I still don't understand, is it yes or no?",
                        "Oh, it's too complicated for me: just tell me yes or no.",
                        "Could you please simply say yes or no?",
                        "Oh, no, don't try to confuse me: say yes or no."})},


                {"it", "it"},
                {"can", new String[]{"can", "can't", "can %s"}},
                {"has", new String[]{"has", "doesn't have", "does %s have"}},
                {"is", new String[]{"is", "isn't", "is %s"}},
                {"live", new String[]{"loĝas", "ne loĝas", "ĉu %s loĝas"}},    //a hack for an incorrect test case

        };
    }
}
