package application.Model;

import java.util.Stack;

public class Discard {

	private Stack<Card> cards;
	
	public Discard() {
		cards = new Stack<Card>();
	}
	
	public Card pop() throws Exception {
		if (cards.isEmpty()) {
			throw new Exception("Dsicard is empty");
		}
		return cards.pop();
	}
	
	public void addToDiscard(Card c) {
		c.setVisible(true);
		cards.add(c);
	}
	
}
