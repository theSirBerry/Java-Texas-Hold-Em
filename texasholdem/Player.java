// Player class

package texasholdem;

import java.util.Scanner;

class Player {
	private int playerBalance = 0;
	public void setPlayerBalance(int newPlayerBalance) { playerBalance = newPlayerBalance; }
	public int getPlayerBalance() { return playerBalance; }
	
	private int currentAnteBet = 0;
	public void setCurrentAnteBet(int newCurrentAnteBet) { currentAnteBet = newCurrentAnteBet; }
	public int getCurrentAnteBet() { return currentAnteBet; }
	
	private int currentCallBet = 0;
	public void setCurrentCallBet(int newCurrentCallBet) { currentCallBet = newCurrentCallBet; }
	public int getCurrentCallBet() { return currentCallBet; }
}

/*
	public int getChoice() {
		Scanner cin = new Scanner(System.in);
		int userEntry;
		boolean runAgain = true;
		
		do {
			System.out.println("\n[1]: Call\t [2]: Fold\t [3]: Quit\t");
			System.out.println("Which of the above options do you choose? [Integer]: ");
			userEntry = cin.nextInt();
		
			switch (userEntry) {
				case 1:
					playerCall();
					runAgain = false;
					return userEntry;
				case 2:
					System.out.println("Player folds");
					runAgain = false;
					return userEntry;
				case 3:
					// Quit Game
					runAgain = false;
					return userEntry;
					// TODO
				default:
					runAgain = true;
			}
		} while (runAgain);
		return 0;
	}
	
	private int balance = 0;
	int getBalance() { return balance; }
	void setBalance(int newBalance) { balance = newBalance; }
	
	private int bet = 0;
	int getBet() { return bet; }
	void setBet(int newBet) { bet = newBet; }
	
	void newBet(int newBetAmount) {
		setBalance(balance - newBetAmount);
		setBet(newBetAmount);
	}
	
	void appendBet(int addedBetAmount) {
		setBalance(balance - addedBetAmount);
		setBet(getBet() + addedBetAmount);
	}
*/