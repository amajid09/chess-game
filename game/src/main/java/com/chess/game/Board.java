package com.chess.game;


import static com.chess.game.BoardView.GRID_SIZE;

public class Board {
    public static Piece[][] createBoard() {
       Piece[][] piece = new Piece[8][8];
        for( int i = 0; i < GRID_SIZE; i++ ) {
            for ( int j = 0; j <  GRID_SIZE; j++ ) {
                if ( i == 1 || i == GRID_SIZE - 2 ) piece[i][j] = new Pawn( j, i );
                else if( i == 0 || i == GRID_SIZE - 1 )
                    if( j == 0 || j == GRID_SIZE - 1 ) piece[i][j] = new Rook( j, i );
                    else if( j == 1 || j == GRID_SIZE - 2 ) piece[i][j] = new Knight( j, i );
                    else if( j == 2 || j == GRID_SIZE - 3 ) piece[i][j] = new Bishop( j, i );
                    else if( j == 3 ) piece[i][j] = new Queen( j, i );
                    else piece[i][j] = new King( j, i );
                else piece[i][j] = new Empty( j, i );
            }
        }
       return piece;
    }
}
