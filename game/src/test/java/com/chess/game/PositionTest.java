package com.chess.game;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {


    @Test
    public void testForTwoEqualPositions() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);
        assertEquals(position1, position2);
    }

    @Test
    public void testDifferentPosition(){
        Position position1 = new Position(2, 1);
        Position position2 = new Position(1, 1);
        assertNotEquals(position2, position1);
    }
    @Test
    public void testIfPositionIsInListOfPositions() {
        List<Position> positions = List.of(new Position( 1, 1 ),
                new Position(2, 2), new Position(3,2));
       assertTrue(positions.contains(new Position(1,1)));
    }



}