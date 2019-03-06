/**
 * 
 */
package technopoly;

/**
 * @author jmac
 *
 */
public class Company extends Square {
	private String squareOwnership;
	private int numberOfOffices;
	private int numberOfCampuses; 
	private boolean hasCampus; 
	
	public Company() {
		
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
	 */
	
	public Company(String name, int position, int value,  String field, String squareOwnership, int numberOfOffices, int numberOfCampuses, boolean hasCampus) {
		super(name, position, value, field);
		this.squareOwnership=squareOwnership;
		this.numberOfOffices=numberOfOffices;
		this.numberOfCampuses=numberOfCampuses;
		this.hasCampus=hasCampus;
	}

	public String getSquareOwnership() {
		return squareOwnership;
	}

	public void setSquareOwnership(String squareOwnership) {
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
	public void addSubscription(int numberOfOffices, int numberOfCampuses) {
		
	}

	@Override
	public void sendSquareDetails(Player player) {
		// TODO Auto-generated method stub
		
	}

}
