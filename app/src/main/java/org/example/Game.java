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
        player1 = new Player("Player 1", "X");
        player2 = new Player("Player 2", "O");
        externalPrint = new PrintToFile();
    }

    public void start() {

        boolean vsComputer = ui.askVsComputer();
        boolean computerIsPlayer1 = false;

        if (vsComputer) {
            computerIsPlayer1 = ui.askComputerFirst();
        }

        boolean playAgain;

        do {
            play(vsComputer, computerIsPlayer1);
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

    private void play(boolean vsComputer, boolean computerIsPlayer1) {

        board.newboard();

        Player current = starter.equals("Player 1") ? player1 : player2;

        Bot cpu = null;
        if (vsComputer) {
            cpu = new Bot(board, computerIsPlayer1 ? player1 : player2);
        }

        while (true) {
            board.print();

            int move;

            if (vsComputer && cpu.isCpu(current)) {
                int botMove = cpu.getMove();
                move = botMove + 1;
                System.out.println("Computer chooses: " + move);
            } else {
                move = ui.askMove(current);
            }

            if (!board.markCell(move, current.getMark())) {
                System.out.println("That cell is already taken!");
                continue;
            }

            if (board.checkWinner(current.getMark())) {
                board.print();
                System.out.println(current.getName() + " wins!");
                current.addWin();

                starter = current == player1 ? "Player 2" : "Player 1";

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
