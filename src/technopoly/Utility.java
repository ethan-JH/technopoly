/**
 * 
 */
package technopoly;

/**
 * @author Ethan Utility class which holds all the details for this square
 */
public class Utility extends Square {

	// instance vars
	private int squareOwnership;
	private int subscription;

	/**
	 * default constructor
	 */
	public Utility() {

	}

	/**
	 * constructor with args
	 * 
	 * @param name
	 * @param position
	 * @param value
	 * @param field
	 */
	public Utility(String name, int position, int value, String field, int squareOwnership, int subscription) {
		super(name, position, value, field);
		this.squareOwnership = squareOwnership;
		this.subscription = subscription;
	}


	/**
	 * @return the squareOwnership
	 */
	public int getSquareOwnership() {
		return squareOwnership;
	}

	/**
	 * @param squareOwnership the squareOwnership to set
	 */
	public void setSquareOwnership(int squareOwnership) {
		this.squareOwnership = squareOwnership;
	}

	/**
	 * @return the subscription
	 */
	public int getSubscription() {
		return subscription;
	}

	/**
	 * @param subscription the subscription to set
	 */
	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}

	/**
	 * sends the details of the utility square to the player
	 */
	@Override
	public void sendSquareDetails(Player player) {
		
		System.out.println(player.getName() + " has landed on a " + getName());
		
		if(getSquareOwnership() != 0 && getSquareOwnership() == player.getPlayerNumber()) {
			System.out.println("You already own this "+ getName() + ", no subscription required");
		} else if (getSquareOwnership() != 0 && getSquareOwnership() != player.getPlayerNumber()) {
			System.out.println("This " + getName() + " is owned by Player " +getSquareOwnership()+ ". Pay them a subscription of " + getSubscription() + " Techcoin");
		} else {
			System.out.println("This " + getName() + " is available for purchase for " + getValue() + " Techcoin");
		}

	}

	/**
	 * updates the square ownership number to the player number of the player who
	 * buys it
	 * 
	 * @param player
	 */
	public void updateSquareOwnership(Player player) {

		int newSquareOwnership = player.getPlayerNumber();
		setSquareOwnership(newSquareOwnership);

	}
	
	/**
	 * increments the number of utilities the player owns
	 * @param player
	 */
	public void updateUtilitiesOwned(Player player) {
		int numberUtilitiesOwned = player.getNumberOfUtilitiesOwned();
		
		if(numberUtilitiesOwned < 4) {
			player.setNumberOfUtilitiesOwned(++numberUtilitiesOwned);
		} else {
			System.out.println("You own all of the Data Centres and Techcoin Mines!");
		}
		
	}
	
	/**
	 * multiplies the subscription by the number of utilities the player owns and sets it
	 * @param player
	 */
	public void multiplySubscription(Player player) {
		
		int numberUtilitiesOwned = player.getNumberOfUtilitiesOwned();
		setSubscription(getSubscription() * numberUtilitiesOwned);
		
	}

}
