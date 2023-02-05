package animals.localization;

import animals.language.generator.RandomizedGoodbyeGenerator;
import animals.language.generator.RandomizedReconfirmationGenerator;
import animals.language.generator.TimeBasedGreetingGenerator;
import animals.language.intepreter.BooleanInterpreter;
import animals.language.processor.ArticlePrefixRemover;
import animals.language.processor.StringProcessor;

import java.util.ListResourceBundle;

public class App_eo extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"menu", """
                    Kion vi volas fari:

                    1. Ludi la divenludon
                    2. Listo de ĉiuj bestoj
                    3. Serĉi beston
                    4. Kalkuli statistikon
                    5. Presu la Scion-Arbon
                    0. Eliri"""},
                {"welcome", "Bonvenon al la sperta sistemo de la besto!"},
                {"askAnimal", "Mi volas lerni pri bestoj.\nKiun beston vi plej ŝatas?"},
                {"askSearchAnimal", "Enigu la nomon de besto:"},
                {"askNewAnimal", "Mi rezignas. Kiun beston vi havas en la kapo?"},
                {"learnAnimals", "Mi lernis tiom multe pri bestoj!"},
                {"gameStartPrompt", """
                    Ni ludu ludon!
                    Vi pensu pri besto, kaj mi divenos ĝin.
                    Premu enen kiam vi pretas."""},
                {"askFact", "Indiku fakton, kiu distingas %s de %s.%n"},
                {"formatPrompt", "La frazo devas esti de la formato: 'Ĝi ...'."},
                {"confirmStatement", "Ĉu la aserto ĝustas por la %s?%n"},
                {"learnedFollowingFact", "I learned the following facts about animals"},
                {"noFactsAvailable", "Neniuj faktoj pri %s.%n"},
                {"factsHeading", "Faktoj pri %s:%n"},
                {"listAnimalHeading", "Jen la bestoj, kiujn mi konas:"},
                {"factExamples", """
                    La ekzemploj de aserto:
                     - Ĝi povas flugi
                     - Ĝi havas kornojn
                     - Ĝi estas mamulo"""},
                {"playAgain?", "\nĈu vi volas provi denove?"},
                {"treeStats", """
                    La statistiko de la Scio-Arbo
                    
                    - radika nodo                   %s
                    - totala nombro de nodoj        %d
                    - totala nombro de bestoj       %d
                    - totala nombro de deklaroj     %d
                    - alteco de la arbo             %d
                    - minimuma profundo             %d
                    - averaĝa profundo              %.1f%n"""},


                {"dataDefaultFileName", "animals_eo"},


                {"greetingGenerator", new TimeBasedGreetingGenerator(
                        new String[]{"Saluton Frua Birdo!", "Bonan matenon!", "Bonan tagon!",  "Bonan vesperon!", "Saluton Nokta Strigo!"})},
                {"goodbyeGenerator", new RandomizedGoodbyeGenerator(
                        new String[]{"Ĝis!", "Ĝis revido!", "Estis agrable vidi vin!"})},
                {"definiteArticlePrepender", (StringProcessor) text -> text.replaceAll("(?i)^(la |)", "la ")},
                {"indefiniteArticlePrepender", new ArticlePrefixRemover(new String[]{"la"})},
                {"articlePrefixRemover", new ArticlePrefixRemover(new String[]{"la"})},
                {"booleanInterpreter", new BooleanInterpreter("(j|jes|certe)", "(n|ne)")},
                {"reconfirmationGenerator", new RandomizedReconfirmationGenerator(new String[]{
                        "Bonvolu enigi jes aŭ ne.",
                        "Amuze, mi ankoraŭ ne komprenas, ĉu jes aŭ ne?",
                        "Pardonu, devas esti jes aŭ ne.",
                        "Ni provu denove: ĉu jes aŭ ne?",
                        "Pardonu, ĉu mi rajtas demandi vin denove: ĉu tio estis jes aŭ ne?"
                })},


                {"it", "ĝi"},
                {"can", new String[]{"povas", "ne povas", "ĉu %s povas"}},
                {"has", new String[]{"havas", "ne havas", "ĉu %s havas"}},
                {"is", new String[]{"estas", "ne estas", "ĉu %s estas"}},
                {"live", new String[]{"loĝas", "ne loĝas", "ĉu %s loĝas"}},    //a hack for an incorrect test case


        };
    }
}