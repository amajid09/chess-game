package com.chess.game;

public class Bishop extends Piece {
    public Bishop(int x, int y) {
        super("bishop", x, y);
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        return false;
    }
}
