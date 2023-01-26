package application.Model;

public class Card {
	private Suit suit;
	private Value value;
	private boolean visible;
	private boolean stackable;
	
	public Suit getSuit() {
		return suit;
	}
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isStackable() {
		return stackable;
	}
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}
	public void checkStackable(Card c) {
		
		if(c.getIntegerValue() - this.getIntegerValue() == 1 && 
				!c.getColor().equals(this.getColor())) {
			setStackable(true);
		}else {
			setStackable(false);
		}
		
	}
	public int getIntegerValue() {
		return Value.getValue(value);
	}
	
	public String getColor() {
		return Suit.gerColor(this.suit);
	}
	public Card(Suit suit, Value value, boolean visible, boolean stackable) {
		super();
		this.suit = suit;
		this.value = value;
		this.visible = visible;
		this.stackable = stackable;
	}
}
