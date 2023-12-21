package com.chess.game;

public abstract class Piece {
    private String name;



    private int x, y;
    public Piece(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    public String getPiece() {
        return name;
    }
    public int getX() {
        return x;
    }

    

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean move(Piece[][] board, int x, int y);
}
