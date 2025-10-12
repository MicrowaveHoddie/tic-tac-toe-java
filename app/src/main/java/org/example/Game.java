package org.example;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private final Display ui;

    public Game() {
        board = new Board();
        ui = new Display();
        player1 = new Player("Player 1", "X"); // I think being able to change
        player2 = new Player("Player 2", "O"); // mark is extra credit no?
    }

    public void start() {
        boolean playAgain;
        do {
            play();
            playAgain = ui.askPlayAgain();
        } while (playAgain);

        ui.scannerClose();
        System.out.println("Thanks for playing!");
    }

    private void play() {
        Player current = player1;
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
                break;
            }

            if (board.isFull()) {
                board.print();
                System.out.println("It's a tie!");
                break;
            }

            current = (current == player1) ? player2 : player1;
        }
    }
}
