package animals.view.util;

import animals.language.processor.HyphenTrimmer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ArgumentFinder {
    private final Map<String, String> args = new HashMap<>();
    private final HyphenTrimmer hyphenTrimmer = new HyphenTrimmer();

    public ArgumentFinder(String[] arguments){
        for(int i=0; i<arguments.length; i++){
            if(arguments[i].startsWith("-")){
                args.put(hyphenTrimmer.process(arguments[i]), arguments[i+1]);
            }
        }
    }

    public Optional<String> getProperty(String key){
        return Optional.ofNullable(args.get(key));
    }
}
