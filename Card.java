/*
THIS CODE WAS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS OR COPIED FROM ONLINE RESOURCES. Allison_Ng
*/

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;



public class Card {


	private static final String[] SUITS = {"Spades", "Clubs", "Hearts", "Diamonds"}; //ArrayList that holds names of all suits
	private static final String[] NAMES = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"}; //ArrayList that holds names of each card
	private String suit; //variable for the suit of the card
	private String name; //variable for the name (number) of the card
	private int value; //variable to hold the numeric value of the card
	
	// Default constructor with no argument
	// NOTE: You can overload the constructor but you must
	// have this default constructor and it must generate a valid card
    public Card() {
    	suit = "Spades";
    	name = "Queen";
    	value = 10;
    	//default constructor that creates a Queen of Spades, which has a value of 10
    }
    
    //Overloaded constructor which takes the suit, name, and value of a card 
    public Card (String suit, String cardName, int value) {
    	this.suit = suit;
    	name = cardName;
    	this.value = value;
    }
 

    // Override the method equals which is inherited from class Object
	// (similar to what we did in class Employee), and make it return true if the
	// two cards have the same suit and rank
	// Important: Use the @Override annotation
    @Override
    public boolean equals (Object obj) {
    	//checks of the object is an instance of the class Card
    	if (! (obj instanceof Card)) {
    		return false;
    	}
    	//casts the object as a Card if the object is indeed an instance of Card
    	Card card2 = (Card) obj;
    	
    	//checks if the name and suit of the two cards match, and returns false otherwise
    	if (name.equals (card2.name) && suit.equals (card2.suit) ) {
    		return true;
    	} else {
    		return false;
    	}
    }

	// Public static method that takes in an empty deck and constructs a
	// randomly shuffled standard 52-card deck. A standard 52-card deck
	// is comprised of 13 ranks in each of the four French suits:
	// clubs, diamonds, hearts, and spades. Each suit includes an Ace, a King,
	// a Queen, and a Jack with the numeric cards from two to ten.
	// Important: Two calls to the build deck should likely return 2 different shuffles.
    public static void buildDeck(ArrayList<Card> deck) {
    	//Nested for loops that add a card off every suit deck of every name (value) to the deck by running through the previously created SUITS and NAMES ArrayLists
    	for (int i = 0; i < SUITS.length; i++) {
    	
    		for (int j = 0; j <NAMES.length; j++) {
    			deck.add(new Card (SUITS[i], NAMES[j], Math.min(j+1, 10)));
    			//Math.min assigns each card their value, and prevents the royal cards (Jack, Queen, King) from having a value over 10
    		}
    	}
    	
    	//For loop that shuffles the deck by starting at the END of the deck, and selecting a random number (a). That random number then calls the swap method 
    	//in order to switch the value at index a with the value at the end of the deck. This continues all the way down the deck until the entire deck is shuffled
    	for (int end = deck.size() - 1; end >= 1; end--) {
    		Random randomizer = new Random();
    		int a = randomizer.nextInt (end + 1);
    		swap (a, end, deck);
    	}
    }
    

	// Method that takes a non-empty deck and deals 2 cards to the player's hand
	// and deals 2 cards to the dealer's hand. The deck at the end of
	// this method should have 4 less cards than when it started.
    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand) {
    	//for loop that takes two cards from the deck and adds it to the player's hand. It removes the added card from the deck before the next card is added
    	for (int i = 0; i < 2; i++) {
    		Card temp = deck.get(0); //temporary variable to hold the card taken from the deck
    		playerHand.add(temp);
    		deck.remove(0);
    		
    	}
    	
    	//for loop that takes two cards from the deck and adds it to the dealer's hand. It removes the added card from the deck before the next card is added
    	for (int i = 0; i < 2; i++) {
    		Card temp2 = deck.get(0); //temporary variable to hold the card taken from the deck
    		dealerHand.add(temp2);
    		deck.remove(0);
    	}
    }


	// Method that takes a non-empty deck and deals 1 card to the hand.
	// The deck at the end of this method should have
	// 1 less card than when it started.
    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand) {
    	Card temp = deck.get(0); //temporary variable to hold the card taken from the deck
    	hand.add(temp); //adds the card to the hand
    	deck.remove(0); //removes the card from the original deck
    }

    // Method that gets the total value of the hand. The Jack, Queen, and
    // King cards take on the value 10, while an Ace can be 1 or 11.
	// Thus, if the hand contains a 10 of Spades and a Jack of Hearts
	// it will return a 20. If the hand contains a 5 of Clubs and a
	// Queen of Spades, this method should return a 15.
    public static int getHandValue(ArrayList<Card> hand){
    	int numAces = 0; //counter variable to count the # of aces
    	int result = 0; //Variable to store the total value of the hand
    	//for loop that runs through the given hand to check for aces. If an ace is found, then the counter variable numAces is incremented
    	//if the card isn't an ace, then the value of the card is added to the result variable (which tracks the total value of the hand)
    	for (int i = 0; i < hand.size(); i++) {
    		if (hand.get(i).value == 1) {
    			numAces++;
    		} else {
    			result += hand.get(i).value;
    		}
    	}
    	//conditional statement that returns the result if there are no aces in the hand
    	if (numAces == 0) {
    		return result;
    	}
    	
    	//conditional statement for the case where the value of the hand is over 21, and there are aces in the hand. 
    	// This makes the value of the aces = 1, so it returns the value of the result + the # of aces
    	if ((result > 21) && (numAces > 0)) return result + numAces;
    	
    	
		if ((result + (11 + (numAces - 1)) > 21)) { return result + numAces;
		} else {
			//Accounts for all other cases. The above conditionals eliminate all other possibilities.
	    	//This also accounts for the switching value of the Ace, as we know the total result MUST be less than 21 (since we accounted for all other cases above 21).
			//Therefore, there can only ever be ONE ace in the hand counted with a value of 11. Any other ace after this is added to the result with the value of 1
			return result + (11 + (numAces - 1));
		}
    }

	// Method that checks whether the given hand's value exceeds 21.
    public static boolean checkBust(ArrayList<Card> hand){
    	 //conditional statement that returns false if the hand is less than or equal to 21, and true otherwise (a bust)
    	if (getHandValue(hand) <= 21) { 
    		return false;
    	} else {
    		return true;
    	}
    }

    // Method that conduct the dealer's turn. The return value should be
    // true if the dealer busts and false otherwise. For the dealer's turn,
    // your code should continue to hit (or get a card) if the hand is less than 17
    // otherwise stand if the hand is greater than or equal to 17.
    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
    	//conditional statement that checks whether or not the dealer has busted
    	if (checkBust (hand)) {
    		return true;
    	//second conditional statement that checks whether or not the dealer's hand has a value below 17.
    	} else if (getHandValue (hand) < 17) {
    		dealOne (deck, hand); //deals one card to the dealer hand (given that the dealer's hand is below 17)
    		if (checkBust (hand)) { //checks whether or n ot the dealer has busted after being dealt a card
    			return true;
    		}
    	}
    	return false; //returning false otherwise
    	
    }

    // Fill in the code that determines who wins. The return value should be
    // 1 if the player wins and 2 if the dealer wins. Winning is determined by who has
    // the closer value to 21 without busting (going over 21).
    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
    	//conditional statement that accounts for the case where neither the dealer nor the player busts, 
    	//AND the value of the player's hand is greater than the value of the dealer's hand
    	if (!checkBust (playerHand) && !checkBust (dealerHand) && (getHandValue (playerHand) > getHandValue (dealerHand))) {
    		return 1;
    	//conditional statement where the dealer busts and the player does not bust
    	} else if (checkBust (dealerHand) && !checkBust (playerHand)) {
    		return 1;
    	} else {
    		//since there are only TWO cases where the player can win, all other cases are the dealer's win (including ties, etc)
    		return 2;
    	}
    }

    // Method that describes the card (value and suit) located at index 1
    // in the hand. This is used to show one of the cards that the dealer has.
    public static String displayCard(ArrayList<Card> hand){
    	//returns a string of the name and suit of the card, which is retrieved from the hand using .get(1)
    	return hand.get(1).name + " of " + hand.get(1).suit;
    }

    // Method that describes the cards (values and suits) in the hand.
    public static String displayHand(ArrayList<Card> hand){
    	String result = ""; //Variable to store result string containing the names of all the cards
 
    	//for loop that runs through the entire hand, and adds the name & suit of each card (the entire name of the card) to the result variable
    	for  (int i = 0; i < hand.size(); i++) {
    		result += hand.get(i).name + " of " + hand.get(i).suit + "\n";
    	}
    	
    	return result; //returns result
    }

    // Method that clears both the player hand and dealer hand.
    // There should be no cards in either hand after this method is called.
    public static void clearHands(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
    	//uses .clear() of the ArrayList methods to empty both the player hand and the dealer hand
    	playerHand.clear();
    	dealerHand.clear();
    }

    //Swap method which takes two ints to use as indexes in order to shuffle the deck by switching values in the ArrayList 
	public static void swap (int a, int b, ArrayList<Card> deck) {
		Card c = deck.get(a);
		deck.set (a, deck.get(b));
		deck.set (b, c);
	}

    // Do not change anything after this!
    public static void main(String[] args) {

		int playerChoice, winner;
		ArrayList<Card> deck = new ArrayList<Card> ();

		playerChoice = JOptionPane.showConfirmDialog(null, "Ready to Play Blackjack?", "Blackjack", JOptionPane.OK_CANCEL_OPTION);

		if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
		    System.exit(0);


		Object[] options = {"Hit", "Stand"};
		boolean isBusted, dealerBusted;
		boolean isPlayerTurn;
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();

		do{ // Game loop
			// Clear the hands
			clearHands(playerHand, dealerHand);
			// Clear the deck and build if not enough cards left to play a new game
			if (deck.size() <= 12) {
				deck.clear();
				buildDeck(deck);
			}

		    initialDeal(deck, playerHand, dealerHand);
		    isPlayerTurn=true;
		    isBusted=false;
		    dealerBusted=false;

		    while (isPlayerTurn){
		    	System.out.println("Player hand value is:" + String.valueOf(getHandValue(playerHand)));

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(null, "Dealer shows " + displayCard(dealerHand) + "\nYour hand is: " + displayHand(playerHand) + "\nWhat do you want to do?","Hit or Stand",
									   JOptionPane.YES_NO_OPTION,
									   JOptionPane.QUESTION_MESSAGE,
									   null,
									   options,
									   options[0]);

				// Player chooses to close the game
				if (playerChoice == JOptionPane.CLOSED_OPTION)
				    System.exit(0);

				// Player chooses to Hit
				else if(playerChoice == JOptionPane.YES_OPTION){
				    dealOne(deck, playerHand);
				    System.out.println("Player hand value after hitting is:" + String.valueOf(getHandValue(playerHand)));
				    isBusted = checkBust(playerHand);
				    if(isBusted){
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(null,"Your hand: " +  displayHand(playerHand) + "\nPlayer has busted!", "You lose", JOptionPane.OK_CANCEL_OPTION);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);

						isPlayerTurn = false;
				    }
				}
			    // Player chooses to Stand
				else{
				    isPlayerTurn = false;
				}
		    }
		    System.out.println("Dealer hand value is:" + String.valueOf(getHandValue(dealerHand)));

		    if (!isBusted) { // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				System.out.println("Dealer hand value after turn is:" + String.valueOf(getHandValue(dealerHand)));

				if(dealerBusted){ // Case: Dealer Busts
				    playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " + displayHand(dealerHand) + "\n \nYour hand: " + displayHand(playerHand) + "\nThe dealer busted.\nCongratulations!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);

					if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				}


				else{ //The Dealer did not bust.  The winner must be determined
				    winner = whoWins(playerHand, dealerHand);

				    if (winner == 1){ //Player Wins
						playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " + displayHand(dealerHand) + "\n \nYour hand: " + displayHand(playerHand) + "\nCongratulations!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }

				    else{ //Player Loses
						playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \nYour hand: " + displayHand(playerHand) + "\nBetter luck next time!", "You lose!!!", JOptionPane.OK_CANCEL_OPTION);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }
				}
		    }
		} while(true);
    }
}
