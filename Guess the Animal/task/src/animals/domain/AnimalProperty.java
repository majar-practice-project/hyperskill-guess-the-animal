package animals.domain;

public class AnimalProperty {
    private FactVerb verb;
    private String property;

    AnimalProperty() {}

    public AnimalProperty(FactVerb verb, String property) {
        this.verb = verb;
        this.property = property;
    }

    public FactVerb getVerb() {
        return verb;
    }

    public String getProperty() {
        return property;
    }

    public String toInterrogativeString(){
        return String.format("%s %s?", verb.getInterrogativeText(), property);
    }

    public String toAffirmativeString(String animal){
        return String.format("%s %s %s.", animal, verb.getText(), property);
    }

    public String toNegativeString(String animal){
        return String.format("%s %s %s.", animal, verb.getNegateText(), property);
    }
}
