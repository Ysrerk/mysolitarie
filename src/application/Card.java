package application;

public class Card {
	public String suite;
	public String rank;
	public int value;
	public String imgUrl;
	public boolean selected = false;
	public boolean faceUp = false;

	Card(String suite, String rank, int value, String imgUrl) {
		this.suite = suite;
		this.rank = rank;
		this.value = value;
		this.imgUrl = imgUrl;
	}
	
	public String getColor() {
		if(this.suite.startsWith("h") || this.suite.startsWith("d")) {
			return "red";
		}
		
		return "black";
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	void toggleSelected() {
		selected = !selected;
	}
}
