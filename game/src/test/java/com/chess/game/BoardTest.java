package com.chess.game;

import com.chess.game.pieces.Piece;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    public static final int LAST_ROW = 8;
    public static final int FIRST_ROW = 0;
    private Board board;

    @BeforeEach
    public void setup(){
        board = Board.theBoard();
    }

    @AfterEach
    public void teardown(){
        board = null;
    }
    @Test
    @DisplayName("Second row and second last row should all have pawns.")
    public void testBoardDefaultPawn(){
        int secondRow = FIRST_ROW + 1;
        int secondLastRow = LAST_ROW - 2;
       assertTrue(Arrays.stream(board.defaultBoard()[secondRow]).
               allMatch(i-> i.getPiece().equals("pawn")));
        assertTrue(Arrays.stream(board.defaultBoard()[secondLastRow]).
                allMatch(i-> i.getPiece().equals("pawn")));
    }
    @Test
    @DisplayName("test all the different pieces on the first row")
    public void testBoardDefaultFirstRow(){
        Piece[] actual = board.defaultBoard()[FIRST_ROW];
        String[] expected = {"rook", "knight", "bishop", "queen" ,"king", "bishop", "knight", "rook"};
        assertTrue(IntStream.range(FIRST_ROW, LAST_ROW)
                .sequential().allMatch(i-> actual[i].getPiece().equals(expected[i])));
    }
    @Test
    @DisplayName("test all the different pieces on the last row")
    public void testBoardDefaultSecondRow(){
        Piece[] actual = board.defaultBoard()[LAST_ROW - 1];
        String[] expected = {"rook", "knight", "bishop", "queen" ,"king", "bishop", "knight", "rook"};
        assertTrue(IntStream.range(FIRST_ROW, LAST_ROW).sequential()
                .allMatch(i-> actual[i].getPiece().equals(expected[i])));
    }


}