package com.chess.game.pieces;

import com.chess.game.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private List<Position> openPositions =  new ArrayList<>();

    public King(PieceColor color ) {
        super("king", color );
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        return openPositions;
    }
}
