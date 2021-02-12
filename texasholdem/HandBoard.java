package texasholdem;

public class HandBoard implements Board {
	public void getCardFromDeck(Deck deck) {
		Card newCard = deck.dealCard();
		
		// Debug for printing out card
	 // System.out.print("New Card: ");
	 // newCard.printCard();
	 // System.out.println("\tHand size: " + getHandSize());
		
		setCardFromHand(newCard, getHandSize());
		
		setHandSize(getHandSize()+1);
	}
	
	public void printCards() {
		for(int i = 0; i < handSize; i++) {
			System.out.print("    ");
			getCardFromHand(i).printCard();
		}
		
		System.out.println("");
		System.out.println("");
		
	}
	
	// Accesssor [[
	Card getCardFromHand(int index) { return playerHand[index]; }
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