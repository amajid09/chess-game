package com.chess.game;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn( int x, int y) {
        super("pawn", x, y);
    }

    @Override
    public boolean move(Piece[][] board, int x, int y) {
        //get a list of valid move
        List<Position> openPositions = validPositions();
        return false;
    }

    private List<Position> validPositions() {
        List<Position> positions = new ArrayList<>();
        int x = getX();
        int y = getY();
        if(y == 1 ){
           positions.add(moveTwoStepsUp(x, y));
        }else if(y == 5) {
            positions.add(moveTwoStepsDown(x, y));
        }
        return positions;
    }

    private Position moveTwoStepsDown(int x, int y) {
        return new Position(x, y+2);
    }

    private Position moveTwoStepsUp(int x, int y) {
        return new Position(x, y - 2);
    }
}
