package animals.language.generator.util;

import java.util.List;
import java.util.Random;

public class RandomArray<T> {
    private final Random rand = new Random(System.nanoTime());
    private final T[] array;
    public RandomArray(T[] array){
        this.array = array;
    }

    public T get(){
        return array[rand.nextInt(array.length)];
    }
}