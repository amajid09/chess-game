package com.chess.game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    private Draggable draggable;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        int x = 200;
//        int y = 200;
//        draggable = new Draggable();
//        Grid grid = new Grid(anchorPane);
//        grid.drawGrid();
//        Text piece = new Text("♟\uFE0F");
//        piece.setY(y);
//        piece.setX(x);
//
//        piece.setFont(Font.font(100));
//        System.out.println(piece.getFont().getSize());
//        draggable.makeDraggable(piece, x, y);
//        anchorPane.getChildren().add(piece);
//        System.out.println(anchorPane.getChildren());



    }
}

class Draggable{
    private double mouseAnchorX;
    private double mouseAnchorY;
    private final int gridSize = 100;

    public void makeDraggable(Node node, int startX, int startY) {

        node.setOnMouseDragged(event -> {
            mouseAnchorX = event.getSceneX();
            mouseAnchorY = event.getSceneY();

            int x = (int) (( mouseAnchorX / gridSize ) % 8 ) * gridSize;
            int y = (int) (( mouseAnchorY / gridSize ) % 8 ) * gridSize;

            node.setLayoutX( x - startX );
            node.setLayoutY( y - startY + gridSize );
        });
    }
}