package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private List<Position> openPosition = new ArrayList<>();

    public Bishop(PieceColor color) {
        super("bishop", color);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        return openPosition;
    }
}
