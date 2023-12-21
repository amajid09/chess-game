package com.chess.game;

public class King extends Piece {
    public King(int x, int y) {
        super("king", x, y);
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        return false;
    }
}
