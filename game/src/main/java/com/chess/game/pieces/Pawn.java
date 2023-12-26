package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private int x, y;
    List<Position> openPositions;
    public Pawn(PieceColor color) {
        super("pawn",color);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        //get a list of valid    ove
        openPositions = new ArrayList<>();
        x = location.getX();
        y = location.getY();

        if (getColor().equals(PieceColor.BLACK)){
            validDownMoves( board );
            checkDownDiagonalMoves( board );
        }else{
            checkUpDiagonalMoves( board );
            validUpMoves( board );
        }

        return openPositions;
    }

    private void checkUpDiagonalMoves(Piece[][] board) {
        int north = y - 1;
        int right = x+1;
        int left = x-1;
        if(y >= 1 && x < 7 && board[north][right] != null)
            if( (!board[north][right].isEmpty() && board[north][right].isOtherPiece(this))){
                openPositions.add(new Position(right, north));
            }
        if(y >= 1  && x >= 1 && board[north][left] != null)
            if( !board[north][left].isEmpty() && board[north][left].isOtherPiece(this)){
                openPositions.add(new Position(left, north));
            }
    }

    private void checkDownDiagonalMoves(Piece[][] board) {
        int south = y + 1;
        int right = x + 1;
        int left = x - 1;
        if(y < 7 && x < 7 && board[south][right] != null)
            if( (!board[south][right].isEmpty() && board[south][right].isOtherPiece(this))) {
                openPositions.add(new Position(right, south));
            }
        if( y < 7 && x   > 1 && board[south][left] != null)
                if((!board[south][left].isEmpty() && board[south][left].isOtherPiece(this))){
                    openPositions.add(new Position(left, south));
                }
    }
    private void validUpMoves(Piece[][] board) {
        if(y > 1 && (board[y - 1][x] == null || board[y - 1][x].isEmpty())){
            openPositions.add(new Position(x, y - 1));
        }
        if(y ==6 && ( board[y - 2][x] == null || board[y - 2][x].isEmpty() )){
            openPositions.add(new Position(x, y - 2 ));
        }
    }


    private void validDownMoves(Piece[][] board) {
        System.out.println("Y................." + y);

        if(y <= 6 && (board[y + 1][x] == null || board[y + 1][x].isEmpty())) {
            openPositions.add(new Position(x, y + 1));
            if(y == 1){
                openPositions.add(new Position(x, y + 2));
            }
        }

    }
}
