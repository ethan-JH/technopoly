/**
 * 
 */
package technopoly;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jmac
 *
 */
public abstract class Company extends Square {
	
	// instance vars
	private int squareOwnership;
	private int numberOfOffices;
	private int numberOfCampuses;
	private boolean hasCampus;
	
	/**
	 * default constructor
	 */
	public Company() {

	}

	/**
	 * constructor with args
	 * @param name
	 * @param position
	 * @param value
	 * @param field
	 * @param squareOwnership
	 * @param numberOfOffices
	 * @param numberOfCampuses
	 * @param hasCampus
	 */

	public Company(String name, int position, int value, String field, int squareOwnership, int numberOfOffices,
			int numberOfCampuses, boolean hasCampus) {
		super(name, position, value, field);
		this.squareOwnership = squareOwnership;
		this.numberOfOffices = numberOfOffices;
		this.numberOfCampuses = numberOfCampuses;
		this.hasCampus = hasCampus;
	}

	public int getSquareOwnership() {
		return squareOwnership;
	}

	public void setSquareOwnership(int squareOwnership) {
		this.squareOwnership = squareOwnership;
	}

	public int getNumberOfOffices() {
		return numberOfOffices;
	}

	public void setNumberOfOffices(int numberOfOffices) {
		this.numberOfOffices = numberOfOffices;
	}

	public int getNumberOfCampuses() {
		return numberOfCampuses;
	}

	public void setNumberOfCampuses(int numberOfCampuses) {
		this.numberOfCampuses = numberOfCampuses;
	}

	public boolean isHasCampus() {
		return hasCampus;
	}

	public void setHasCampus(boolean hasCampus) {
		this.hasCampus = hasCampus;
	}

	public abstract int addSubscription(int numberOfOffices, int numberOfCampuses);

	public abstract void sendSquareDetails(Player player, ArrayList<Player> playerList, Scanner scanner);

}
