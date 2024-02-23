package domain;

public class Client {
    private final String name;
    private final String surName;
    private final int birthYear;

    public Client(String name, String surName, int birthYear) {
        this.name = name;
        this.surName = surName;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {

        return birthYear;
    }

    public String getSurName() {
        return surName;
    }

}
