package com.chess.game;

import com.chess.game.pieces.Piece;
import com.chess.game.pieces.PieceColor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class BoardView extends Application {
    public static final int RECT_SIZE = 50;
    public static final int GRID_SIZE = 8;
    private double startX, startY;
    private final ImagePattern pawn = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50.png") ) ) );
    private final ImagePattern bishop = new ImagePattern( new Image(String.valueOf( getClass().getResource("icons/icons8-bishop-50.png" ) ) ) );
    private final ImagePattern rook = new ImagePattern( new Image(String.valueOf( getClass().getResource("icons/icons8-rook-50.png" ))));
    private final ImagePattern knight = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-knight-50.png" ))));
    private final ImagePattern king = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-king-50.png" ))));
    private final ImagePattern queen = new ImagePattern( new Image(String.valueOf( getClass().getResource( "icons/icons8-queen-50.png" ))));
    Piece[][] allPieces = Board.theBoard().defaultBoard();
    private final Board board = Board.theBoard();
    private double currX;
    private double currY;
    private int prevX, prevY;
    private final ImagePattern whitePawn = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50-white.png") ) ) );
    private final ImagePattern whiteRook = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-rook-50-white.png") ) ) );
    private PieceColor turn = PieceColor.WHITE;
    private final ImagePattern whiteKnight = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-knight-50-white.png") ) ) );
    private final ImagePattern whiteBishop = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-bishop-50-white.png") ) ) );
    private final ImagePattern whiteQueen = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-queen-50-white.png") ) ) );
    private final ImagePattern whiteKing = new ImagePattern( new Image( String.valueOf( getClass().getResource("icons/icons8-king-50-white.png") ) ) );
    private final Group highlighted  = new Group();
    private final Group pieces = new Group();

    @Override
    public void start( Stage stage ) throws IOException {
        Group grid = new Group();
        Pane root = new Pane(grid, pieces, highlighted);

        System.out.println(getClass().getResource("icons/icons8-pawn-50.png"));
        drawBoard(grid);
        placePieces();
        Scene scene = new Scene( root );
        stage.setTitle( "Hello!" );
        stage.setScene( scene );
        stage.show();
    }


    private void drawBoard(Group root) {
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
        rectangle.setLayoutX( RECT_SIZE * j);
        String piece = allPieces[i][j].getPiece();
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
        clickOnPiece(rectangle);
        pieces.getChildren().add(rectangle);

    }
//    click on piece which will show all available positions and by clicking on one of the available position will the piece move to that position
    private void clickOnPiece(Rectangle piece) {
       piece.setOnMouseClicked(e-> {

           int i = (int)  ( e.getSceneY() / RECT_SIZE % GRID_SIZE );
           int j = (int)  ( e.getSceneX() / RECT_SIZE % GRID_SIZE );
           System.out.printf("col %d row %d \n", j, i);
           System.out.printf("Available space  %s \n", board.getPieces()[i][j].validMoves(allPieces, new Position(j, i)));
           List<Position> availablePosition = board.getPieces()[i][j].validMoves(allPieces, new Position(j, i));
           if(board.getPieces()[i][j].getColor().equals(turn))
                highlightSquare(availablePosition, piece);
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
           System.out.printf("Clicked on the highlighted rectangle @( %d, %d)\n " , x, y);
           System.out.printf("@( %d, %d) " , col, row);
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

    public static void main(String[] args) {
        launch();
    }
}