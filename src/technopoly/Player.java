/**
 * 
 */
package technopoly;

import java.util.ArrayList;

/**
 * @author Luke
 *
 */
public class Player {
	private ArrayList<Square> ownedSquares = new ArrayList<Square>();
	private int position;
	private String name;
	private int playerNumber;
	private int resource;
	private int numberOfUtilitiesOwned;
	private int numberOfTechGiantOwned;
	private int numberOfSocialMediaOwned;
	private int numberOfRetailOwned;
	private int numberOfStreamingServiceOwned;

	public Player(int position, String name, int playerNumber) {
		this.position = position;
		this.name = name;
		this.playerNumber = playerNumber;
	}

	public void displayOwnedSquares() {
		if (ownedSquares.size() > 0) {
			System.out.println("You currently own the following fields: ");
			for (Square square : ownedSquares) {
				System.out.println(" " + square.getName());
			}
		} else {
			System.out.println("You currently own zero fields.");
		}
	}
	

	public ArrayList<Square> getOwnedSquares() {
		return ownedSquares;
	}

	public void setOwnedSquares(ArrayList<Square> ownedSquares) {
		this.ownedSquares = ownedSquares;
	}

	public void setResource(int resource) {
		this.resource = resource;
	}

	public int getResource() {
		return this.resource;
	}
	

	public int getPosition() {
		return position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * @return the numberOfUtilitiesOwned
	 */
	public int getNumberOfUtilitiesOwned() {
		return numberOfUtilitiesOwned;
	}

	/**
	 * @param numberOfUtilitiesOwned the numberOfUtilitiesOwned to set
	 */
	public void setNumberOfUtilitiesOwned(int numberOfUtilitiesOwned) {
		this.numberOfUtilitiesOwned = numberOfUtilitiesOwned;
	}

	
	
	public int getNumberOfTechGiantOwned() {
		return numberOfTechGiantOwned;
	}

	public void setNumberOfTechGiantOwned(int numberOfTechGiantOwned) {
		this.numberOfTechGiantOwned = numberOfTechGiantOwned;
	}
	

	public int getNumberOfSocialMediaOwned() {
		return numberOfSocialMediaOwned;
	}

	public void setNumberOfSocialMediaOwned(int numberOfSocialMediaOwned) {
		this.numberOfSocialMediaOwned = numberOfSocialMediaOwned;
	}

	public int getNumberOfRetailOwned() {
		return numberOfRetailOwned;
	}

	public void setNumberOfRetailOwned(int numberOfRetailOwned) {
		this.numberOfRetailOwned = numberOfRetailOwned;
	}

	public int getNumberOfStreamingServiceOwned() {
		return numberOfStreamingServiceOwned;
	}

	public void setNumberOfStreamingServiceOwned(int numberOfStreamingServiceOwned) {
		this.numberOfStreamingServiceOwned = numberOfStreamingServiceOwned;
	}

	public void updatePosition(int rollNumber) {

	}
	
}
