package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    int top, bottom, right, left;
    boolean isTop ;
    boolean isBottom;
    boolean isRight;
    boolean isLeft;
    private List<Position> openPositions;

    public King(PieceColor color ) {
        super("king", color );
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        openPositions = new ArrayList<>();
        int x = location.getX() , y = location.getY();
        bottom = y + 1;
        top = y - 1;
        left = x - 1;
        right = x + 1;
        isTop  = top > -1;
        isBottom = bottom < 8;
        isRight = right < 8;
        isLeft = left > -1;
        if(isTop) {
            diagonal( board, x, top);
        }
        if(isBottom) {
            diagonal( board, x , bottom);
        }
        if(isRight && ( board[y][right].isOtherPiece(this) || board[y][right].isEmpty() )) {
                openPositions.add(new Position(right, y));
        }
        if(isLeft && ( board[y][left].isOtherPiece(this) || board[y][left].isEmpty() )) {
                openPositions.add(new Position(left, y));
        }
        return openPositions;
    }

    private void diagonal(Piece[][] board, int x, int y) {
        if ( isRight && (board[y][right].isOtherPiece(this) || board[y][right].isEmpty())) {
            openPositions.add(new Position(right, y));
        }
        if ( isLeft && (board[y][left].isOtherPiece(this) || board[y][left].isEmpty())) {
            openPositions.add(new Position(left, y));
        }
        if ((board[y][x].isOtherPiece(this) || board[y][x].isEmpty())) {
            openPositions.add(new Position(x, y));
        }
    }
}
