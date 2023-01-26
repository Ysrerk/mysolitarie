package application;

import java.util.Iterator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
	private final double CARD_WIDTH = 70;
	private final double CARD_HEIGHT = 95;
	private final double WINDOW_WIDTH = 810;
	private final double WINDOW_HEIGHT = 500;
	Group root = new Group();
	Deck deck = new Deck();
	
	@Override
	public void start(Stage primaryStage) {
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		scene.setFill(Color.GREEN);
		createHeader();
		createFoundations();
		addResetHandButton();
		addResetGameButton();
		addStock();
		// start Game
		startGame();
		root.applyCss();
		root.layout();
		primaryStage.show();
	}

	private void addResetGameButton() {
		Button newBtn = new Button("New Game");
		root.getChildren().add(newBtn);
		newBtn.setLayoutX(380);
		newBtn.setLayoutY(450);
		newBtn.layout();

		newBtn.setOnMouseClicked(event -> {
			System.out.println("new button clicked!");
		});
	}

	private void addStock() {
		Image cardBack = new Image("cardBack.png", CARD_WIDTH, CARD_HEIGHT, false, false);
		ImageView backView = new ImageView(cardBack);
		backView.setX(10);
		backView.setY(50);
		root.getChildren().add(backView);
		
		backView.setOnMouseClicked(event -> {
			if(deck.cards.size() == 0) {
				root.getChildren().remove(backView);
				return;
			}
			
			Card lastCard = deck.cards.pop();
			deck.waste.push(lastCard);
			this.renderWaste();
			System.out.println(deck.waste.size() + "," + deck.cards.size());
		});
		
	}

	private void createFoundations() {
	
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

	private void createHeader() {
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

	private void startGame() {
		deck.createDeck();
		deck.shuffleDeck();
		deck.distributeCard();
		renderGame();
	}

	private void renderGame() {
		int xOffset = 100;
		Image renderedCard;
		for (int i = 0; i < deck.piles.size(); i++) {
			Iterator<Card> card = deck.piles.get(i).iterator();
			int step = 0;
			while (card.hasNext()) {
				Card currentCard = card.next();
				
				if(!currentCard.faceUp) {
					renderedCard = new Image("cardBack.png", CARD_WIDTH, CARD_HEIGHT, false, false);
				} else{
					renderedCard = new Image(currentCard.imgUrl, CARD_WIDTH, CARD_HEIGHT, false, false);
				}
				ImageView iView = new ImageView(renderedCard);
				iView.setX(xOffset);
				iView.setY(50 + step);
				step += 30;

				iView.setOnMouseClicked((MouseEvent e) -> {
					e.consume();
					System.out.println("card clicked");
				});

				root.getChildren().add(iView);
			}
			xOffset += 90;
		}

		
	}

	private void addResetHandButton() {
		Rectangle resetButton = createCardbase(10, 50);
		Image img = new Image("icon-reset.png");
		resetButton.setFill(new ImagePattern(img));
		root.getChildren().add(resetButton);
		
		resetButton.setOnMouseClicked((event) -> resetHand());
	}

	public void resetHand() {
			deck.resetHand();
	}

	private void renderWaste() {
		ImageView wasteView;
		if(deck.waste.size() == 0) {
			return;
		}
		
		Card currentCard = deck.waste.peek();
		Image cardFace = new Image(currentCard.imgUrl, CARD_WIDTH, CARD_HEIGHT, false, false);
		wasteView = new ImageView(cardFace);
		wasteView.setX(10);
		wasteView.setY(180);
		root.getChildren().add(wasteView);
	}

	// Main method to run the program
	public static void main(String[] args) {
		launch(args);
	}

	// Function to create cardBase rectangles for demo
	public Rectangle createCardbase(double x, double y) {
		Rectangle r = new Rectangle(CARD_WIDTH, CARD_HEIGHT);
		r.setX(x);
		r.setY(y);
		r.setFill(Color.DARKGREEN);
		r.setStroke(Color.CORAL);
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
