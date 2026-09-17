package com.lld.Practice.TicTacToe;

import java.util.Scanner;

public class Client {
    private static final int RETRY_LIMIT = 3;

    public static void main(String[] args) {
        // players
        Player aditya = new Player("Aditya");
        Player john = new Player("John");

        Game newGame = new Game(3);
        newGame.addPlayer(aditya, 'X');
        newGame.addPlayer(john,'O');

        Scanner sc = new Scanner(System.in);

        while (true) {
            boolean end = false;
            for(GamePlayer player:newGame.getGamePlayers()) {
                GameStatus gameStatus = Client.takeInput(sc, player, newGame, 1);
                if(gameStatus.equals(GameStatus.DRAW) || gameStatus.equals(GameStatus.WIN)) {
                    end = true;
                    break;
                }
            }
            if(end) break;
        }
    }
    private static GameStatus takeInput(Scanner sc, GamePlayer gp, Game newGame, int retryCount) {

        System.out.println("------Player "+ gp.player.name + " turn------");
        System.out.print("Enter x y: ");
        int x = sc.nextInt();
        int y = sc.nextInt();

        GameStatus gameStatus = newGame.move(gp, x, y);

        if (gameStatus.equals(GameStatus.DRAW)) {
            System.out.println("Game has been drawn");
        } else if(gameStatus.equals(GameStatus.WIN)) {
            System.out.println("Player " + gp.player.name + " has won the game!");
        } else if(gameStatus.equals(GameStatus.INVALID)) {
            System.out.println("Invalid move");
            if(retryCount==Client.RETRY_LIMIT) {
                System.out.println("Retry limit reached, next player turn!");
                return gameStatus;
            }
            System.out.println("Please try again!");
            takeInput(sc,gp, newGame, retryCount+1);
        }
        return gameStatus;
    }
}
