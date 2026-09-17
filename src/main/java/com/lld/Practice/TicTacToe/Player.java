package com.lld.Practice.TicTacToe;

import java.util.UUID;

public class Player {
    String id;
    String name;

    Player (String name) {
        this.id = "P-" + UUID.randomUUID();
        this.name = name;
    }
}
