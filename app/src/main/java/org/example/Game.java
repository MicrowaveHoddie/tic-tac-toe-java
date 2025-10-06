package org.example;

import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<ArrayList<String>> board = createBoard(); // arrayLists are just renamed vectors?
        boolean playAgain = true;

        while (playAgain) {
            updateBoard(board);

            System.out.print("\nWould you like to play again? (y/n): ");
            String choice = input.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                board = cleanBoard(board);
            } else {
                playAgain = false;
                System.out.println("Thanks for playing!");
            }
        }
        input.close();
    }


    public static ArrayList<ArrayList<String>> createBoard() {
        ArrayList<ArrayList<String>> board = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            board.add(new ArrayList<>());
            for (int j = 1; j <= 3; j++) {
                board.get(i).add(String.valueOf(i * 3 + j));
            }
        }
        return board;
    }

    public static ArrayList<ArrayList<String>> cleanBoard(ArrayList<ArrayList<String>> board) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j < 3; j++) {
                board.get(i).set(j, String.valueOf(i * 3 + (j + 1)));
            }
        }
        return board;
    }

    public static ArrayList<ArrayList<String>> updateBoard(ArrayList<ArrayList<String>> board) {
        Scanner input = new Scanner(System.in);
        boolean p1Turn = true;
        int moves = 0;

        while (true) {
            printBoard(board);

            String player = p1Turn ? "Player 1 (X)" : "Player 2 (O)";
            System.out.print(player + ", enter a number between 1 and 9: ");

            String choice = input.nextLine().trim();

            if (!choice.matches("[1-9]")) {
                System.out.println("Invalid input. Please enter a number from 1 to 9.");
                continue;
            }

            int cell = Integer.parseInt(choice);
            int row = (cell - 1) / 3;
            int col = (cell - 1) % 3;

            if (board.get(row).get(col).equals("X") || board.get(row).get(col).equals("O")) {
                System.out.println("That cell is already taken! Try again.");
                continue;
            }

            String mark = p1Turn ? "X" : "O";
            board.get(row).set(col, mark);
            moves++;

            if (checkWinner(board, mark)) {
                printBoard(board);
                System.out.println(player + " wins!");
                break;
            }

            if (moves == 9) {
                printBoard(board);
                System.out.println("Game over — it's a tie!");
                break;
            }

            p1Turn = !p1Turn;
        }

        return board;
    }

    public static boolean checkWinner(ArrayList<ArrayList<String>> board, String mark) {

        for (int i = 0; i < 3; i++) { // row code check here
            if (board.get(i).get(0).equals(mark) &&
                    board.get(i).get(1).equals(mark) &&
                    board.get(i).get(2).equals(mark)) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) { // colum code check here
            if (board.get(0).get(j).equals(mark) &&
                    board.get(1).get(j).equals(mark) &&
                    board.get(2).get(j).equals(mark)) {
                return true;
            }
        }

        if (board.get(0).get(0).equals(mark) && //dia are funny
                board.get(1).get(1).equals(mark) &&
                board.get(2).get(2).equals(mark)) {
            return true;
        }

        if (board.get(0).get(2).equals(mark) &&
                board.get(1).get(1).equals(mark) &&
                board.get(2).get(0).equals(mark)) {
            return true;
        }

        return false;
    }

    public static void printBoard(ArrayList<ArrayList<String>> board) {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < 3; i++) {
            System.out.printf(" %s | %s | %s%n", board.get(i).get(0), board.get(i).get(1), board.get(i).get(2));
            if (i < 2) {
                System.out.println("-----------");
            }
        }
        System.out.println();
    }

}
