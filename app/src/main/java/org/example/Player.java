package org.example;

public class Player { // keep mark here because I think it gives EC?
    private final String name;
    private final String mark;

    public Player(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public String getMark() {
        return mark;
    }
}
