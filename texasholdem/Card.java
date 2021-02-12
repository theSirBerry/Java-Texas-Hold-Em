package texasholdem;

public class Card {
	
	static public enum Suit { SPADES, CLUBS, DIAMONDS, HEARTS };
	static public final Suit suits[] = Suit.values();

	static public enum Value { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };
	static public final Value values[] = Value.values();
	
	// Constructors [[
	public Card(int cardID) {
		value = values[( ( cardID - 1 ) % 13 )]; // Assign value as 1 - 13
		suit = suits[ ( cardID - 1 ) / 13 ]; // Truncate number
	}
	
	// Optional, but not used
	public Card(Value newValue, Suit newSuit) {
		value = newValue;
		suit = newSuit;
	}
	// Constructors ]]
	
	// Card accessors [[
	public Suit getSuit(){ return suit; }
	public Value getValue(){ return value; }
	// Card accessors ]]
	
	public void printCard() {
		System.out.print(getValue() + " of " + getSuit());
	}
	
	private final Suit suit;
	private final Value value;
}