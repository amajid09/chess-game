package com.chess.game.pieces;

import com.chess.game.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private List<Position> openPosition = new ArrayList<>();

    public Queen(PieceColor color) {
        super("queen", color);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        return openPosition;
    }
}