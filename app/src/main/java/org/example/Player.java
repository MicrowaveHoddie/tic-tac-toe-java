package org.example;

public class Player { // keep mark here because I think it gives EC?
    private final String name;
    private final String mark;
    private int wins = 0;

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

    public int getWins() {
        return wins;
    }

    public void addWin() {
        this.wins += 1;
    }
}
