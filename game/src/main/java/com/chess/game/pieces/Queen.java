package com.chess.game.pieces;

import com.chess.game.Position;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private List<Position> openPosition;
    private Piece rook;
    private Piece bishop;
    public Queen(PieceColor color) {
        super("queen", color);
         rook = new Rook(getColor());
         bishop = new Bishop(getColor());
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        openPosition = new ArrayList<>();
        System.out.println("diagonal++++" + bishop.validMoves(board, location));
        openPosition.addAll(bishop.validMoves(board, location));
        openPosition.addAll(rook.validMoves(board, location));
        return openPosition;
    }
}