package com.chess.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DragNDrop extends Application {
    private Image pawn =  new Image( String.valueOf( getClass().getResource("icons/icons8-pawn-50.png") ) );
    private Rectangle piece;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
//        Circle rectangle = createCircle("#ff0fff", "#ff88ff", 100);

        Pane pane  = new Pane();
        String[] board = new String[8];
        for(int i = 0; i < 8; i++){
            Rectangle rectangle = blocks(50, 100+(i*50));
            if(i % 2 ==0){
                board[i ] = "pawn";
               rectangle.setFill(Color.GREEN);
            }else{
                board[i] = "";
                rectangle.setFill(Color.GREENYELLOW);
            }
            pane.getChildren().add( rectangle );
            if(i == 0 || i ==1){
                piece = blocks(50, 100+(i*50));
                piece.setFill(new ImagePattern(pawn));
                dropOn(  rectangle, board, piece );
                pane.getChildren().add( piece  );
            }
        }

        Scene scene = new Scene(pane, 1024, 800, true);
        stage.setScene(scene);
        stage.setTitle("Drag and drop example.");
        stage.show();
    }

    private void dropOn(Rectangle rectangle, String[] board, Rectangle piece) {
        piece.setOnDragDetected(e -> {
        System.out.println("Circle 1 drag detected");
        Dragboard db = piece.startDragAndDrop(TransferMode.COPY_OR_MOVE) ;
        ClipboardContent content = new ClipboardContent();
        content.putImage(((ImagePattern) piece.getFill()).getImage());
        db.setContent(content);
    });

        rectangle.setOnDragOver(event-> {
                int x = (int) rectangle.getLayoutX();
                int y = (int) rectangle.getLayoutY();
            System.out.println("x; " + x);
                if(event.getDragboard().hasImage()){
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
        });
        rectangle.setOnDragDropped( event-> {
            System.out.println("dropped");
            Dragboard db = event.getDragboard();
            if(db.hasImage()){
                System.out.println( "Dropped: " + db.getImage() );
                piece.setLayoutY( rectangle.getLayoutY() );
                piece.setLayoutX( rectangle.getLayoutX() );
                event.setDropCompleted( true );
            }else{
                event.setDropCompleted( false );
            }
            event.consume();
        });
    }

    public Rectangle blocks(int size, int position){
       Rectangle rect = new Rectangle(size, size);
       rect.setLayoutX(position);
       rect.setLayoutY(200);
       return rect;
    }
    private Circle createCircle(String strokeColor, String fillColor, int x) {
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(200);
        circle.setRadius(50);
        circle.setStroke(Color.valueOf(strokeColor));
        circle.setStrokeWidth(5);
        circle.setFill(Color.valueOf(fillColor));
        return circle;
    }
}