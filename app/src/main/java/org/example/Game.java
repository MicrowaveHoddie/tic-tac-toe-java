package org.example;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Display ui;
    private final PrintToFile externalPrint;
    private int ties = 0;
    private String starter = "Player 1";

    public Game() {
        board = new Board();
        ui = new Display();
        player1 = new Player("Player 1", "X"); // I think being able to change
        player2 = new Player("Player 2", "O"); // mark is extra credit no?
        externalPrint = new PrintToFile();
    }

    public void start() {
        boolean playAgain;
        do {
            play();
            System.out.print("\n==========================\n");
            System.out.print("Wins for Player 1: " + player1.getWins() + "\n");
            System.out.print("Wins for Player 2: " + player2.getWins() + "\n");
            System.out.print("Ties: " + ties + "\n");
            System.out.print("==========================\n");
            playAgain = ui.askPlayAgain();
        } while (playAgain);

        ui.scannerClose();
        externalPrint.print(player1,player2,ties);
        System.out.println("Thanks for playing!");
    }

    private void play() {
        Player current = player1;
        if (starter == "Player 1") {
            current = player1;
        } else {
            current = player2;
        }
        while (true) {
            board.print();
            int move = ui.askMove(current);

            if (!board.markCell(move, current.getMark())) {
                System.out.println("That cell is already taken!");
                continue;
            }

            if (board.checkWinner(current.getMark())) {
                board.print();
                System.out.println(current.getName() + " wins!");
                current.addWin();
                if (current.getName().equals("Player 1")) {
                    starter = "Player 2";
                    System.out.println("Player 2 starts first next round!");
                } else if (current.getName().equals("Player 2")) {
                    starter = "Player 1";
                    System.out.println("Player 1 starts first next round!");
                }
                board.newboard();
                break;
            }

            if (board.isFull()) {
                board.print();
                System.out.println("It's a tie!");
                ties++;
                break;
            }

            current = (current == player1) ? player2 : player1;
        }
    }

}
