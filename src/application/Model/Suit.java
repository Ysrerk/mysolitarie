package application.Model;

public enum Suit {
	SPADE,
	DIAMOND,
	CLUB,
	HEART;
	
	public static String gerColor(Suit suit) {
		
		switch (suit) {
		case SPADE: {
			return "black";
		}
		case DIAMOND: {
			return "red";
		}
		case CLUB: {
			return "black";
		}
		case HEART: {
			return "red";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + suit);
		}
		
	}
}
