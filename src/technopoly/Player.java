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
	private ArrayList<Company> ownedCompanies = new ArrayList<>();
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
		if (ownedCompanies.size() > 0) {
			System.out.println("You currently own the following fields: ");
			for (Company company : ownedCompanies) {
				System.out.println(" " + company.getName());
			}
		} else {
			System.out.println("You currently own zero fields.");
		}
	}

	public int getTotalResources(){
		int total = 0;
		for(Company company: ownedCompanies){
			total += company.getValue();
		}
		total += resource;
		return resource;
	}
	

	public ArrayList<Company> getOwnedCompanies() {
		return ownedCompanies;
	}

	public void setOwnedCompanies(ArrayList<Company> ownedCompanies) {
		this.ownedCompanies = ownedCompanies;
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
	
	/**
	 * displays all of the resources owned by the player
	 *
	 */
	public void displayResources() {
		
		System.out.println("You currently have " + getResource() + " Techcoins");
		displayOwnedSquares();
		
		if(getNumberOfStreamingServiceOwned() < 2) {
			System.out.println("You own " + getNumberOfStreamingServiceOwned() + " out of the 2 Streaming Companies");
		} else if (getNumberOfStreamingServiceOwned() == 2){
			System.out.println("You own both the Streaming Companies and can now develop Offices and Campuses there!");
		}
		
		if(getNumberOfRetailOwned() < 3) {
			System.out.println("You own " + getNumberOfRetailOwned() + " out of the 3 Retail Companies");
		} else if (getNumberOfRetailOwned() == 3){
			System.out.println("You own all the Retail Companies and can now develop Offices and Campuses there!");
		}
		
		if(getNumberOfSocialMediaOwned() < 3) {
			System.out.println("You own " + getNumberOfSocialMediaOwned() + " out of the 3 Social Media Companies");
		} else if (getNumberOfRetailOwned() == 3){
			System.out.println("You own all the Social Media Companies and can now develop Offices and Campuses there!");
		}
		
		if(getNumberOfTechGiantOwned() < 2) {
			System.out.println("You own " + getNumberOfTechGiantOwned() + " out of the 2 Tech Giants");
		} else if (getNumberOfTechGiantOwned() == 2){
			System.out.println("You own all the Tech Giants and can now develop Offices and Campuses there!");
		}
		
		if(getNumberOfUtilitiesOwned() < 4) {
			System.out.println("You own " + getNumberOfUtilitiesOwned() + " out of the 4 Data Centres and Techcoin Mines");
		} else if (getNumberOfUtilitiesOwned() == 4){
			System.out.println("You own all of the Data Centres and Techcoin Mines!");
		}
		
		System.out.println();
	}
	
}
