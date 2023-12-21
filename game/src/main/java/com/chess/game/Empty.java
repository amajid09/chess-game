package com.chess.game;

public class Empty extends Piece {
    public Empty(int x, int y) {
        super("", x, y);
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        return false;
    }
}
