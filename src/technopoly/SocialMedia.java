/**
 * 
 */
package technopoly;

/**
 * @author jmac
 *
 */
public class SocialMedia extends Company {
	private int officeCost;
	private int campusCost;
	private int subscription;
	
	public SocialMedia() {
		
	}
	
	/**
	 * 
	 * @param name
	 * @param position
	 * @param value
	 * @param field
	 * @param squareOwnership
	 * @param numberOfHouses
	 * @param numberOfCampuses
	 * @param hasCampus
	 * @param officeCost 
	 * @param campusCost 
	 * @param subscription 
	 */

	public SocialMedia(String name, int position, int value, String field, String squareOwnership, int numberOfHouses,
			int numberOfCampuses, boolean hasCampus, int officeCost, int campusCost, int subscription) {
		super(name, position, value, field, squareOwnership, numberOfHouses, numberOfCampuses, hasCampus);
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
	public void updateSocialOwned() {
		
	}

}
