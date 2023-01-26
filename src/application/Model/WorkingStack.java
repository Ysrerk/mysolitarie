package application.Model;

import java.util.Stack;

public class WorkingStack {
	
	private Stack<Card> cards;
	
	public WorkingStack() {
		this.cards = new Stack<Card>();
	}
	
	
	public boolean addCardsToWorkingStack( Card card) {
		if(cards.isEmpty() && card.getSuit().equals(Value.KING)) {
			this.cards.add(card);
			return true;
		}
		
		Card top = this.cards.peek();
		card.checkStackable(top);
		
		if(top.isStackable() && card.isStackable()) {
			cards.add(card);
			return true;
		}
		
		return false;
	}
}
