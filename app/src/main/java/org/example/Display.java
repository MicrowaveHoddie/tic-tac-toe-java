package org.example;

import java.util.Scanner;

public class Display {
    private final Scanner scanner = new Scanner(System.in);

    public int askMove(Player player) {
        while (true) {
            System.out.print(player.getName() + " (" + player.getMark() + "), enter a number (1–9): ");
            String input = scanner.nextLine().trim();

            if (input.matches("[1-9]")) { // shouldn't crash from the funny numbers
                return Integer.parseInt(input);
            }
            System.out.println("Invalid input. Try again.");
        }
    }

    public boolean askPlayAgain() {
        System.out.print("\nPlay again? (y/n): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        return choice.equals("y");
    }

    public void scannerClose() {
        scanner.close();
    }
}
