//

package texasholdem;

public class CenterBoard implements Board {
	public void getCardFromDeck(Deck deck) {
		Card newCard = deck.dealCard();
		
		// Debug for now
	 // System.out.print("New Card: ");
	 // newCard.printCard();
		
		setCardFromHand(newCard, getHandSize());
		
		setHandSize(getHandSize()+1);
	 // System.out.println("Hand size: " + getHandSize());
	}
	
	public void printCards() {
		System.out.println("\n\t\t\tCenter Cards:\n");
		
		if(getHandSize() < 3)
		{
			for(int i = 0; i < getHandSize(); i++) {
				
				getCardFromHand(i).printCard();
				System.out.print("\t");
			}
		}
		else
		{
			for(int i = 0; i < 3; i++)
			{
				System.out.print("     ");
				getCardFromHand(i).printCard();
			}
			if(getHandSize() >= 3)
			{
				System.out.println();
				System.out.print("\t\t");
				for(int i = 3; i < getHandSize(); i++) {
					
					getCardFromHand(i).printCard();
					System.out.print("    ");
				}
			}
		}
		
		// for(int i = 0; i < handSize; i++) {
			// getCardFromHand(i).printCard();
			// System.out.println();
		// }
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
	private Card[] playerHand = new Card[5];
	private int handSize = 0;
	// Variables ]]
}