package com.chess.game.pieces;

import com.chess.game.Position;
import javafx.scene.control.skin.PaginationSkin;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {


    List<Position> possible = List.of(new Position(-1, 2), new Position(-2, 1), new Position(-2, -1),
            new Position(-1, -2), new Position(1, -2), new Position(1, 2), new Position(2,1), new Position(2, -1));
    public Knight(PieceColor color) {
        super( "knight",  color);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        List<Position> openPosition = new ArrayList<>();
        int x = location.getX();
        int y = location.getY();
        for (Position position : possible) {
           int possibleX = position.getX();
           int possibleY = position.getY();
           int calcX = x + possibleX, calcY = y + possibleY;
           if(calcY >= 0 && calcX >= 0 && calcX < 8 && calcY < 8 && (board[calcY][calcX].isOtherPiece(this) || board[calcY][calcX].isEmpty())){
               openPosition.add(new Position(calcX, calcY));
           }
        }
        return openPosition;
    }
}
