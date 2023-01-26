package application.Model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public void initializeCards() {

		Value[] values = Value.values();
		Suit[] suits = Suit.values();

		for (Value v : values) {

			for (Suit s : suits) {

				Card card = new Card(s, v, false, false);
				cards.add(card);

			}

		}

	}

	public Card pop() throws Exception {

		if (!cards.isEmpty()) {
			Card c = cards.get(0);
			cards.remove(0);
			return c;
		}else {
			throw new Exception("The deck is empty");
		}

	}
	
}
