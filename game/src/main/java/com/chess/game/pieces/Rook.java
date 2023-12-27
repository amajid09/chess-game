package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private List<Position> openPosition;

    public Rook(PieceColor color ) {
        super("rook", color );
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        openPosition = new ArrayList<>();
        int x = location.getX(), y = location.getY();
        checkUp( x,y, board );
        checkDown( x,y, board );
        checkLeft( x,y, board );
        checkRight( x,y, board );
        return openPosition;
    }

    private void checkLeft(int x, int y, Piece[][] board) {
        x+= -1;
        while (x > -1){
            if(board[y][x].isOtherPiece(this)){
                openPosition.add(new Position(x, y));
                break;
            } else if(!board[y][x].isEmpty()){
                break;
            }else{
                openPosition.add(new Position(x, y));
            }
            x+= -1;
        }
    }

    private void checkRight(int x, int y, Piece[][] board) {
        x += 1;
        while ( x < 8 ){
            if(board[y][x].isOtherPiece(this)){
                openPosition.add(new Position(x, y));
                break;
            } else if(!board[y][x].isEmpty()){
                break;
            }else{
                openPosition.add(new Position(x, y));
            }
            x += 1;
        }
    }

    private void checkDown(int x, int y, Piece[][] board) {
        y += 1;
        while ( y < 8 ){
            if(board[y][x].isOtherPiece(this)){
                openPosition.add(new Position(x, y));
                break;
            } else if(!board[y][x].isEmpty()){
                break;
            }else{
                openPosition.add(new Position(x, y));
            }
            y += 1;
        }
    }

    private void checkUp(int x, int y, Piece[][] board) {
        y += -1;
        while ( y > -1 ){
            if(board[y][x].isOtherPiece(this)){
                openPosition.add(new Position(x, y));
                break;
            } else if(!board[y][x].isEmpty()){
                break;
            }else{
                openPosition.add(new Position(x, y));
            }
            y += -1;
        }
    }
}
