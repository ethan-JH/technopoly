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
public class SocialMedia extends Company {
	
	// instance vars
	private int officeCost;
	private int campusCost;
	private int subscription;
	
	/**
	 * default constructor
	 */
	public SocialMedia() {
		
	}
	
	/**
	 * 
	 * @param name
	 * @param position
	 * @param value
	 * @param field
	 * @param squareOwnership
	 * @param numberOfCampuses
	 * @param hasCampus
	 * @param officeCost 
	 * @param campusCost 
	 * @param subscription 
	 */
	public SocialMedia(String name, int position, int value, String field, int squareOwnership, int numberOfOffices,
			int numberOfCampuses, boolean hasCampus, int officeCost, int campusCost, int subscription) {
		super(name, position, value, field, squareOwnership, numberOfOffices, numberOfCampuses, hasCampus);
		this.officeCost = officeCost;
		this.campusCost = campusCost;
		this.subscription = subscription;
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
	public void sendSquareDetails(Player player, ArrayList<Player> playerList, Scanner scanner) {
		System.out.println(player.getName() + " has landed on " + getName() + " it is a " + getField()
				+ " company and costs " + getValue());

		if (getSquareOwnership() == player.getPlayerNumber()) { // check if player already owns square
			System.out.println("You already own " + this.getName() + ", no subscription required");
		} else if (getSquareOwnership() != 0 && getSquareOwnership() != player.getPlayerNumber()) { 
			// checks if other player owns square
			for (Player owner : playerList) {
				if (getSquareOwnership() == owner.getPlayerNumber()) {
					System.out.println(getName() + " is owned by " + owner.getName() + ". There are "
							+ getNumberOfOffices() + " Offices and " + getNumberOfCampuses()
							+ " Campuses. Pay the owner a subscription of " + getSubscription() + " Techcoin.");
					updateResource(-getSubscription(), player);
					updateResource(getSubscription(), owner);

					System.out.println(player.getName() + " now has " + player.getResource() + " Techcoin. "
							+ owner.getName() + " now has " + owner.getResource() + " Techcoin.");
				}
			}
		} else if (getSquareOwnership() == 0 && player.getResource() >= getValue()) { 
			// if square is unowned check if player has enough to buy square
			buyCompany(player, scanner);
		} else { // player does not have enough to buy square
			System.out.println("Sorry you don't have enough to buy this square, you only have " + player.getResource()
					+ " Techcoin.");
		}
	}
	
	/**
	 * adds to the subscription fee based on number of offices/campuses
	 *  multiplier is based on value of property 
	 *  @return subscription
	 */
	public int addSubscription(int numberOfOffices, int numberOfCampuses) {
		int subscription = getSubscription();
		
		if(numberOfOffices <=3) {		// subscription fee for up to 3 houses
			setSubscription(subscription+=(numberOfOffices*75));
		} else if(numberOfOffices>=1 && numberOfCampuses>=1) { // subscription fee for having offices and hotels
			setSubscription(subscription+=(numberOfOffices*75) + (numberOfCampuses*375));
		} else { //fee for campuses - offices not included in this fee as when a campus is built offices are removed
			setSubscription(subscription+=(numberOfCampuses*375));	
		}
		return subscription;
	}
	
	/**
	 * 
	 * when called increments number of social media owned by 1, checks if all social media is owned
	 */
	public void updateSocialOwned(Player player) {
			int numberOfSocialMediaOwned = player.getNumberOfSocialMediaOwned();
			player.setNumberOfSocialMediaOwned(++numberOfSocialMediaOwned); // adds 1 social media when called
			if (numberOfSocialMediaOwned==3) {
				System.out.println("You own all of the "+ getField() +" companies. You can now begin developing offices and campuses.");
			} else {
				System.out.println("You own "+player.getNumberOfSocialMediaOwned()+"/3 "+ getField() +" companies.");
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
		Board board = new Board();
		ArrayList<Square> newProperty = new ArrayList<>();
		ArrayList<Company> newCompany = new ArrayList<>();

		do {
			System.out.println(getName() + " is available for purchase for " + getValue() + " Techcoin");
			System.out.println("Would you like to purchase " + getName() + "? (Y/N)");
			try {
				confirm = scanner.next();
				if (confirm.equalsIgnoreCase("y")) {

					updateResource(-getValue(), player);
					setSquareOwnership(player.getPlayerNumber());
					updateSocialOwned(player);
					
					//add to player owned properties array
					for(Square square: board.getSquares()){
						if(square.getName().equals(this.getName())){
							newProperty = player.getOwnedSquares();
							newProperty.add(square);
							player.setOwnedSquares(newProperty);
						}
					}
					
					// add to player owned companies array
					for(Company company : board.getCompanies()) {
						if(company.getName().equals(this.getName())){
							newCompany = player.getOwnedCompanies();
							newCompany.add(company);
							player.setOwnedCompanies(newCompany);
						}
					}
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
