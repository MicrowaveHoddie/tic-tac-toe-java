package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {

    private final Board board;
    private final Player cpuPlayer;
    private final Random random = new Random();

    public Bot(Board board, Player cpuPlayer) {
        this.board = board;
        this.cpuPlayer = cpuPlayer;
    }

    public boolean isCpu(Player p) {
        return p == cpuPlayer;
    }

    public int getMove() {
        char[] cells = board.getCells();
        String cpuMark = cpuPlayer.getMark();
        String enemyMark = cpuMark.equals("X") ? "O" : "X";

        int filled = countFilled(cells);

        // RULE 1
        if (filled == 0) {
            int[] corners = {0, 2, 6, 8};
            return corners[random.nextInt(4)];
        }

        // RULE 2
        if (filled == 1 && cells[4] != 'X' && cells[4] != 'O') {
            return 4;
        }

        // RULE 3
        Integer winningMove = findWinningSpot(cells, cpuMark);
        if (winningMove != null) return winningMove;

        // RULE 4
        Integer blockMove = findWinningSpot(cells, enemyMark);
        if (blockMove != null) return blockMove;

        // RULE 5
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (cells[i] != 'X' && cells[i] != 'O') {
                available.add(i);
            }
        }

        return available.get(random.nextInt(available.size()));
    }

    private int countFilled(char[] cells) {
        int count = 0;
        for (char c : cells) {
            if (c == 'X' || c == 'O') count++;
        }
        return count;
    }

    private Integer findWinningSpot(char[] cells, String mark) {
        int[][] lines = {
                {0,1,2}, {3,4,5}, {6,7,8},   // rows
                {0,3,6}, {1,4,7}, {2,5,8},   // cols
                {0,4,8}, {2,4,6}             // diagonals
        };

        char m = mark.charAt(0);

        for (int[] line : lines) {
            int a=line[0], b=line[1], c=line[2];

            int countMark = 0;
            int empty = -1;

            if (cells[a] == m) countMark++; else if (cells[a] != 'X' && cells[a] != 'O') empty = a;
            if (cells[b] == m) countMark++; else if (cells[b] != 'X' && cells[b] != 'O') empty = b;
            if (cells[c] == m) countMark++; else if (cells[c] != 'X' && cells[c] != 'O') empty = c;

            if (countMark == 2 && empty != -1)
                return empty;
        }
        return null;
    }
}
