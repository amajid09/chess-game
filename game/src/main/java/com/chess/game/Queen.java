package com.chess.game;

public class Queen extends Piece {
    public Queen(int x, int y) {
        super("queen", x, y);
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        return false;
    }
}
