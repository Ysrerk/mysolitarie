package application.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Header {
    Group root;

    public Header(Group root) {
        this.root = root;
    }

    public void createHeader() {
        // create header
        HBox header = new HBox(8);
        header.setPrefWidth(810);
        header.setPrefHeight(40);
        header.setPadding(new Insets(10, 5, 5, 5));
        header.setStyle("-fx-background-color: #555555;");
        header.setAlignment(Pos.BASELINE_CENTER);

        Text timeText = new Text("  00:00:00  ");
        timeText.setFill(Color.WHITE);
        header.getChildren().add(timeText);

        Text scoreText = new Text("  Score: 0  ");
        scoreText.setFill(Color.WHITE);
        header.getChildren().add(scoreText);

        Text movesText = new Text("  Moves:  0  ");
        movesText.setFill(Color.WHITE);
        header.getChildren().add(movesText);

        root.getChildren().add(header);
    }
}
