package com.chess.game.pieces;

import com.chess.game.Position;
import java.util.ArrayList;
import java.util.List;

public class Empty extends Piece {


    private List<Position> openPositions = new ArrayList<>();

    public Empty() {
        super("", PieceColor.EMPTY);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        return openPositions;
    }
}
