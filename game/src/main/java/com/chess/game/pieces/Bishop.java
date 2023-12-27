package com.chess.game.pieces;

import com.chess.game.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    Position topLeft = new Position(-1, -1);
    Position topRight = new Position(1, -1);
    Position bottomLeft = new Position(-1,1);
    Position bottomRight = new Position(1,1);
    private List<Position> openPosition ;

    public Bishop(PieceColor color) {
        super("bishop", color);
    }

    @Override
    public List<Position> validMoves(Piece[][] board, Position location) {
        openPosition = new ArrayList<>();
        int x = location.getX();
        int y = location.getY();
        checkTopLeft( x, y, board );
        checkTopRight( x, y , board );
        checkBottomRight( x, y , board );
        checkBottomLeft( x, y, board );
        return openPosition;
    }

    private void checkBottomRight( int x, int y, Piece[][] board ) {
        x += bottomRight.getX();
        y += bottomRight.getY();
        while ( x < 8 && y < 8 ) {
            if( board[y][x].isOtherPiece( this ) ){
                openPosition.add( new Position(x, y) );
                break;
            }else if( !board[y][x].isEmpty() ) {
                break;
            }else {
                openPosition.add( new Position(x, y) );
            }
            x += bottomRight.getX();
            y += bottomRight.getY();
        }
    }

    private void checkBottomLeft(int x, int y, Piece[][] board) {
        x += bottomLeft.getX();
        y += bottomLeft.getY();
        while ( x > -1 && y < 8  )  {
            if(board[y][x].isOtherPiece(this)){
                openPosition.add(new Position(x, y));
                break;
            }else if(!board[y][x].isEmpty()) {
                break;
            }else {
                openPosition.add(new Position(x, y));
            }
            x+= bottomLeft.getX();
            y += bottomLeft.getY();
        }
    }

    private void checkTopRight(int x, int y, Piece[][] board) {
        x+= topRight.getX();
        y += topRight.getY();
        while ( x < 8 && y > -1 ) {
            if(board[ y ][ x ].isOtherPiece( this ) ){
                openPosition.add( new Position( x, y ) );
                break;
            }else if(!board[y][x].isEmpty()) {
                break;
            }else {
                openPosition.add(new Position(x, y));
            }
           x+= topRight.getX();
           y += topRight.getY();
       }
    }

    private void checkTopLeft( int x, int y, Piece[][] board ) {
        x += topLeft.getX();
        y += topLeft.getY();
        while ( x > -1 && y > -1  ){
            if( board[y][x].isOtherPiece( this ) ){
                openPosition.add( new Position( x, y ) );
                break;
            }else if(!board[y][x].isEmpty()) {
                break;
            }else {
                openPosition.add(new Position(x, y));
            }
            x += topLeft.getX();
            y += topLeft.getY();
       }
    }
}
