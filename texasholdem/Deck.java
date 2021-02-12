package texasholdem;

import java.util.Random; // for shuffling the deck

public class Deck {

	// Constructor
	public Deck() {
		resetDeck();
	}
	
	// Deck Functions [[
	public void resetDeck() {
		for(int i = 1; i <= 52; i++) // 52 cards in a deck
		{
			cards[i-1] = new Card(i);
		}
		setDeckSize(52);
	}

	public void shuffleDeck() {
		for(int cardsRemaining = 52; cardsRemaining > 0; cardsRemaining--)
		{
			// Create a new Random class
			Random rng = new Random();
			
			// Select a random number between 0 and cards that need to be shuffled
			int randomNumber = rng.nextInt(cardsRemaining);
			
			// Debug out the random number sorting
		 // System.out.println("Debug> Random Number: " + randomNumber + " / " + cardsRemaining);
			
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
		
	 // System.out.println("The deck has been shuffled.");
	}
	
	public void printDeck() {
		for (int i = 0; i < 52; i++)
		{
			System.out.println("Card #" + (i+1) + ": " + getCard(i).getValue() + " of " + getCard(i).getSuit() + ".");
		}
	}
	
	public Card dealCard() {
		Card dealtCard = getCard((getDeckSize()-1));
		cards[getDeckSize()-1] = null;
		
		setDeckSize(getDeckSize()-1);
		
		return dealtCard;
	}

	// Deck Functions ]]
	
	// Accessors [[
	Card getCard(int position) { return cards[position]; }
	
	int getDeckSize() { return deckSize; }
	// Accessors ]]
	
	// Mutators [[
	void setCard(int position, Card other) { cards[position] = other; }
	void setDeckSize(int newDeckSize) { deckSize = newDeckSize; }
	// Mutators ]]
	
	// Cards in my deck
	private Card[] cards = new Card[52];
	private int deckSize = 0;
}