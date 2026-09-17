package com.lld.Practice.TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<GamePlayer> gamePlayers;
    GameService gameService;

    Game(int dimension) {
        this.gameService = new GameService(dimension);
        this.gamePlayers = new ArrayList<>();
    }

    void addPlayer(Player p, char symbol) {
        GamePlayer gp = new GamePlayer(p,symbol);
        gamePlayers.add(gp);
    }
    GameStatus move(GamePlayer gp, int x, int y) {
        return gameService.move(gp, x, y);
    }
    List<GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
}
