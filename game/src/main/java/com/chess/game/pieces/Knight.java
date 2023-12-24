package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {


    private List<Position> openPosition = new ArrayList<>();

    public Knight(PieceColor color) {
        super( "knight",  color);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        return openPosition;
    }
}
