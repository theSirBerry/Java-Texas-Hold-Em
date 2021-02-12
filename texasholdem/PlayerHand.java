package texasholdem;

public class HandBoard implements Board {
	public void getCardFromDeck(Deck deck) {
		Card newCard = deck.dealCard();
		
		// Debug for now
		System.out.print("New Card: ");
		newCard.printCard();
		
		setCardFromHand(newCard, getHandSize());
		
		setHandSize(getHandSize()+1);
		System.out.println("Hand size: " + getHandSize());
	}
	
	// Accesssor [[
	Card getCardfromHand(int index) { return playerHand[index]; }
	int getHandSize() { return handSize; }
	// Accesssor ]]
	
	// Mutator [[
	void setCardFromHand(Card newCard, int index) { playerHand[index] = newCard; }
	void setHandSize(int newHandSize) { handSize = newHandSize; }
	// Mutator ]]
	
	// Variables [[
	private Card[] playerHand = new Card[2];
	private int handSize = 0;
	// Variables ]]
}