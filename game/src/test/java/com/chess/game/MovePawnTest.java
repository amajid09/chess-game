package com.chess.game;

import com.chess.game.pieces.Pawn;
import com.chess.game.pieces.Piece;
import com.chess.game.pieces.PieceColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovePawnTest {
    private Board board = Board.theBoard();
    @AfterEach
    void tearDown() {
        board.clear();
    }

    @Test
    @DisplayName("Move white pawn one step forward")
    void moveWhitePawnForward() {
       Piece whitePawn = new Pawn(PieceColor.WHITE);
        Position from = new Position(3, 6);
        board.placePiece(whitePawn, from);
        Position dest = new Position(3,5);
        assertTrue(board.move(whitePawn,from, dest ));
        assertEquals(whitePawn, board.getPieces()[5][3]);
    }

    @Test
    @DisplayName("Move white pawn two steps forward")
    void moveWhitePawnTwoForward() {
        Piece whitePawn = new Pawn(PieceColor.WHITE);
        Position from = new Position(3, 6);
        board.placePiece(whitePawn, from);
        Position dest = new Position(3,4);
        assertTrue(board.move(whitePawn,from, dest ));
        assertEquals(whitePawn, board.getPieces()[4][3]);
    }

    @Test
    @DisplayName("Pawn must capture a piece one square up to the right")
    void captureCaptureTopRight() {
        Piece whitePawn = new Pawn(PieceColor.WHITE);
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        Position from = new Position(3, 6);
        Position dest = new Position(4,5 );
        board.placePiece(whitePawn, from);
        board.placePiece(blackPawn, dest);
        assertTrue(board.move(whitePawn,from, dest ));
        assertEquals(whitePawn, board.getPieces()[5][4]);
    }
    @Test
    @DisplayName("Pawn must capture a piece one square up to the left")
    void captureCaptureTopLeft() {
        Piece whitePawn = new Pawn(PieceColor.WHITE);
        Piece blackPawn = new Pawn(PieceColor.BLACK);
        Position from = new Position(3, 6);
        Position dest = new Position(2,5 );
        board.placePiece(whitePawn, from);
        board.placePiece(blackPawn, dest);
        assertTrue(board.move(whitePawn,from, dest ));
        assertEquals(whitePawn, board.getPieces()[5][2]);
    }

}