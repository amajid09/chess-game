package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private List<Position> openPosition = new ArrayList<>();

    public Rook(PieceColor color ) {
        super("rook", color );
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        return openPosition;
    }
}
