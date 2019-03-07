/**
 * 
 */
package technopoly;

/**
 * @author jmac
 *
 */
public class StreamingService extends Company {
	
	private int officeCost;
	private int campusCost;
	private int subscription; 
	
	

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
	public StreamingService(String name, int position, int value, String field, int squareOwnership,
			int numberOfOffices, int numberOfCampuses, boolean hasCampus, int officeCost, int campusCost, int subscription) {
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
	public void sendSquareDetails(Player player) {
		System.out.println(player.getName() + "has landed on "+ getName()+" it is a "+ getField()+ " company and costs "+getValue());
		
		if (getSquareOwnership()==player.getPlayerNumber()) {	//check if player already owns square
			System.out.println("You already own " + getName());
		}else if (getSquareOwnership()!=0 && getSquareOwnership()!=player.getPlayerNumber()) {	// check if other player owns square
			System.out.println(getName() + "is owned by Player "+ getSquareOwnership()+". There are "+ getNumberOfOffices()+" Offices and "+getNumberOfCampuses()+" Campuses. Pay the owner a subscription of "+ getSubscription() + " Techcoin.");
		} else if (getSquareOwnership()==0 && player.getResource()>=getValue()) { //if square is unowned check if player has enough to buy square
			System.out.println("This is an unowned square. Would you like to buy "+ getName()+ " for "+ getValue()+"? You have "+ player.getResource()+ " Techcoin.");	
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
			setSubscription(subscription+=(numberOfOffices*25));
		} else if(numberOfOffices>=1 && numberOfCampuses>=1) { // subscription fee for having offices and hotels
			setSubscription(subscription+=(numberOfOffices*25) + (numberOfCampuses*125));
		}else { //fee for campuses - offices not included in this fee as when a campus is built offices are removed
			setSubscription(subscription+=(numberOfCampuses*125));	
			}
		return subscription;
	}
	

		/**
		 * 
		 * when called increments number of Streaming owned by 1, checks if all Streaming is owned
		 */
		public void updateStreamingOwned(Player player) {
				int numberOfStreamingOwned = player.getNumberOfStreamingServiceOwned();
				player.setNumberOfStreamingServiceOwned(++numberOfStreamingOwned); // adds 1 Streaming when called
				if (numberOfStreamingOwned==2) {
					System.out.println("You own all of the "+ getField() +" companies. You can now begin developing offices and campuses.");
				}
			
		}
		
	}
	
