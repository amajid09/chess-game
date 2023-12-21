package com.chess.game;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardView extends Application {
    public static final int RECT_SIZE = 50;
    public static final int GRID_SIZE = 8;
    private double startX, startY;
    private ImagePattern pawn = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50.png") ) ) );
    private ImagePattern bishop = new ImagePattern( new Image(String.valueOf( getClass().getResource("icons/icons8-bishop-50.png" ) ) ) );
    private ImagePattern rook = new ImagePattern( new Image(String.valueOf( getClass().getResource("icons/icons8-rook-50.png" ))));
    private ImagePattern knight = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-knight-50.png" ))));
    private ImagePattern king = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-king-50.png" ))));
    private ImagePattern queen = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-queen-50.png" ))));
    Piece[][] board = Board.createBoard();

    @Override
    public void start( Stage stage ) throws IOException {
        AnchorPane root = new AnchorPane();
        drawBoard(root);
        placePieces(root);
        Scene scene = new Scene( root );
        stage.setTitle( "Hello!" );
        stage.setScene( scene );
        stage.show();
    }

    private void drawBoard(AnchorPane root) {
        for ( int i =0; i < GRID_SIZE; i++ ) {
            for ( int j = 0; j < GRID_SIZE; j++ ){
                Rectangle color = new Rectangle( RECT_SIZE, RECT_SIZE );
                color.setLayoutX( RECT_SIZE * j );
                color.setLayoutY( RECT_SIZE * i );
                if((i+j) %2 == 0) {
                    color.setFill(Color.GREEN);
                }else{
                    color.setFill(Color.GRAY);
                }
                root.getChildren().add(color);
            }
        }
    }

    private void placePieces(AnchorPane root) {
        for(int i = 0; i < BoardView.GRID_SIZE; i++ ) {
            for (int j = 0; j <  BoardView.GRID_SIZE; j++ ) {

                Rectangle rectangle = new Rectangle( RECT_SIZE, RECT_SIZE );
                rectangle.setLayoutX( RECT_SIZE * j );
                rectangle.setLayoutY( RECT_SIZE * i );
                String piece = board[i][j].getPiece();
                switch ( piece ) {
                    case "pawn" -> rectangle.setFill( pawn );
                    case "rook" -> rectangle.setFill( rook );
                    case "knight" -> rectangle.setFill( knight );
                    case "bishop" -> rectangle.setFill( bishop );
                    case "queen" -> rectangle.setFill( queen );
                    case "king" -> rectangle.setFill( king );
                    default -> rectangle.setFill( Color.TRANSPARENT );
                }

                makeDraggable( rectangle , board[i][j]);
                root.getChildren().add( rectangle );
            }
        }
    }

    private void makeDraggable( Rectangle node , Piece piece) {
        node.setOnMouseDragged(event -> {
            startX = event.getSceneX() ;
            startY = event.getSceneY() ;
            int x = ( int ) (( startX  / RECT_SIZE) % GRID_SIZE ) * RECT_SIZE;
            int y = ( int ) (( startY  / RECT_SIZE) % GRID_SIZE ) * RECT_SIZE;
            System.out.println("x: " + x/RECT_SIZE );
            System.out.println("y: " + y/RECT_SIZE);
            int row = y/RECT_SIZE, col =x/RECT_SIZE;
            if(piece.move(board, x, y)) {
                board[x][y].setPosition(x, y);
            }
            node.setLayoutY( y );
            node.setLayoutX( x );
        });
    }

    public static void main(String[] args) {
        launch();
    }
}