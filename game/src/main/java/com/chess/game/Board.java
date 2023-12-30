package com.chess.game;


import com.chess.game.pieces.*;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

import static com.chess.game.Game.GRID_SIZE;

public class Board {
    private boolean check = false;
    private final PieceColor WHITE = PieceColor.WHITE;
    private final PieceColor BLACK = PieceColor.BLACK;
    private static Board board;
    private Piece king;
    private Position kingPosition;
    private Piece[][] pieces = new Piece[8][8];
    private List<Position> kingCorridors = new ArrayList<>();

    public static Board theBoard(){
       if(board == null){
           board = new Board();
       }
       return board;
    }
    public Piece[][] defaultBoard() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                //pawns.
                if (row == 1) {
                    pieces[row][column] = new Pawn(BLACK);
                } else if (row == GRID_SIZE - 2){
                    pieces[row][column] = new Pawn(WHITE);

                //other pieces.
                }else if (row == 0){
                    placePiece(row, column, BLACK);
                }else if (row == GRID_SIZE - 1){
                    placePiece(row, column, WHITE);
                }else {
                    pieces[row][column] = new Empty();
                }
            }
        }
       return pieces;
    }

    private void placePiece(int row, int column, PieceColor color) {
        if (column == 0 || column == GRID_SIZE - 1) pieces[row][column] = new Rook(color);
        else if (column == 1 || column == GRID_SIZE - 2) pieces[row][column] = new Knight(color);
        else if (column == 2 || column == GRID_SIZE - 3) pieces[row][column] = new Bishop(color);
        else if (column == 3) pieces[row][column] = new Queen(color);
        else pieces[row][column] = new King(color);
    }

    public void placePiece(Piece piece, Position position){
       int row = position.getY(), column = position.getX();
        pieces[row][column] = piece;
    }
    public boolean isOccupied(int row, int col){
       return pieces[row][col] != null && !pieces[row][col].isEmpty();
    }

    public Piece[][] getPieces(){
        return pieces;
    }

    public void displayBoard(){
        for( int i = 0; i < GRID_SIZE; i++ ) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if(pieces[i][j].isEmpty()){
                    System.out.print("0 ");
                }else {
                    System.out.print(pieces[i][j].getPiece() + " ");
                }
            }
            System.out.println();
        }
    }


    public void clear() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                pieces[i][j] = new Empty();
            }
        }
        check = false;
    }

    public boolean move(Piece piece, Position from, Position dest) {
        if(piece.validMoves(this.getPieces(), from).contains(dest)){
            kingCorridors.clear();
            check = piece.validMoves(this.getPieces(), dest).stream()
                    .anyMatch(position -> {
                king = this.getPieces()[position.getY()][position.getX()];
                kingPosition = new Position(position.getX(), position.getY());
                kingCorridors.add(kingPosition);
                return king.getPiece().equals("king") && !king.getColor().equals(piece.getColor());
            });
            System.out.println("open the gates for the king: " + kingCorridors.toString());
            this.placePiece(piece, dest);
            this.pieces[from.getY()][from.getX()] = new Empty();
            return true;
        }else{
            return false;
        }
    }

    public boolean isCheck() {

        return check;
    }

    public List<Position> getValidMoves(Piece piece, int x, int y) {
        List<Position> validMoves = piece.validMoves(getPieces(), new Position(x, y));
        if ( check ) {
            System.out.println("King: Where is my knight?");
            System.out.println("Pawn: He is at.. umm "+validMoves.toString() );
            System.out.println("King: What! He is everywhere. Call him to protect me");
            return validMoves.stream().
                    filter(kingCorridors::contains).toList();
        }
        return validMoves;
    }
}
