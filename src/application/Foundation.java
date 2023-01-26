package application;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Foundation {

    private final double CARD_WIDTH = 70;
    private final double CARD_HEIGHT = 95;

    Group root;

    public Foundation(Group root) {
        this.root = root;
    }

    public void createFoundations() {

        // create foundations
        Rectangle foundationHeart = createCardbase(730, 50);
        Image img = new Image("heart.png");
        foundationHeart.setFill(new ImagePattern(img));

        Rectangle foundationDiamond = createCardbase(730, 155);
        Image img2 = new Image("diamond.png");
        foundationDiamond.setFill(new ImagePattern(img2));

        Rectangle foundationClub = createCardbase(730, 260);
        Image img3 = new Image("club.png");
        foundationClub.setFill(new ImagePattern(img3));
        Rectangle foundationSpade = createCardbase(730, 365);
        Image img4 = new Image("spade.png");
        foundationSpade.setFill(new ImagePattern(img4));

        root.getChildren().add(foundationHeart);
        root.getChildren().add(foundationDiamond);
        root.getChildren().add(foundationClub);
        root.getChildren().add(foundationSpade);
    }
    public Rectangle createCardbase(double x, double y) {
        Rectangle r = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
        r.setX(x);
        r.setY(y);
        r.setFill(Color.YELLOW);
        r.setStroke(Color.PINK);
        r.setStrokeWidth(3);
        r.setOnMouseClicked(event -> {
            try {
                /*
                 * Rectangle target = (Rectangle) event.getTarget(); if (target.getFill() ==
                 * Color.GOLD) { target.setFill(Color.BLACK); } else {
                 * target.setFill(Color.GOLD); }
                 */
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return r;
    }
}
