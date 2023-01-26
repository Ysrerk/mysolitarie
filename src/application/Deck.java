package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Deck {
	public Stack<Card> cards = new Stack<>();
	public List<Stack<Card>> piles = new ArrayList<Stack<Card>>();
	public Stack<Card> waste = new Stack<>();
	private final Random random = new Random();

	Deck() {
	}

	public void createDeck() {
		String[] suits = { "clubs", "diamonds", "hearts", "spades" };
		String[] ranks = { "ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king" };
		int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

		for (int i = 0; i < suits.length; i++) {
			for (int j = 0; j < ranks.length; j++) {
				String imgUrl = ranks[j].charAt(0) == '1' ? "t" + suits[i].charAt(0) + ".gif"
						: ranks[j].charAt(0) + "" + suits[i].charAt(0) + ".gif";


				
				this.cards.push(new Card(suits[i], ranks[j], values[j], imgUrl));
			}
		}
	}

	public void shuffleDeck() {
		for (int i = 0; i < 52; i++) {
			swapCard(random.nextInt(52 - i), 51 - i);
		}
	}

	private void swapCard(int i1, int i2) {

			Card temp = this.cards.get(i1);
			this.cards.set(i1, this.cards.get(i2));
			this.cards.set(i2, temp);


	}
    
	// distribute cards into piles
	public void distributeCard() {
		for (int i = 0; i < 7; i++) {
			Stack<Card> stack = new Stack<>();
			for (int j = 0; j < i + 1; j++) {
				stack.push(this.cards.pop());
			}
			// make the top card face up
			Card topCard = stack.pop();
			topCard.faceUp = true;
			stack.push(topCard);
			this.piles.add(stack);
		}
	}
	
	// when no card left in hand, we push the waste again into the stock
	// this might require re-shuffle
	public void resetHand() {
		int size = waste.size();
		for (int i = 0; i < size; i++) {
			this.cards.push(waste.pop());
		}
	}
}
