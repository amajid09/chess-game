package com.chess.game.pieces;
import com.chess.game.Position;

import java.util.List;
public abstract class Piece {
    private String name;
    private boolean isCheck;

    private PieceColor color;
    public Piece(String name, PieceColor color) {
        this.name = name;
        this.color = color;
    }
    public String getPiece() {
        return name;
    }

    public boolean isEmpty(){
        return this.name.equals("");
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract List<Position> validMoves(Piece[][] board, Position Location);

    protected boolean isOtherPiece(Piece piece) {
        if(this.isEmpty()){
            return false;
        }
        return !piece.getColor().equals(this.color);
    }
}
