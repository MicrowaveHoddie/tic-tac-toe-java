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

    public boolean askVsComputer() {
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.print("Choose: ");
        return safeMenuInput(1,2) == 2;
    }

    public boolean askComputerFirst() {
        System.out.println("1. Computer goes first");
        System.out.println("2. Computer goes second");
        System.out.print("Choose: ");
        return safeMenuInput(1,2) == 1;
    }

    private int safeMenuInput(int min, int max) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        if (val < min || val > max) {
            System.out.print("Enter valid option: ");
            return safeMenuInput(min,max);
        }
        return val;
    }

}
