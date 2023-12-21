package com.chess.game;

public class Knight extends Piece {
    public Knight(int x, int y) {
        super("knight",  x, y);
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        return false;
    }
}
