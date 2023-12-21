module com.chess.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens com.chess.game to javafx.fxml;
    exports com.chess.game;
}