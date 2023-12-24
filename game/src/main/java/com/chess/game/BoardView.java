package com.chess.game;

import com.chess.game.pieces.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

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
    @Override
    public void start( Stage stage ) throws IOException {
        AnchorPane root = new AnchorPane();
        System.out.println(getClass().getResource("icons/icons8-pawn-50.png"));
        drawBoard(root);
        Scene scene = new Scene( root );
        stage.setTitle( "Hello!" );
        stage.setScene( scene );
        stage.show();
    }

    private void drawBoard(AnchorPane root) {
        for ( int i =0; i < GRID_SIZE; i++ ) {
            for ( int j = 0; j < GRID_SIZE; j++ ){
                Rectangle color = new Rectangle( RECT_SIZE, RECT_SIZE );
                Rectangle rectangle = placePiece(j, i);
                color.setLayoutX( RECT_SIZE * j );
                color.setLayoutY( RECT_SIZE * i );
                if((i+j) %2 == 0) {
                    color.setFill(Color.valueOf("DAB785"));
                }else{
                    color.setFill(Color.valueOf("D5896F"));
                }
                dropOn(color, rectangle, board);
                root.getChildren().addAll(color, rectangle);
            }
        }
    }

    private void placePieces(AnchorPane root) {
        for(int i = 0; i < BoardView.GRID_SIZE; i++ ) {
            for (int j = 0; j <  BoardView.GRID_SIZE; j++ ) {

                Rectangle rectangle = placePiece(j, i);
                root.getChildren().add( rectangle );
            }
        }
    }

    private Rectangle placePiece(int j, int i) {
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        Rectangle rectangle = new Rectangle( RECT_SIZE, RECT_SIZE );
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
            }else{
            rectangle.setFill(Color.TRANSPARENT);
        }
        return rectangle;
    }

    private void dropOn(Rectangle rectangle,Rectangle piece,  Piece[][] board) {
        piece.setOnDragDetected(e -> {
            System.out.println("Circle 1 drag detected");
            Dragboard db = piece.startDragAndDrop(TransferMode.COPY_OR_MOVE) ;
            ClipboardContent content = new ClipboardContent();
            content.putImage(((ImagePattern) piece.getFill()).getImage());
            db.setContent(content);
        });
        rectangle.setOnDragOver(event-> {
            int x = (int) rectangle.getLayoutX() / 100;
            int y = (int) rectangle.getLayoutY() / 100;
            System.out.println("x: "+x );
            System.out.println("Y: "+y );
            if(event.getDragboard().hasImage()
            ){
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });
        rectangle.setOnDragDropped(event-> {
            System.out.println("dropped");
            Dragboard db = event.getDragboard();
            if(db.hasImage()){
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
    private void makeDraggable( Rectangle node , Piece piece) {

        node.setOnMousePressed(e -> {
            currX = e.getSceneX() ;
            currY = e.getSceneY() ;

            prevX = ( int ) (( currX  / RECT_SIZE) % GRID_SIZE );
             prevY = ( int ) (( currY  / RECT_SIZE) % GRID_SIZE );
        });
        node.setOnMouseDragged(event -> {
            node.startDragAndDrop(TransferMode.MOVE);
            startX = event.getSceneX() ;
            startY = event.getSceneY() ;
            int x = ( int ) (( startX  / RECT_SIZE) % GRID_SIZE ) * RECT_SIZE;
            int y = ( int ) (( startY  / RECT_SIZE) % GRID_SIZE ) * RECT_SIZE;
            System.out.println("x: " + x/RECT_SIZE );
            System.out.println("y: " + y/RECT_SIZE);
            int row = y/RECT_SIZE, col =x/RECT_SIZE;
//            if(piece.move(board, col, row)) {
//                System.out.println("prevX: " + prevX);
//                System.out.println("prevY: " + prevY);
//                node.setLayoutY( y );
//                node.setLayoutX( x );
//
//            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}