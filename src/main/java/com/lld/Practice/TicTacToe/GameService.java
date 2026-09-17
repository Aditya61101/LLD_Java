package com.lld.Practice.TicTacToe;

import java.util.HashMap;
import java.util.Map;

public class GameService {
    int emptyCells;
    int dimensions;

    Board board;

    Map<Integer, Map<Character,Integer>> rows;
    Map<Integer, Map<Character,Integer>> cols;
    Map<Integer, Map<Character,Integer>> diagonals;

    GameService(int dimension) {
        this.dimensions = dimension;
        this.emptyCells = dimension*dimension;
        this.board = new Board(dimension);

        rows = new HashMap<>();
        cols = new HashMap<>();
        diagonals = new HashMap<>();
    }

    GameStatus move(GamePlayer gp, int x, int y) {
        if(isInvalid(x,y)) return GameStatus.INVALID;

        char symbol = gp.symbol;

        board.setCellValue(x, y, symbol);
        emptyCells-=1;

        fillMap(rows,x,symbol);
        fillMap(cols,y,symbol);

        if(onMainDiagonal(x,y)) fillMap(diagonals,0,symbol);
        else if(onOppositeDiagonal(x,y)) fillMap(diagonals,1,symbol);

        // check for win
        if(isWinningMove(x, y, symbol)) return GameStatus.WIN;

        // check for draw
        if(hasGameDrawn()) return GameStatus.DRAW;

        return GameStatus.CONTINUE;
    }

    private boolean isInvalid(int x, int y) {
        if(x<0 || y<0 || x>=dimensions || y>=dimensions) return true;
        return board.getCellValue(x, y) != '\0';
    }

    private boolean isWinningMove(int x, int y, char symbol) {
        // checking in rows
        int symbolRowCount = rows.get(x).get(symbol);
        if(symbolRowCount==dimensions) return true;

        // checking in cols
        int symbolColCount = cols.get(y).get(symbol);
        if(symbolColCount==dimensions) return true;

        // checking on main diagonal
        if(onMainDiagonal(x,y) && diagonals.get(0).get(symbol)==dimensions) return true;

        // checking on opposite diagonal
        return onOppositeDiagonal(x, y) && diagonals.get(1).get(symbol) == dimensions;
    }
    private boolean hasGameDrawn() {
        return emptyCells==0;
    }

    private boolean onMainDiagonal(int x, int y) {
        return (x==y);
    }
    private boolean onOppositeDiagonal(int x, int y) {
        return (x+y) == (dimensions-1);
    }

    private void fillMap(Map<Integer, Map<Character,Integer>> mp, int i, char symbol) {
        //        if (!rows.containsKey(x)) {
//            rows.put(x, new HashMap<>());
//        }
//
//        Map<Character, Integer> inner = rows.get(x);
//
//        if (!inner.containsKey(symbol)) {
//            inner.put(symbol, 0);
//        }
//
//        inner.put(symbol, inner.get(symbol) + 1);
        mp.computeIfAbsent(i, k -> new HashMap<>())
                .merge(symbol, 1, Integer::sum);
    }
}
