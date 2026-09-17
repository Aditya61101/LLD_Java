package com.lld.Practice.TicTacToe;

public class Board {
    private final char [][] board;

    Board(int dimension) {
        board = new char[dimension][dimension];
    }

    char getCellValue(int x, int y) {
        return board[x][y];
    }

    void setCellValue(int x, int y, char symbol) {
        board[x][y] = symbol;
    }
}
