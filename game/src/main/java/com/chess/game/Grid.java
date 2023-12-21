package com.chess.game;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Stack;


public class Grid {
    private double width;
    private double height;
    private final AnchorPane pane;
    final static int ROW = 8;
    final static int COL = 8;
    final static int gridSize  = 100;
    final static int TOTALTILES  = 64;
    public Grid(AnchorPane pane) {
        this.pane = pane;
    }

    public void drawGrid() {
        for(int i=0; i < TOTALTILES; i++) {
            int x = i % ROW;
            int y = i / COL;
            StackPane stack = new StackPane();
            Rectangle rectangle = new Rectangle(x * gridSize, y * gridSize, gridSize, gridSize);
            if((x +y) % 2 == 0) {
                rectangle.setFill(Color.GREEN);
            }else{
                rectangle.setFill(Color.GRAY);
            }
           pane.getChildren().addAll(rectangle);
        }
    }


}
