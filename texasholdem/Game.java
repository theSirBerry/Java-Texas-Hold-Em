package texasholdem;

import java.util.Random; // for shuffling the deck

public class Deck {
	
	static public enum Suit { SPADES, CLUBS, DIAMONDS, HEARTS };
	static public final Suit suits[] = Suit.values();

	static public enum Value { ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING };
	static public final Value values[] = Value.values();
	
	// Constructor
	public static Deck() {
		resetDeck();
	}
	
	// Deck Functions [[
	public void resetDeck() {
		for(int i = 1; i <= 52; i++) // 52 cards in a deck
		{
			Suit thisCardSuit;
			Value thisCardValue;
			
			thisCardSuit = suits[ ( i - 1 ) / 13 ]; // Truncate number
			thisCardValue = values[( ( i - 1 ) % 13 )]; // Assign value as 1 - 13
			
			cards[i-1] = new Card(thisCardValue, thisCardSuit);
			
			/*
			if((i % 13) <= 13) // First 13 cards (Spades)
			{
				thisCardSuit = suits[ (i-1) / 13 ]; // Truncate number
				thisCardValue = values[((i-1) % 13)]; // Assign value as 1 - 13
			}
			else if((i % 13) <= 26) // Next 13 cards (Clubs)
			{
				thisCardSuit = suits[ (i-1) / 13 ]; // Truncate number
				thisCardValue = values[((i-1) % 13)];
			}
			else if((i % 13) <= 39) // Next 13 cards (Diamonds)
			{
				thisCardSuit = suits[ (i-1) / 13 ]; // Truncate number
				thisCardValue = values[((i-1) % 13)];
			}
			else // Last 13 cards (Hearts)
			{
				thisCardSuit = suits[ (i-1) / 13 ]; // Truncate number
				thisCardValue = values[((i-1) % 13)];
			}
			*/
		}
	}

	public void shuffleDeck() {
		for(int cardsRemaining = 52; cardsRemaining > 0; cardsRemaining--)
		{
			// Create a new Random class
			Random rng = new Random();
			
			// Select a random number between 0 and cards that need to be shuffled
			int randomNumber = rng.nextInt(cardsRemaining);
			System.out.println("Debug> Random Number: " + randomNumber + " / " + cardsRemaining);
			
			// Save randomly selected card as tempCard
			Card tempCard = getCard(randomNumber);
			
			// Move all cards above selected random card down a slot
			for(int i = randomNumber; i < 51; i++)
			{
				cards[i] = getCard(i + 1);
			}
			
			// Set top-most card to tempCard
			cards[51] = tempCard;
		}
		
		System.out.println("The deck has been shuffled.");
	}
	
	public void printDeck() {
		for (int i = 0; i < 52; i++)
		{
			System.out.println("Card #" + (i+1) + ": " + getCard(i).getValue() + " of " + getCard(i).getSuit() + ".");
		}
	}

	// Deck Functions ]]
	
	// Accessors [[
	Card getCard(int position)
	{
		return cards[position];
	}
	// Accessors ]]
	
	// Mutators [[
	void setCard(int position, Card other)
	{
		cards[position] = other;
	}
	// Mutators ]]
	
	// Cards in my deck
	private Card[] cards = new Card[52];
}