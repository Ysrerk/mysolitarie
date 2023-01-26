package application;

import java.util.Iterator;

import application.View.Header;
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

	private static final String TITTLE="Solitarie Game";
	Group root = new Group();
	Deck deck = new Deck();
	Foundation foundation=new Foundation(root);
	Header header;
	
	@Override
	public void start(Stage primaryStage) {

		definition(primaryStage);
		header.createHeader();







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

	public void definition(Stage primaryStage){
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle(TITTLE);
		primaryStage.setResizable(false);
		foundation.createFoundations();
		scene.setFill(Color.GREEN);
		header=new Header(root);


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
		;
		Rectangle resetButton = foundation.createCardbase(10,50);
		Image img = new Image("icon-reset.png");
		resetButton.setFill(new ImagePattern(img));
		root.getChildren().add(resetButton);
		
		resetButton.setOnMouseClicked((event) -> resetHand());
	}

	public void resetHand() {
			deck.resetHand();
	}

	private void renderWaste() {
		//deneme
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

}
