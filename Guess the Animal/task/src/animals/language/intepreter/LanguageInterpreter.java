package animals.language.intepreter;

public interface LanguageInterpreter<T> {
    T interpret(String text);
}
