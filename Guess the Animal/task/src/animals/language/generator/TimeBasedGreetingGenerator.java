package animals.language.generator;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeBasedGreetingGenerator implements GreetingGenerator {
    //todo could just remove hashmap and iterate through two arrays insteadZ
    private final Map<LocalTime, String> greetingThreshold = new LinkedHashMap<>();
    private final LocalTime[] thresholds = {LocalTime.of(5,0), LocalTime.of(12,0),
            LocalTime.of(18, 0), LocalTime.of(23, 0), LocalTime.of(23,59,59)};

    public TimeBasedGreetingGenerator(String[] greetings){
        if(greetings.length!= thresholds.length) throw new IllegalArgumentException("Incorrect number of greetings are supplied.");

        for(int i=0; i<thresholds.length; i++){
            greetingThreshold.put(thresholds[i], greetings[i]);
        }
    }

    @Override
    public String generate() {
        LocalTime currentTime = LocalTime.now();
        return greetingThreshold.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isAfter(currentTime))
                .findFirst().orElseThrow()
                .getValue();
    }
}