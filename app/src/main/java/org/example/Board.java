package org.example;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<List<String>> cells;

    public Board() { //
        cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                row.add(String.valueOf(i * 3 + j));
            }
            cells.add(row);
        }
    }

    public boolean markCell(int cellNumber, String mark) {
        int row = (cellNumber - 1) / 3;
        int col = (cellNumber - 1) % 3;

        if (cells.get(row).get(col).equals("X") || cells.get(row).get(col).equals("O")) {
            return false;
        }
        cells.get(row).set(col, mark);
        return true;
    }

    public boolean checkWinner(String mark) {
        for (int i = 0; i < 3; i++) {
            if (cells.get(i).get(0).equals(mark) &&
                    cells.get(i).get(1).equals(mark) &&
                    cells.get(i).get(2).equals(mark)) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (cells.get(0).get(j).equals(mark) &&
                    cells.get(1).get(j).equals(mark) &&
                    cells.get(2).get(j).equals(mark)) {
                return true;
            }
        }

        if (cells.get(0).get(0).equals(mark) &&
                cells.get(1).get(1).equals(mark) &&
                cells.get(2).get(2).equals(mark)) {
            return true;
        }

        if (cells.get(0).get(2).equals(mark) &&
                cells.get(1).get(1).equals(mark) &&
                cells.get(2).get(0).equals(mark)) {
            return true;
        }

        return false;
    }

    public boolean isFull() {
        for (List<String> row : cells) {
            for (String cell : row) {
                if (!cell.equals("X") && !cell.equals("O")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void print() { //neato
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < 3; i++) {
            System.out.printf(" %s | %s | %s%n", cells.get(i).get(0), cells.get(i).get(1), cells.get(i).get(2));
            if (i < 2) System.out.println("-----------");
        }
    }
}
