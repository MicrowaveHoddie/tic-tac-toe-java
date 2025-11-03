package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintToFile {
    public void print(Player player1, Player player2, int ties) {
        String filename = "app/src/main/resources/lastSessionLog.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("==========================");
            writer.println("Wins for Player 1: " + player1.getWins());
            writer.println("Wins for Player 2: " + player2.getWins());
            writer.println("Ties: " + ties);
            writer.println("==========================");
            System.out.println("\nGame log saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
