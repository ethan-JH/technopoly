package technopoly;

public class Retail extends Company {
	private int officeCost;
	private int campusCost;
	private int subscription;
	
	/**
	 * 
	 * @param name
	 * @param position
	 * @param value
	 * @param field
	 * @param squareOwnership
	 * @param numberOfOffices
	 * @param numberOfCampuses
	 * @param hasCampus
	 * @param campusCost
	 * @param officeCost
	 * @param subscription
	 */
	public Retail(String name, int position, int value, String field, String squareOwnership, int numberOfOffices,
			int numberOfCampuses, boolean hasCampus, int campusCost, int officeCost, int subscription) {
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
	public void updateRetailOwned() {
		
	}

}
