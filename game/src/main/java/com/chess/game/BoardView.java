package com.chess.game;

import com.chess.game.pieces.Piece;
import com.chess.game.pieces.PieceColor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BoardView extends Application {
    public static final int RECT_SIZE = 60;
    public static final int GRID_SIZE = 8;
    private final int offsetX = 500;
    private final int offsetY = 100;
    private static final int offset = 140;
    private ImagePattern pawn;
    private ImagePattern bishop;
    private ImagePattern rook;
    private ImagePattern knight;
    private ImagePattern king;
    private ImagePattern queen;
    Piece[][] allPieces;
    private Board board;
    private ImagePattern whitePawn ;
    private ImagePattern whiteRook;
    private PieceColor turn;
    private ImagePattern whiteKnight;
    private ImagePattern whiteBishop;
    private ImagePattern whiteQueen;
    private ImagePattern whiteKing;
    private Group highlighted;
    private Group pieces;
    


    @Override
    public void start( Stage stage ) throws IOException {
        initialise();
        Group grid = new Group();
        AnchorPane root = new AnchorPane( grid, pieces, highlighted );
        drawBoard(grid);
        placePieces();
        Scene scene = new Scene( root , 140, 140);
        stage.setTitle( "Hello!" );
        stage.setScene( scene );
        stage.show();
    }


    private void drawBoard(Group root) {
        for ( int i = 0; i < GRID_SIZE; i++ ) {
            for ( int j = 0; j < GRID_SIZE; j++ ){
                Rectangle color = new Rectangle( RECT_SIZE, RECT_SIZE );

                color.setLayoutX( (RECT_SIZE * j) + offsetX );
                color.setLayoutY( (RECT_SIZE * i) + offsetY );
                if( ( i+j ) % 2 == 0 ) {
                    color.setFill( Color.valueOf("DAB785") );
                }else{
                    color.setFill( Color.valueOf("D5896F") );
                }
                root.getChildren().add( color );
            }
        }
    }

    private void placePieces() {
        for( int i = 0; i < 2; i++ ) {
            for ( int j = 0; j <  BoardView.GRID_SIZE; j++ ) {
                //place black pieces
                placePiece( j, i );
                //place white pieces
                placePiece( j,BoardView.GRID_SIZE - ( i + 1 ) );
            }
        }
    }

    private void placePiece(int j, int i) {
        Rectangle rectangle = new Rectangle( RECT_SIZE, RECT_SIZE );
        rectangle.setLayoutX( (RECT_SIZE * j) + offsetX);
        String piece = allPieces[i][j].getPiece();
        rectangle.setLayoutY( (RECT_SIZE * i) + offsetY);
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
        clickOnPiece(rectangle);
        pieces.getChildren().add(rectangle);

    }
//    click on piece which will show all available positions and by clicking on one of the available position will the piece move to that position
    private void clickOnPiece(Rectangle piece) {
       piece.setOnMouseClicked(e-> {
           int i = (int)  ( e.getSceneY() / RECT_SIZE % GRID_SIZE );
           int j = (int)  ( e.getSceneX() / RECT_SIZE % GRID_SIZE );
           if(board.getPieces()[i][j].getColor().equals(turn)) {
               List<Position> availablePosition = board.getValidMoves(board.getPieces()[i][j], j, i);
               highlightSquare(availablePosition, piece);
           }
       });
    }

    private void highlightSquare(List<Position> positions, Rectangle piece) {
        highlighted.getChildren().clear();
        positions.forEach(square -> {
            Rectangle rectangle = new Rectangle( RECT_SIZE, RECT_SIZE );
            int x = square.getX() * RECT_SIZE;
            int y = square.getY() * RECT_SIZE;
            rectangle.setLayoutX( x );
            rectangle.setLayoutY( y );
            rectangle.setFill(Color.GREEN);
            rectangle.setOpacity(0.15);
            clickOnHighlightedBlock(rectangle, x, y ,piece);
            highlighted.getChildren().add(rectangle);
        });
    }

    private void clickOnHighlightedBlock(Rectangle rectangle, int x, int y, Rectangle piece) {
       rectangle.setOnMouseClicked(e-> {
           int col = x / RECT_SIZE;
           int row = y / RECT_SIZE;

           int prevX = ( int ) ( piece.getLayoutX() / RECT_SIZE );
           int prevY = ( int ) ( piece.getLayoutY() / RECT_SIZE );

           Piece movingPiece = allPieces[ prevY ][ prevX ];
           Position from = new Position( prevX, prevY );
           Position dest = new Position( col, row );

           if(board.isOccupied( row, col )) {
               killPiece( x, y );
           }

           movePiece( piece, movingPiece, from, dest );
           highlighted.getChildren().clear();
           turn = turn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
       });
    }

    private void movePiece(Rectangle piece, Piece movingPiece, Position from, Position dest) {
        if(board.move(movingPiece, from, dest)){
            piece.setLayoutY( dest.getY() * RECT_SIZE );
            piece.setLayoutX( dest.getX() * RECT_SIZE );
        }
    }

    private void killPiece(int x, int y) {
        //remove from pieces group
        Rectangle rect = (Rectangle) pieces.getChildren().stream()
                .filter(child-> child.getLayoutX() == x
                        && child.getLayoutY() == y).
                findFirst().get();
        pieces.getChildren().remove(rect);
    }
    private void initialise(){
        pawn = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50.png") ) ) );
        bishop = new ImagePattern( new Image(String.valueOf( getClass().getResource("icons/icons8-bishop-50.png" ) ) ) );
        rook = new ImagePattern( new Image(String.valueOf( getClass().getResource("icons/icons8-rook-50.png" ))));
        knight = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-knight-50.png" ))));
        king = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-king-50.png" ))));
        queen = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-queen-50.png" ))));
        allPieces = Board.theBoard().defaultBoard();
        board = Board.theBoard();
        whitePawn = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50-white.png") ) ) );
        whiteRook = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-rook-50-white.png") ) ) );
        turn = PieceColor.WHITE;
        whiteKnight = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-knight-50-white.png") ) ) );
        whiteBishop = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-bishop-50-white.png") ) ) );
        whiteQueen = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-queen-50-white.png") ) ) );
        whiteKing = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-king-50-white.png") ) ) );
        highlighted  = new Group();
        pieces = new Group();

    }
    public static void main(String[] args) {
        launch();
    }
}