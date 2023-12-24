package com.chess.game;

import com.chess.game.pieces.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
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
    Piece[][] board = Board.theBoard().defaultBoard();
    private double currX;
    private double currY;
    private int prevX, prevY;
    private ImagePattern whitePawn = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50-white.png") ) ) );
    private ImagePattern whiteRook = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-rook-50-white.png") ) ) );

    private ImagePattern whiteKnight = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-knight-50-white.png") ) ) );
    private ImagePattern whiteBishop = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-bishop-50-white.png") ) ) );
    private ImagePattern whiteQueen = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-queen-50-white.png") ) ) );
    private ImagePattern whiteKing = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-king-50-white.png") ) ) );
    private List<List<Rectangle>> rectangles = new ArrayList<>();
    private int counter = 0;

    @Override
    public void start( Stage stage ) throws IOException {
        Pane root = new Pane();
        System.out.println(getClass().getResource("icons/icons8-pawn-50.png"));
        drawBoard(root);
        placePieces(root);
        System.out.println("pieces     " + counter);
        makePieceDraggable(root);
        Scene scene = new Scene( root );
        stage.setTitle( "Hello!" );
        stage.setScene( scene );
        stage.show();
    }

    private void makePieceDraggable(Pane root) {

    }

    private void drawBoard(Pane root) {
        for ( int i =0; i < GRID_SIZE; i++ ) {
            for ( int j = 0; j < GRID_SIZE; j++ ){
                Rectangle color = new Rectangle( RECT_SIZE, RECT_SIZE );

                color.setLayoutX( RECT_SIZE * j );
                color.setLayoutY( RECT_SIZE * i );
                if( ( i+j ) % 2 == 0 ) {
                    color.setFill( Color.valueOf("DAB785") );
                }else{
                    color.setFill( Color.valueOf("D5896F") );
                }
                root.getChildren().add( color );
            }
        }
    }

    private void placePieces( Pane root ) {
        for( int i = 0; i < 2; i++ ) {
            for ( int j = 0; j <  BoardView.GRID_SIZE; j++ ) {
                placePiece( j, i, root );
                placePiece( j,BoardView.GRID_SIZE - (i + 1), root);
            }
        }
    }

    private void placePiece(int j, int i, Pane root) {
        Rectangle rectangle = new Rectangle( RECT_SIZE, RECT_SIZE );
        rectangle.setId("piece");
        rectangle.setLayoutX( RECT_SIZE * j);
        String piece = board[i][j].getPiece();
        rectangle.setLayoutY( RECT_SIZE * i);
        if(i == 1) {
            rectangle.setFill( pawn );
        }else if(i == 6){

            rectangle.setFill( whitePawn );
        }else if(i == 0)
            switch ( piece ) {
                case "rook" -> rectangle.setFill( rook );
                case "knight" -> rectangle.setFill( knight );
                case "bishop" -> rectangle.setFill( bishop );
                case "queen" -> rectangle.setFill( queen );
                case "king" -> rectangle.setFill( king );
            }
        else if(i == 7)
            switch ( piece ) {
                case "pawn" -> rectangle.setFill( whitePawn );
                case "rook" -> rectangle.setFill( whiteRook );
                case "knight" -> rectangle.setFill( whiteKnight );
                case "bishop" -> rectangle.setFill( whiteBishop );
                case "queen" -> rectangle.setFill( whiteQueen );
                case "king" -> rectangle.setFill( whiteKing );
            }


        root.getChildren().forEach(child -> {
                dropOn( rectangle, (Rectangle) child, board, i, j );
        });
        root.getChildren().add(rectangle);
    }

    private void dropOn(Rectangle piece, Rectangle rectangle, Piece[][] board, int i, int j) {
        if(!board[i][j].isEmpty()) {
        rectangle.setOnDragDetected(e -> {
            Dragboard db = rectangle.startDragAndDrop(TransferMode.COPY_OR_MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(((ImagePattern) rectangle.getFill()).getImage());
            db.setContent(content);
        });
    }
        rectangle.setOnMouseDragged(e-> e.setDragDetect(true));
        rectangle.setOnDragOver(event-> {
            System.out.println("here");
            int x = (int) rectangle.getLayoutX() / 100;
            int y = (int) rectangle.getLayoutY() / 100;
            if(event.getDragboard().hasImage()){
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                System.out.println("x: "+x );
                System.out.println("Y: "+y );
            }
            event.consume();
        });
        rectangle.setOnDragDropped(event-> {
            System.out.println("dropped");
            Dragboard db = event.getDragboard();
            if(db.hasImage()){
                System.out.println("rec x "+ rectangle.getLayoutX());
                System.out.println("rec y "+ rectangle.getLayoutY());
                System.out.println("Dropped: " + db.getImage());
                piece.setLayoutY(rectangle.getLayoutY());
                piece.setLayoutX(rectangle.getLayoutX());
                event.setDropCompleted(true);
            }else{
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}