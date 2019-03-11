/**
 * 
 */
package technopoly;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author jmac
 *
 */
public class TechGiant extends Company {

	
	private int officeCost;
	private int campusCost;
	private int subscription; 

	public TechGiant(){

	}
	/**
	 * @param name
	 * @param position
	 * @param value
	 * @param field
	 * @param squareOwnership
	 * @param numberOfOffices
	 * @param numberOfCampuses
	 * @param hasCampus
	 * @param officeCost 
	 * @param campusCost 
	 * @param subscription 
	 */
	public TechGiant(String name, int position, int value, String field, int squareOwnership, int numberOfOffices,
			int numberOfCampuses, boolean hasCampus, int officeCost, int campusCost, int subscription) {
		super(name, position, value, field, squareOwnership, numberOfOffices, numberOfCampuses, hasCampus);
		this.officeCost=officeCost;
		this.campusCost=campusCost;
		this.subscription=subscription;
		 
	}

	public int getOfficeCost() {
		return officeCost;
	}

	public void setOfficeCost(int officeCost) {
		this.officeCost = officeCost;
	}

	public int getCampusCost() {
		return campusCost;
	}

	public void setCampusCost(int campusCost) {
		this.campusCost = campusCost;
	}

	public int getSubscription() {
		return subscription;
	}

	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}
	
	/**
	 * sends details to player
	 */
	public void sendSquareDetails(Player player,ArrayList<Player> playerList, Scanner scanner) {
		System.out.println(player.getName() + " has landed on "+ getName()+" it is a "+ getField()+ " company and costs "+getValue());
		
		if (getSquareOwnership()==player.getPlayerNumber()) {	//check if player already owns square
			System.out.println("You already own " + this.getName()+ ", no subscription required");
		}else if (getSquareOwnership()!=0 && getSquareOwnership()!=player.getPlayerNumber()) {	// check if other player owns square
			System.out.println(getName() + " is owned by Player "+ getSquareOwnership()+". There are "+ getNumberOfOffices()+" Offices and "+getNumberOfCampuses()+" Campuses. Pay the owner a subscription of "+ getSubscription() + " Techcoin.");
			
			for (Player owner : playerList) {
				if (getSquareOwnership() == owner.getPlayerNumber()) {
					updateResource(-getSubscription(), player);
					updateResource(getSubscription(), owner);

					System.out.println(player.getName() + " now has " + player.getResource() + " Techcoin. "
							+ owner.getName() + " now has " + owner.getResource() + " Techcoin.");
				}
			}
		} else if (getSquareOwnership()==0 && player.getResource()>=getValue()) { // check if player has enough to buy square
			buyCompany(player, scanner);
		} else { // player does not have enough to buy square
			System.out.println("Sorry you don't have enough to buy this square, you only have "+player.getResource()+ " Techcoin.");
		}
	}
	
	/**
	 * adds to the subscription fee based on number of offices/campuses
	 * multiplier is based on value of property
	 * @return subscription
	 */
	public int addSubscription(int numberOfOffices, int numberOfCampuses) {
		int subscription = getSubscription();
		
		if(numberOfOffices <=3) {		// subscription fee for up to 3 houses
			setSubscription(subscription+=(numberOfOffices*100));
		}		
		else if(numberOfOffices>=1 && numberOfCampuses>=1) { // subscription fee for having offices and hotels
			setSubscription(subscription+=(numberOfOffices*100) + (numberOfCampuses*500));
				
		} else { //fee for campuses - offices not included in this fee as when a campus is build offices are removed
			setSubscription(subscription+=(numberOfCampuses*500));	
			}
		return subscription;
	}
	
	/**
	 * 
	 * when called increments number of techgiant owned by 1, checks if all techgiants are owned
	 */
	public void updateTechGiantOwned(Player player) {
		int numberOfTechGiantOwned = player.getNumberOfTechGiantOwned();
		player.setNumberOfTechGiantOwned(++numberOfTechGiantOwned); // adds 1 techgiant when called
		if (numberOfTechGiantOwned==2) {
			System.out.println("You own all of the "+ getField() +" companies. You can now begin developing offices and campuses.");
		} else {
			System.out.println("You own "+player.getNumberOfTechGiantOwned()+"/2 "+ getField() +" companies.");
		}
	}
	
	/**
	 * method to allow the player to buy a company
	 * 
	 * @param player
	 */
	public void buyCompany(Player player, Scanner scanner) {
		
		String confirm;
		boolean doneBuyCompany = false;

		do {
			System.out.println(getName() + " is available for purchase for " + getValue() + " Techcoin");
			System.out.println("Would you like to purchase " + getName() + "? (Y/N)");
			try {
				confirm = scanner.next();
				if (confirm.equalsIgnoreCase("y")) {

					updateResource(-getValue(), player);
					setSquareOwnership(player.getPlayerNumber());
					updateTechGiantOwned(player);
					System.out.println(getName() + " is now owned by " + player.getName() + ". You now have "
							+ player.getResource() + " Techcoin");
					System.out.println();
					doneBuyCompany = true;

				} else if (confirm.equalsIgnoreCase("n")) {
					
					System.out.println("Ok. " + getName() + " is still available for purchase");
					doneBuyCompany = true;
					
				} else {
					System.out.println("You must enter either Y or N for yes or no.");
				}

			} catch (InputMismatchException e) {
				System.out.println("Invalid input, enter Y or N for yes or no.");
			}
		} while (!doneBuyCompany);
		
	}

}
