package com.chess.game;

import com.chess.game.pieces.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckTest {
    private Board board;

    Piece king = new King(PieceColor.WHITE);
    Position kingPosition = new Position(4, 7);
    @BeforeEach
    void setUp() {
        board = Board.theBoard();
        board.clear();
        board.placePiece(king, kingPosition);
        assertFalse(board.isCheck());
    }

    @AfterEach
    void tearDown() {
        board.clear();
    }

    @Test
    void RookCheck() {
        Piece rook = new Rook(PieceColor.BLACK);
        Position rookPosition = new Position(3, 5);
        board.placePiece(rook, rookPosition);
        assertTrue(board.move(rook, rookPosition, new Position(rookPosition.getX()+1, rookPosition.getY()) ));
        assertTrue(board.isCheck());
    }
    @Test
    @DisplayName("The bishop should protect the king from the rook")
    void pawnProtectFromBishop() {
        Piece knight= new Knight(PieceColor.WHITE);
        Position knightPosition = new Position(1,7);
        Piece bishop = new Bishop(PieceColor.BLACK);
        Position position = new Position(1, 2);
        board.placePiece(bishop, position);
        board.placePiece(knight, knightPosition);
        assertTrue(board.move(bishop, position, new Position(0, 3)));
        assertTrue(board.isCheck());
        List<Position> expected = List.of( new Position(3,6) , new Position(2, 5));
        List<Position> actual = board.getValidMoves(knight, knightPosition.getX(), knightPosition.getY());
        assertTrue(expected.containsAll(actual));
        System.out.println("Knight: Im here your highness.");
        assertTrue(actual.containsAll(expected));
        System.out.println("King: Stick to your duty, Knight");
    }
    @Test
    @DisplayName("The bishop should protect the king from the rook")
    void bishopProtectFromRook() {
        Piece rook = new Rook( PieceColor.BLACK );
        Piece bishop = new Bishop( PieceColor.WHITE );
        Position rookPosition = new Position(3, 5);
        Position bishopPosition = new Position(5, 7);
        board.placePiece( rook, rookPosition );
        board.placePiece( bishop, bishopPosition );
        assertTrue( board.move( rook, rookPosition, new Position(rookPosition.getX() + 1, rookPosition.getY() )));
        assertEquals(new Position(bishopPosition.getX() - 1, bishopPosition.getY() - 1), board.getValidMoves(bishop, bishopPosition.getX(), bishopPosition.getY()).get(0));

    }

    @Test
    void pawnCheck() {
        Piece pawn = new Pawn(PieceColor.BLACK);
        Position pawnPosition = new Position(3, 5);
        board.placePiece(pawn, pawnPosition);
        assertTrue(board.move(pawn  , pawnPosition, new Position(3, 6)));
        assertTrue(board.isCheck());
    }

    @Test
    void bishopCheck() {
        Piece bishop = new Bishop(PieceColor.BLACK);
        Position position = new Position(1, 2);
        board.placePiece(bishop, position);
        assertTrue(board.move(bishop, position, new Position(0, 3)));
        assertTrue(board.isCheck());
    }
}