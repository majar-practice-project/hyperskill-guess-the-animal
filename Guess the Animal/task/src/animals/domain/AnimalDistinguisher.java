package animals.domain;

public class AnimalDistinguisher {
    private final String availableAnimal;
    private final String unavailableAnimal;
    private final AnimalProperty property;

    public AnimalDistinguisher(String availableAnimal, String unavailableAnimal, AnimalProperty property) {
        this.availableAnimal = availableAnimal;
        this.unavailableAnimal = unavailableAnimal;
        this.property = property;
    }

    public String getAvailableAnimal() {
        return availableAnimal;
    }

    public String getUnavailableAnimal() {
        return unavailableAnimal;
    }

    public AnimalProperty getProperty() {
        return property;
    }
}
