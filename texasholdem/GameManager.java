// Java Texas Hold'em Program
// Author: Berry
package texasholdem;

// Receiving input
import java.util.Scanner;

class GameManager {

	public static void main(String args[]) {
		
		program:{
			// Create object to read from console input
			Scanner cin = new Scanner(System.in);
			int menuEntry;
			boolean runProgram = true;
			boolean runTexasHoldEm = false;
			
			int playerBalance = 500;
			
			do {
				System.out.println("[1] Play Game");
				System.out.println("[2] Print Rules");
				System.out.println("[3] Print Credits");
				System.out.println("[4] Quit");
				System.out.print("Which of the above would you like to do? [Integer]: ");
				
				menuEntry = cin.nextInt();
				
				switch(menuEntry)
				{
					case 1:
					{
						do {
							playgame:{
								int playerChoice = 0;
								
								Player myPlayer = new Player();
								
								// Set balance from previous game
								myPlayer.setPlayerBalance(playerBalance);
								
								// Get new ante
								boolean validEntry = false;
								int userEntry;
								
								do {
									System.out.print("\nCurent Balance: $" + myPlayer.getPlayerBalance() + "\nPlease enter a starting Ante : ");
									userEntry = cin.nextInt();
									if(userEntry > ((myPlayer.getPlayerBalance() - userEntry) / 2))
									{
										System.out.println("Error, cannot bet entered Ante. Remaining balance must be at least double entered Ante. Try again.");
										validEntry = false;
									}
									else if(!(userEntry > ((myPlayer.getPlayerBalance() - userEntry) / 2)))
									{
										System.out.println("Ante of $" + userEntry + " accepted.");
										
										myPlayer.setPlayerBalance(myPlayer.getPlayerBalance() - userEntry);
										myPlayer.setCurrentAnteBet(userEntry);
										
										System.out.println("Current Balance: $" + myPlayer.getPlayerBalance());
										
										validEntry = true;
									}
									else
									{
										System.out.println("Entry not recognized, try again.");
									}
								} while (!validEntry);
						//myPlayer.newAnte();

								// Create new Deck named myDeck
								Deck myDeck = new Deck();
								
								//myDeck.printDeck();
								
								myDeck.shuffleDeck();
								
								//myDeck.printDeck();
								
								// Create and set player hand
								HandBoard playerHand = new HandBoard();
								playerHand.getCardFromDeck(myDeck);
								playerHand.getCardFromDeck(myDeck);
								
								// Create and set dealer hand
								HandBoard dealerHand = new HandBoard();
								dealerHand.getCardFromDeck(myDeck);
								dealerHand.getCardFromDeck(myDeck);
								
								// Create and set center deck (start with 3)
								CenterBoard centerBoard = new CenterBoard();
								for(int i = 0; i < 3; i++)
								{
									centerBoard.getCardFromDeck(myDeck);
								}
								
								// Table is set and cards are dealt
								
								// Show Cards
								System.out.println("\n=================================================================\n");
								centerBoard.printCards();
								
								System.out.println("\n\n\n\t\t\tPlayer cards: \n");
								System.out.print("            ");
								playerHand.printCards();
								System.out.println("\n=================================================================\n");
								// Show Cards
								
							 // System.out.println("[1]: Call\t [2]: Fold\t [3]: Quit\t");
							 // System.out.print("Which of the above options do you choose? [Integer]: ");
								
								boolean isValidChoice = false;
								
								do {
									System.out.print("\nCurent Balance: $" + myPlayer.getPlayerBalance() + ".\n");
									System.out.println("[1]: Call\t [2]: Fold\t [3]: Quit\t");
									System.out.print("Which of the above options do you choose? [Integer]: ");
									userEntry = cin.nextInt();

									switch (userEntry) {
										case 1:
											System.out.println("Player calls");

											myPlayer.setCurrentCallBet(myPlayer.getCurrentAnteBet() * 2);
											myPlayer.setPlayerBalance(myPlayer.getPlayerBalance() - myPlayer.getCurrentCallBet());
											
											System.out.println("Current Balance: " + myPlayer.getPlayerBalance());
											
											isValidChoice = true;
											
											centerBoard.getCardFromDeck(myDeck);
											centerBoard.getCardFromDeck(myDeck);

											break;
										case 2:
											System.out.println("Player folds");
											
											System.out.println("Balance Before Game: $" + (myPlayer.getPlayerBalance() + myPlayer.getCurrentAnteBet()) + ".");
											
											System.out.println("Losses: $" + myPlayer.getCurrentAnteBet() + ".");
											
											System.out.println("Current Balance: $" + myPlayer.getPlayerBalance() + ".");
											
											// Set main balance
											playerBalance = myPlayer.getPlayerBalance();
											
											break playgame;
										case 3:
											System.out.println("Thank you for playing!");
											break program;
										default:
											System.out.println("Error > Invalid Choice. Try again.");
											isValidChoice = false;
											break;
									}
								} while (!isValidChoice);
								
								// Show cards
								System.out.println("\n=================================================================\n");
								centerBoard.printCards();
								
								System.out.println("\n\n\n\t\t\tPlayer cards: \n");
								System.out.print("            ");
								playerHand.printCards();
								System.out.println("\n=================================================================\n");
								// Show cards
								

								CardCalculator playerCalc = new CardCalculator(playerHand, centerBoard);
								CardCalculator dealerCalc = new CardCalculator(dealerHand, centerBoard);
								
							 // System.out.println(playerCalc.getFinalHandScore());
							 // System.out.println(dealerCalc.getFinalHandScore());
								
								// Print Stuff
								
								System.out.println();
								
								System.out.print("Player Starting Cards: ");
								playerHand.printCards();
								
								System.out.println("Player Best Hand: ");
								playerCalc.printFinalHandCards();
								
								System.out.print("\nDealer Starting Cards: ");
								dealerHand.printCards();
								
								System.out.println("Dealer Best Hand: ");
								dealerCalc.printFinalHandCards();
								System.out.println();
								System.out.println("\n=================================================================\n");
								
								// Player Wins if their score beats dealer's or if dealer's score is less than a pair of 4s (20500000)
								if((playerCalc.getFinalHandScore() > dealerCalc.getFinalHandScore()) || (dealerCalc.getFinalHandScore() < 20500000))
								{
									System.out.println("\nThe Player's hand wins!");
									System.out.println("Previous Balance: $" + myPlayer.getPlayerBalance() + ".");
									
									System.out.println("Winnings: $" + ((myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet()) * 2) + ".");
									
									myPlayer.setPlayerBalance(myPlayer.getPlayerBalance() + ((myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet()) * 2));
									
									System.out.println("Current Balance: $" + myPlayer.getPlayerBalance() + ".");
									
									// Set main balance
									playerBalance = myPlayer.getPlayerBalance();
								}
								else if(playerCalc.getFinalHandScore() < dealerCalc.getFinalHandScore() && (dealerCalc.getFinalHandScore() >= 20500000))
								{
									System.out.println("The Dealer's hand wins.");
									System.out.println("Balance Before Game: $" + (myPlayer.getPlayerBalance() + myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet()) + ".");
									
									System.out.println("Losses: $" + ((myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet())) + ".");
									
									System.out.println("Current Balance: $" + myPlayer.getPlayerBalance() + ".");
									
									// Set main balance
									playerBalance = myPlayer.getPlayerBalance();
								}
								else if((playerCalc.getFinalHandScore() == dealerCalc.getFinalHandScore()))
								{
									if((dealerCalc.getFinalHandScore() < 20500000))
									{
										System.out.println("\nThe Player's hand wins!");
										System.out.println("Previous Balance: $" + myPlayer.getPlayerBalance() + ".");
										
										System.out.println("Winnings: $" + ((myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet()) * 2) + ".");
										
										myPlayer.setPlayerBalance(myPlayer.getPlayerBalance() + ((myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet()) * 2));
										
										System.out.println("Current Balance: $" + myPlayer.getPlayerBalance() + ".");
										
										// Set main balance
										playerBalance = myPlayer.getPlayerBalance();
									}
									else
									{
										// Hands push
										System.out.println("\nBoth hands are of equal value, a push.");
										System.out.println("Previous Balance: $" + myPlayer.getPlayerBalance() + ".");
										
										System.out.println("Value of Ante + Call: $" + ((myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet())) + ".");
										
										myPlayer.setPlayerBalance(myPlayer.getPlayerBalance() + (myPlayer.getCurrentAnteBet() + myPlayer.getCurrentCallBet()));
										
										System.out.println("Current Balance: $" + myPlayer.getPlayerBalance() + ".");
										
										// Set main balance
										playerBalance = myPlayer.getPlayerBalance();
									}
								}
								else{ // Bug
									System.out.println("WARNING: THIS ERROR SHOULD NEVER HAPPEN.");
								}

							} // end of playgame code block

							boolean validResponse = false;
							int response = 0;
							
							do {
								System.out.print("\nWould you like to play again? [1] for yes, [2] for no, [3] for quit: ");
								response = cin.nextInt();
								
								switch(response)
								{
									case 1:
										runTexasHoldEm = true;
										validResponse = true;
										break;
									case 2:
										runTexasHoldEm = false;
										validResponse = true;
										break;
									case 3:
										System.out.println("Thank you for playing!");
										break program;
									case 0:
										validResponse = false;
										break;
									default:
										validResponse = false;
										break;
								}
							} while (!validResponse);
							
						} while (runTexasHoldEm);
						
						break;
					}
					case 2:
					{
						System.out.println("\n\n=================================================================\n\n");
						System.out.println("RULES");
						System.out.println("The game is played with a standard 52 card deck.");
						System.out.println("The player makes an Ante bet.");
						System.out.println("The player and dealer are both dealt two cards (face down).");
						System.out.println("Three cards are then dealt to the board and will eventually contain five cards.");
						System.out.println("After checking his/her cards, the player has to decide (a) to fold with no further play losing the Ante bet or (b) to make a Call bet of double the Ante bet.");
						System.out.println("If one or more players makes a Call bet the dealer will deal two more cards to the board, for a total of five.");
						System.out.println("Players and dealer make their best five card poker hand from their own two personal cards and five board cards.");
						System.out.println("Each player’s hand are compared with the dealer’s.");
						System.out.println("The dealer must have a pair of 4s or better to qualify.");
						System.out.println("If the dealer does not qualify, the Ante bet pays according to the AnteWin pay table and the Call bet is a push (stand off).");
						System.out.println("If the dealer qualifies, and the player's hand is better than the dealer's, the Ante bet pays according to the Ante-Win pay table and the Call bet pays 1 to 1.");
						System.out.println("If the dealer qualifies, and the dealer's hand is equal to the player's, all bets are push (it doesn't win or lose).");
						System.out.println("If the dealer qualifies, and the dealer's hand is better than the player's, the player loses all bets.");
						System.out.println("\n\n=================================================================\n\n");
						break;
					}
					case 3:
					{
						System.out.println("\n\n=================================================================\n\n");
						System.out.println("Credits: David Berry");
						System.out.println("\n\n=================================================================\n\n");
						break;
					}
					case 4:
					{
						System.out.println("Thank you for playing!");
						runProgram = false;
						break;
					}
					default:
					{
						// Do nothing
					}
				}
				
			} while(runProgram);
		} // program code block "break program;"
	}
}