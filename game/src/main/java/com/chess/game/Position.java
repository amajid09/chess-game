package com.chess.game;


import javafx.geometry.Pos;

public class Position {


    private final int x;
    private final int y;


    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){

        if(!(o instanceof Position)) {
            return false;
        }

        Position position = (Position) o;
        if(position.x != this.x || position.y != this.y){
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public String toString(){
        return String.format("(%s, %s)", this.x, this.y);
    }

}
