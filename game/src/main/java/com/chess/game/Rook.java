package com.chess.game;

public class Rook extends Piece {
    public Rook( int x, int y) {
        super( "rook",  x, y );
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        return false;
    }
}
