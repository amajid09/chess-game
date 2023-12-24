package com.chess.game;

import com.chess.game.pieces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {
    private Board board;
    @BeforeEach
    public void setup(){
       board = Board.theBoard();
    }

    @AfterEach
    public void teardown(){
        board.clear();
    }
    @Test
    @DisplayName("Movements for black Pawn at the 2nd row")
    public void testBlackPawn(){
        //pawn on position a6 on the board.
        Piece[][] board = this.board.defaultBoard();
        Piece pawn = board[1][0];
        Position current = new Position(0, 1);
        List<Position> moves = pawn.validMoves(board, current);
        List<Position> expectedMoves = List.of(new Position(0, 2), new Position(0, 3));
        assertEquals(expectedMoves, moves);
    }
    @Test
    @DisplayName("Movements for white Pawn at the 7th row")
    public void testWhitePawn(){
        //pawn on position a6 on the board.
        Piece[][] board = this.board.defaultBoard();
        Piece pawn = board[6][0];
        Position currentLocation = new Position(0, 6);
        List<Position> moves = pawn.validMoves(board, currentLocation);
        List<Position> expectedMoves = List.of(new Position(0, 5), new Position(0, 4));
        assertEquals(expectedMoves, moves);
    }
    @Test
    @DisplayName("move black piece one square diagonally from the pawn")
    public void testPawnDiagonal(){
        int row = 2;
        int col = 4;
        Position currentLocation = new Position(col, row);
        Position bottom = new Position(col, row+1);
        Position bottomRight = new Position(col+1, row+1);
        Position bottomLeft = new Position(col - 1, row +1);
        Piece pawn = new Pawn(PieceColor.BLACK);
        Piece king = new King(PieceColor.WHITE);
        Piece queen = new Queen(PieceColor.WHITE);
        this.board.placePiece(pawn, currentLocation);
        this.board.placePiece(king, bottomRight);
        this.board.placePiece(queen, bottomLeft);
        List<Position> actual = pawn.validMoves(board.getPieces(), currentLocation);
        List<Position> expected = List.of(bottomLeft,bottom, bottomRight);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    @DisplayName("move white piece one square diagonally from the pawn")
    public void testWhitePawnDiagonal(){
        int row = 2;
        int col = 4;
        Position currentLocation = new Position(col, row);
        int topLeftY = row - 1, topLeftX = col - 1;
        Piece pawn = new Pawn(PieceColor.WHITE);
        Piece king = new King(PieceColor.BLACK);
        Piece queen = new Queen(PieceColor.BLACK);
        Position topLeft = new Position(col - 1, row  - 1);
        Position top = new Position(col, row-1);
        Position topRight = new Position(col+1, row-1);
        this.board.placePiece(pawn, currentLocation);
        this.board.placePiece(king, topLeft);
        this.board.placePiece(queen, topRight);
        List<Position> actual = pawn.validMoves(board.getPieces(), currentLocation);
        List<Position> expected = List.of(topLeft , top, topRight);
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    @DisplayName("Piece in front of the pawn")
    void blockedByPiece() {
        int x = 2;
        int y = 1;
        Position currentLocstion = new Position(x, y);
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        Piece whitePawn = new Pawn(PieceColor.WHITE);
        Position infront = new Position(y+1, x);
        board.placePiece(blackPawn, currentLocstion);
        board.placePiece(whitePawn, infront);
        List<Position> actual = blackPawn.validMoves(board.getPieces(), currentLocstion);

        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("piece in front and two pieces diagonal from it")
    void pieceInfront() {
        int x = 2;
        int y = 1;
        Position currentLocation = new Position(x, y);
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        Piece whitePawn1 = new Pawn(PieceColor.WHITE);
        Piece whitePawn2 = new Pawn(PieceColor.WHITE);
        Piece whitePawn3 = new Pawn(PieceColor.WHITE);

        Position bottomRight = new Position(x+1, y+1);
        Position bottomLeft = new Position(x-1, y+1);

        board.placePiece(blackPawn, currentLocation);
        board.placePiece(whitePawn1, new Position(x, y+1));
        board.placePiece(whitePawn2, bottomRight);
        board.placePiece(whitePawn3, bottomLeft);


        List<Position> actual = blackPawn.validMoves(board.getPieces(), currentLocation);
        List<Position> expected = List.of(bottomLeft, bottomRight);
        assertTrue(actual.containsAll(expected));
    }


    @Test
    @DisplayName("allied piece one square diagonal from the pawn")
    void alliedPiece() {
        int x = 3;
        int y = 6;
        Position currentLocation = new Position(x, y);
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        Piece blackBishop = new Bishop(PieceColor.BLACK);
        Piece whiteRook = new Rook(PieceColor.WHITE);
        board.placePiece(blackPawn, currentLocation);
        board.placePiece(blackBishop,new Position(2, 7));
        board.placePiece(whiteRook,new Position(4, 7));

        List<Position> expected = List.of(new Position(3, 7), new Position(4, 7));
        List<Position> actual = blackPawn.validMoves(board.getPieces(), currentLocation);
        assertTrue(actual.containsAll(expected));
    }
}