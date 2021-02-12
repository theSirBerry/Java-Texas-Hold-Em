package texasholdem;

import java.util.Arrays;

class CardCalculator {
	CardCalculator (HandBoard aHand, CenterBoard centerBoard)
	{
		for(int i=0; i<2; i++)
		{
			setPotentialCard(aHand.getCardFromHand(i), i); 
		}
		for(int i=2; i<7; i++)
		{
			setPotentialCard(centerBoard.getCardFromHand(i-2), i);
		}
		
	 // printCards();
		convertCardsToValues();
	 // printCardValues();
		sortCardValues();
	 // printSortedCardValues();
		
		checkForFlush();
		
		checkForPair();
	 // System.out.println("Has pair of : " + getPairValue());
		
		checkForThreeOfAKind();
	 // System.out.println("Has three: " + getThreeOfAKindValue() + "s.");
		
		checkForFourOfAKind();
	 // System.out.println("Has four of : " + getFourOfAKindValue() + "s.");
		
		checkForStraight();
	 // System.out.println("Has straight starting at number: " + getStraightStartingValue() + ".");
		
	 // System.out.println("First pair = " + hasPair() + ".\t" + "Second pair = " + hasSecondPair() + ".");
		
		createFinalHand();
		
		
	 // turnFinalHandToCards();
		
	 // for( int i = 0; i < 5; i++ )
	 // {
	 // 	getFinalHandCard(i).printCard();
	 // 	System.out.println();
	 // }
	}
	
	public void printFinalHandCards() {
		for( int i = 0; i < 5; i++ )
		{
			getFinalHandCard(i).printCard();
			System.out.println();
		}
	}
	
	public void createFinalHand() {
		// 1. Royal Flush A K Q J 10
		if((hasStraight()) && (hasFlush()) && (straightStartingValue == 10))
		{
			for(int i = 0; i < 5; i++)
			{
				setFinalHandValue(getSortedCardValue(getStraightIndex() + i), i);
			}
			turnFinalHandToCards();
			
			// 1000000 base + 1000*Highest Value
			setFinalHandScore(100000000 + (getFinalHandValue(4) * 100000));
		}
		
		// 2. Straight flush
		if((hasStraight()) && (hasFlush()))
		{
			for(int i = 0; i < 5; i++)
			{
				setFinalHandValue(getSortedCardValue(getStraightIndex() + i), i);
			}
			// DEBUG
			//System.out.println("FINAL HAND");
			//for(int i = 0; i < 5; i++)
			//{
			//	System.out.println(getFinalHandValue(i));
			//}
			
		turnFinalHandToCards();
		
		// Set how good hand is
		setFinalHandScore(90000000 + (getFinalHandValue(4) * 100000));
		}
		
		// 3. Four of a Kind
		else if(hasFourOfAKind())
		{
			for(int i = 0; i < 4; i++)
			{
				setFinalHandValue(getFourOfAKindValue(), i);
			}
			
			// Get kicker
			
			// First get helping hand ready
			for(int i = 0; i < 7; i++)
			{
				if(getSortedCardValue(i) != getFourOfAKindValue())
				{
					setHelpingHandValue(getSortedCardValue(i), i);
				}
				else
				{
					setHelpingHandValue(0, i);
				}
			}
			
			// Find max of helping hands
			int max = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
				}
			}

			setFinalHandValue(max, 4);
			// DEBUG
		 // System.out.println("FINAL HAND");
		 // for(int i = 0; i < 5; i++)
		 // {
		 // 	System.out.println(getFinalHandValue(i));
		 // }
			
			//                Four of a kind + Which card is 4 of a kind + Kicker
			setFinalHandScore(80000000 + (getFinalHandValue(0) * 100000) + (getFinalHandValue(4) * 1));
			
			turnFinalHandToCards();
		}
		
		// 4. Full house
		else if(hasThreeOfAKind() && hasSecondPair())
		{
			for(int i = 0; i < 3; i++)
			{
				// setFinalHandValue(getThreeOfAKindValue(), i);
				setFinalHandValue(getThreeOfAKindValue(), i);
			}
			for(int i = 3; i < 5; i++)
			{
				setFinalHandValue(getPairValue(), i);
			}
			turnFinalHandToCards();
			
			// Set how good hand is
			setFinalHandScore(70000000 + (getFinalHandValue(0) * 100000) + (getFinalHandValue(3) * 1000));
		}
		
		// FLUSH NOT WORKING TODO
		  // Prints out the same card 5 times
		
		// 5. Flush
		else if(hasFlush())
		{
			int nextCardIndex = 0;
			boolean foundSuit = false;
			
			int[] usedIndexNumbers = new int[5];
			int currentUsedIndexNumber = 0;
			
			for(int i = 0; i < 5; i++)
			{
				// Useless number
				usedIndexNumbers[i] = 10;
			}
			
			for(int i = 0; i < 5; i++)
			{
				int nextCardSuit = 0;
				
				for(int j = 0; j < 7; j++)
				{
					if((j == usedIndexNumbers[0]) || (j == usedIndexNumbers[1]) || (j == usedIndexNumbers[2]) || (j == usedIndexNumbers[3]) || (j == usedIndexNumbers[4]))
					{
						continue;
					}
						else
						{
						switch (getCard(j).getSuit())
						{
							case SPADES:
								nextCardSuit = 1;
								break;
							case CLUBS:
								nextCardSuit = 2;
								break;
							case DIAMONDS:
								nextCardSuit = 3;
								break;
							case HEARTS:
								nextCardSuit = 4;
								break;
						}
						if(getFlushSuit() == nextCardSuit)
						{
							nextCardIndex = j;
							foundSuit = true;
						}
					}
				}
				if(foundSuit)
				{
					setFinalHandCard(getCard(nextCardIndex), i);
					usedIndexNumbers[currentUsedIndexNumber] = nextCardIndex;
					currentUsedIndexNumber++;
				}
			}
			// Set how good hand is
			setFinalHandScore(60000000 + (getFinalHandValue(4) * 100000));
		}
		
		// 6. Straight
		else if(hasStraight())
		{
			for(int i = 0; i < 5; i++)
			{
				setFinalHandValue(getSortedCardValue(getStraightIndex() + i), i);
			}
			// DEBUG
			System.out.println("FINAL HAND");
			for(int i = 0; i < 5; i++)
			{
				System.out.println(getFinalHandValue(i));
			}
			
		turnFinalHandToCards();
		
		// Set how good hand is
		setFinalHandScore(50000000 + (getFinalHandValue(4) * 100000));
		}
		
		// 7. Three of a kind
		else if(hasThreeOfAKind())
		{
			for(int i = 0; i < 3; i++)
			{
				setFinalHandValue(getThreeOfAKindValue(), i);
			}
			
			// Get kicker
			
			// First get helping hand ready
			for(int i = 0; i < 7; i++)
			{
				if(getSortedCardValue(i) != getThreeOfAKindValue())
				{
					setHelpingHandValue(getSortedCardValue(i), i);
				}
				else
				{
					setHelpingHandValue(0, i);
				}
			}
			
			// Find max of helping hands
			int max = 0;
			int lastIndex = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
					lastIndex = i;
				}
			}
			setHelpingHandValue(0, lastIndex); // Clear last max value
			setFinalHandValue(max, 3);
			
			max = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
					lastIndex = i;
				}
			}
			setFinalHandValue(max, 4);
		
			// DEBUG
			System.out.println("FINAL HAND");
			for(int i = 0; i < 5; i++)
			{
				System.out.println(getFinalHandValue(i));
			}
			
			turnFinalHandToCards();
			// Set how good hand is
			setFinalHandScore(40000000 + (getFinalHandValue(0) * 100000) + (getFinalHandValue(3) * 1000) + (getFinalHandValue(4) * 1));
		}
		
		// 8. Two Pairs
		// TODO Fix 2 pair kicker
		else if(hasPair() && hasSecondPair())
		{
			for(int i = 0; i < 2; i++)
			{
				// setFinalHandValue(getThreeOfAKindValue(), i);
				setFinalHandValue(getPairValue(), i);
			}
			for(int i = 2; i < 4; i++)
			{
				setFinalHandValue(getSecondPairValue(), i);
			}
			
			// Get kicker
			
			// First get helping hand ready
			for(int i = 0; i < 7; i++)
			{
				if((getSortedCardValue(i) != getPairValue()) && (getSortedCardValue(i) != getSecondPairValue()))
				{
					setHelpingHandValue(getSortedCardValue(i), i);
				}
				else
				{
					setHelpingHandValue(0, i);
				}
			}
			
			// Find max of helping hands
			int max = 0;
			int lastIndex = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
					lastIndex = i;
				}
			}
			setHelpingHandValue(0, lastIndex); // Clear last max value
			setFinalHandValue(max, 4);
			
			turnFinalHandToCards();
			
			// Set how good hand is (First pair is larger)
			setFinalHandScore(30000000 + (getFinalHandValue(0) * 100000) + (getFinalHandValue(2) * 1000) + (getFinalHandValue(4) * 1));
		}
		
		// 9. Pair
	    else if(hasPair())
		{
			for(int i = 0; i < 2; i++)
			{
				setFinalHandValue(getPairValue(), i);
			}
			
			// Get kickers (x3)
			
			// First get helping hand ready
			for(int i = 0; i < 7; i++)
			{
				if(getSortedCardValue(i) != getPairValue())
				{
					setHelpingHandValue(getSortedCardValue(i), i);
				}
				else
				{
					setHelpingHandValue(0, i);
				}
			}
			
			// Find max of helping hands
			int max = 0;
			int lastIndex = 0;
			int secondLastIndex = 0;
			int thirdLastIndex = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
					lastIndex = i;
				}
			}
			setHelpingHandValue(0, lastIndex); // Clear last max value
			setFinalHandValue(max, 2);
			
			max = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
					secondLastIndex = i;
				}
			}
			setHelpingHandValue(0, secondLastIndex); // Clear last max value
			setFinalHandValue(max, 3);
			
			max = 0;
			for(int i = 0; i < 7; i++)
			{
				if(max < getHelpingHandValue(i))
				{
					max = getHelpingHandValue(i);
					thirdLastIndex = i;
				}
			}
			setFinalHandValue(max, 4);
		
			turnFinalHandToCards();
			
			// Set how good hand is 200million then GET EVERY KICKER
			setFinalHandScore(20000000 + (getFinalHandValue(0) * 100000) + (getFinalHandValue(2) * 1000) + (getFinalHandValue(3) * 10));
		}
		
		// 10. High Card
		else
		{
			for(int i = 0; i < 5; i++)
			{
				setFinalHandValue(getSortedCardValue(6 - i), i);
			}
			
			turnFinalHandToCards();
			
			// Set how good hand is
			// If less than 200mil
			// DON'T KNOW LAST KICKER BUT NUMBER IS TOO BIG AAAAAGH
			setFinalHandScore((getFinalHandValue(4) * 1000000) + (getFinalHandValue(3) * 10000) + (getFinalHandValue(2) * 100) + (getFinalHandValue(1) * 1));
		}
	}
	
	// ================================================================
	// LOGIC
	
	// Check for flush
	public void checkForFlush() {
		boolean hasFlush = false;
		int numberOfSpades = 0;
		int numberOfClubs = 0;
		int numberOfDiamonds = 0;
		int numberOfHearts = 0;
		for(int i = 0; i < 7; i++)
		{
			switch (getCard(i).getSuit())
			{
				case SPADES:
					numberOfSpades++;
					break;
				case CLUBS:
					numberOfClubs++;
					break;
				case DIAMONDS:
					numberOfDiamonds++;
					break;
				case HEARTS:
					numberOfHearts++;
					break;
			}
		}
		if((numberOfSpades >= 5))
		{
			setFlush(true);
			setFlushSuit(1);
		}
		else if(numberOfClubs >= 5)
		{
			setFlush(true);
			setFlushSuit(2);
		}
		else if(numberOfDiamonds >= 5)
		{
			setFlush(true);
			setFlushSuit(3);
		}
		else if(numberOfHearts >= 5)
		{
			setFlush(true);
			setFlushSuit(4);
		}
		else
		{
			setFlush(false);
		}
	}
	
	// Returns last pair number
	public void checkForPair() {
		boolean hasPair = false;
		int pairValue = 0;
		int pairIndex = 0;
		for(int i = 0; i < (7-1); i++)
		{
			if(getSortedCardValue(i) == getSortedCardValue(i + 1))
			{
				hasPair = true;
				pairValue = getSortedCardValue(i);
				pairIndex = i;
			}
		}
		if(hasPair == true)
		{
		 // System.out.println("Debug>> Has pair!");
			setPair(true);
			// Set index
			setPairIndex(pairIndex);
		}
		else
		{
		 //	System.out.println("Debug>> Does not have pair!");
			setPair(false);
		}
		
		// Default pair value of 0
		setPairValue(pairValue);
		checkForSecondPair();
	}

	public void checkForThreeOfAKind() {
		boolean hasThreeOfAKind = false;
		int threeOfAKindValue = 0;
		int threeOfAKindIndex = 0;
		for(int i = 0; i < (7-2); i++)
		{
			if((getSortedCardValue(i) == getSortedCardValue(i + 1)) && (getSortedCardValue(i) == getSortedCardValue(i + 2)))
			{
				hasThreeOfAKind = true;
				threeOfAKindValue = getSortedCardValue(i);
				threeOfAKindIndex = i;
			}
		}
		if(hasThreeOfAKind == true)
		{
		 // System.out.println("Debug>> Has pair!");
			setThreeOfAKind(true);
			// Set index
			setThreeOfAKindIndex(threeOfAKindIndex);
		}
		else
		{
		 //	System.out.println("Debug>> Does not have pair!");
			setThreeOfAKind(false);
		}
		
		// Default pair value of 0
		setThreeOfAKindValue(threeOfAKindValue);
		checkForSecondPair();
	}
	
	public void checkForSecondPair() {
		boolean hasSecondPair = false;
		int secondPairValue = 0;
		int secondPairIndex = 0;
		// for(int i = 0; i < (7-1); i++)
		
		if(hasThreeOfAKind())
		{
			for(int i = 0; i < (7 - 2); i++)
			{
				if((i != getThreeOfAKindIndex()) && (i != (getThreeOfAKindIndex() + 1)) && (i != (getThreeOfAKindIndex() + 2)))
				{
					if(getSortedCardValue(i) == getSortedCardValue(i + 1))
					{
						//HERE NEXT
						hasSecondPair = true;
						secondPairValue = getSortedCardValue(i);
						secondPairIndex = i;
					}
				}
			}
			if(hasSecondPair)
			{
			 // System.out.println("Debug>> Has pair!");
				setSecondPair(true);
			 // setSecondPairValue(secondPairValue);
				
				// Set index
				setSecondPairIndex(secondPairIndex);
			 // System.out.println("Second Pair Index: " + secondPairIndex);
			}
			else
			{
			 //	System.out.println("Debug>> Does not have pair!");
				setSecondPair(false);
			}
		}
		else if(hasPair())
		{
			for(int i = 0; i < (7 - 1); i++)
			{
				if((i != getPairIndex()) && (i != (getPairIndex() + 1)))
				{
					if(getSortedCardValue(i) == getSortedCardValue(i + 1))
					{
						//HERE NEXT
						hasSecondPair = true;
						secondPairValue = getSortedCardValue(i);
						secondPairIndex = i;
					}
				}
			}
			if(hasSecondPair)
			{
			 // System.out.println("Debug>> Has pair!");
				setSecondPair(true);
				setSecondPairValue(secondPairValue);
				// Set index
				setSecondPairIndex(secondPairIndex);
			 // System.out.println("Second Pair Index: " + secondPairIndex);
			 // System.out.println("Second Pair Value: " + getSecondPairValue());
			}
			else
			{
			 //	System.out.println("Debug>> Does not have pair!");
				setSecondPair(false);
			}
		}
		else
		// Default pair value of 0
		setSecondPairValue(pairValue);
	}
	
	public void checkForFourOfAKind() {
		boolean hasFourOfAKind = false;
		int fourOfAKindValue = 0;
		for(int i = 0; i < (7-3); i++)
		{
			if((getSortedCardValue(i) == getSortedCardValue(i + 1)) && (getSortedCardValue(i) == getSortedCardValue(i + 2)) && (getSortedCardValue(i) == getSortedCardValue(i + 3)))
			{
				hasFourOfAKind = true;
				fourOfAKindValue = getSortedCardValue(i);
			}
		}
		if(hasFourOfAKind == true)
		{
		 // System.out.println("Debug>> Has pair!");
			setFourOfAKind(true);
		}
		else
		{
		 //	System.out.println("Debug>> Does not have pair!");
			setFourOfAKind(false);
		}
		
		// Default pair value of 0
		setFourOfAKindValue(fourOfAKindValue);
	}
	
	public void checkForStraight() {
		boolean hasStraight = false;
		int straightStartingValue = 0;
		int striaghtIndex = 0;
		for(int i = 0; i < (7-4); i++)
		{
			if((getSortedCardValue(i) == (getSortedCardValue(i + 1) - 1)) &&
			(getSortedCardValue(i) == (getSortedCardValue(i + 2) - 2)) && 
			(getSortedCardValue(i) == (getSortedCardValue(i + 3) - 3)) &&
			(getSortedCardValue(i) == (getSortedCardValue(i + 4) - 4)))
			{
				hasStraight = true;
				straightStartingValue = getSortedCardValue(i);
				straightIndex = i;
			}
		}
		if(hasStraight == true)
		{
		 // System.out.println("Debug>> Has pair!");
			setStraight(true);
			setStraightIndex(straightIndex);
		}
		else
		{
		 //	System.out.println("Debug>> Does not have pair!");
			setStraight(false);
		}
		
		// Default pair value of 0
		setStraightStartingValue(straightStartingValue);
	}
	// ================================================================

	void convertCardsToValues() {
		for(int i = 0; i < 7; i++)
		{
			switch(getCard(i).getValue())
			{
				// { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };
				case TWO:
					setCardValue(2, i);
					break;
				case THREE:
					setCardValue(3, i);
					break;
				case FOUR:
					setCardValue(4, i);
					break;
				case FIVE:
					setCardValue(5, i);
					break;
				case SIX:
					setCardValue(6, i);
					break;
				case SEVEN:
					setCardValue(7, i);
					break;
				case EIGHT:
					setCardValue(8, i);
					break;
				case NINE:
					setCardValue(9, i);
					break;
				case TEN:
					setCardValue(10, i);
					break;
				case JACK:
					setCardValue(11, i);
					break;
				case QUEEN:
					setCardValue(12, i);
					break;
				case KING:
					setCardValue(13, i);
					break;
				case ACE:
					setCardValue(14, i);
					break;
			}
		}
	}
	
	void sortCardValues() {
		int[] sortedArray = new int[7];
		for(int i = 0; i < 7; i++)
		{
			sortedArray[i] = getCardValue(i);
		}
		
		Arrays.sort(sortedArray);
		for(int i = 0; i < 7; i++)
		{
			setSortedCardValue(sortedArray[i], i);
		}
	}
	

	// Debug Function
	void printCards() {
		
		System.out.println("Cards in Calculator.");
		
		for(int i = 0; i < 7; i++) {
			getCard(i).printCard();
			System.out.println();
		}
	}
	
	void printCardValues() {
		for(int i = 0; i < 7; i++) {
			System.out.println(getCardValue(i));
			System.out.println();
		}
	}
	
	void printSortedCardValues() {
		for(int i = 0; i < 7; i++) {
			System.out.println(getSortedCardValue(i));
			System.out.println();
		}
	}
	
	public void turnFinalHandToCards() {
		
		int nextAddedCardIndex = 0;
		int[] ignoredCardsIndex = new int[5];
		
		for(int i = 0; i < 5; i++)
		{
			// Number that will never be found
			ignoredCardsIndex[i] = 10;
		}
		
		int ignoredCardsIndexCounter = 0;
		
		boolean valueFound = false;
		
		// Find each card in finalHandCards
		for(int i = 0; i < 5; i++)
		{
			// Debug
		 // System.out.println("Debug>> This should print 5 times.");
			
			// Compare against each card on board
			for(int j = 0; j < 7; j++)
			{
				if((j != ignoredCardsIndex[0]) && (j != ignoredCardsIndex[1]) && (j != ignoredCardsIndex[2]) && (j != ignoredCardsIndex[3]) && (j != ignoredCardsIndex[4]))
				{
				 // System.out.println("Debug>> How many times does this print?.");
					int currentCardValue = 0;
			
					switch (getCard(j).getValue()) 
					{
					// { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };
					case TWO:
						currentCardValue = 2;
						break;
					case THREE:
						currentCardValue = 3;
						break;
					case FOUR:
						currentCardValue = 4;
						break;
					case FIVE:
						currentCardValue = 5;
						break;
					case SIX:
						currentCardValue = 6;
						break;
					case SEVEN:
						currentCardValue = 7;
						break;
					case EIGHT:
						currentCardValue = 8;
						break;
					case NINE:
						currentCardValue = 9;
						break;
					case TEN:
						currentCardValue = 10;
						break;
					case JACK:
						currentCardValue = 11;
						break;
					case QUEEN:
						currentCardValue = 12;
						break;
					case KING:
						currentCardValue = 13;
						break;
					case ACE:
						currentCardValue = 14;
						break;
					default:
						// For removed cards
						currentCardValue = 0;
					}

					if(getFinalHandValue(i) == currentCardValue)
					{
					 // System.out.println("Debug>> Here");
						nextAddedCardIndex = j;
						valueFound = true;
					}
				}
				else
				{
				 // System.out.println("Skip card that's not needed");
				}
			}
			if(valueFound)
			{
				setFinalHandCard(getCard(nextAddedCardIndex), i);

				ignoredCardsIndex[ignoredCardsIndexCounter] = nextAddedCardIndex;
				ignoredCardsIndexCounter++;
				valueFound = false;
			}
		}
	}


	// Variables [[
	private Card[] potentialCards = new Card[7];
	Card getCard(int index) { return potentialCards[index]; }
	void setPotentialCard(Card newCard, int index) { potentialCards[index] = newCard; }
	void setPotentialCards(Card[] newCardArray) { for( int i = 0; i < 7; i++ ) { setPotentialCard(newCardArray[i], i); } }

	private int[] cardValues = new int[7];
	int getCardValue(int index) { return cardValues[index]; }
	void setCardValue(int newValue, int index) { cardValues[index] = newValue; }
	
	
	private int[] sortedCardValues = new int[7];
	int getSortedCardValue(int index) { return sortedCardValues[index]; }
	void setSortedCardValue(int newValue, int index) { sortedCardValues[index] = newValue; }
	
	
	private long finalHandScore = 0;
	long getFinalHandScore() { return finalHandScore; }
	void setFinalHandScore(long newFinalHandScore) { finalHandScore = newFinalHandScore; } 
	
	
		// Hand Info [[
	
		// Flush Info
		private boolean hasFlush = false;
		public boolean hasFlush() { return hasFlush; }
		public void setFlush(boolean bool) { hasFlush = bool; }
		
		// flushSuit 0 = null, 1 = Spades, 2 = Clubs, 3 = Diamonds, 4 = Hearts
		private int flushSuit = 0;
		public int getFlushSuit() { return flushSuit; }
		public void setFlushSuit(int newFlushSuit) { flushSuit = newFlushSuit; }
		
	
		// Pair info
		private boolean hasPair = false;
		public boolean hasPair() { return hasPair; }
		public void setPair(boolean bool) { hasPair = bool; }
		
		private int pairValue = 0;
		public int getPairValue() { return pairValue; }
		public void setPairValue(int newPairValue) { pairValue = newPairValue; }
		
		private int pairIndex = 0;
		public int getPairIndex() { return pairIndex; }
		public void setPairIndex(int newPairIndex) { pairIndex = newPairIndex; }
		
		private boolean hasSecondPair = false;
		public boolean hasSecondPair() { return hasSecondPair; }
		public void setSecondPair(boolean bool) { hasSecondPair = bool; }
		
		private int secondPairValue = 0;
		public int getSecondPairValue() { return secondPairValue; }
		public void setSecondPairValue(int newSecondPairValue) { secondPairValue = newSecondPairValue; }
		
		private int secondPairIndex = 0;
		public int getSecondPairIndex() { return secondPairIndex; }
		public void setSecondPairIndex(int newSecondPairIndex) { secondPairIndex = newSecondPairIndex; }
		
		// Three of a kind
		private boolean hasThreeOfAKind = false;
		public boolean hasThreeOfAKind() { return hasThreeOfAKind; }
		public void setThreeOfAKind(boolean bool) { hasThreeOfAKind = bool; }
		
		private int threeOfAKindValue = 0;
		public int getThreeOfAKindValue() { return threeOfAKindValue; }
		public void setThreeOfAKindValue(int newThreeOfAKindValue) { threeOfAKindValue = newThreeOfAKindValue; }
		
		private int threeOfAKindIndex = 0;
		public int getThreeOfAKindIndex() { return threeOfAKindIndex; }
		public void setThreeOfAKindIndex(int newThreeOfAKindIndex) { threeOfAKindIndex = newThreeOfAKindIndex; }
		
		// Four of a kind
		private boolean hasFourOfAKind = false;
		public boolean hasFourOfAKind() { return hasFourOfAKind; }
		public void setFourOfAKind(boolean bool) { hasFourOfAKind = bool; }
		
		private int fourOfAKindValue = 0;
		public int getFourOfAKindValue() { return fourOfAKindValue; }
		public void setFourOfAKindValue(int newFourOfAKindValue) { fourOfAKindValue = newFourOfAKindValue; }
		
		// Straight
		private boolean hasStraight = false;
		public boolean hasStraight() { return hasStraight; }
		public void setStraight(boolean bool) { hasStraight = bool; }
		
		private int straightStartingValue = 0;
		public int getStraightStartingValue() { return straightStartingValue; }
		public void setStraightStartingValue(int newStraightStartingValue) { straightStartingValue = newStraightStartingValue; }
		
		private int straightIndex = 0;
		public int getStraightIndex() { return straightIndex; }
		public void setStraightIndex(int newStraightIndex) { straightIndex = newStraightIndex; }
		
		
		// Helping Hand (FUNNY PUN HEE HEE)
		private int[] helpingHandValues = new int[7];
		public int getHelpingHandValue(int index) { return helpingHandValues[index]; }
		public void setHelpingHandValue(int newHelpingHandValue, int index) { helpingHandValues[index] = newHelpingHandValue; }
		
		// Final Hand
		private int[] finalHandValues = new int[5];
		public int getFinalHandValue(int index) { return finalHandValues[index]; }
		public void setFinalHandValue(int newFinalHandValue, int index) { finalHandValues[index] = newFinalHandValue; }
		
		// Final Card Hand
		private Card[] finalCardHand = new Card[5];
		public Card getFinalHandCard(int index) { return finalCardHand[index]; }
		public void setFinalHandCard(Card newFinalCard, int index) { finalCardHand[index] = newFinalCard; }
		
		// Hand Info ]]
		
	// Variables ]]
}