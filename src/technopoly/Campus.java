/**
 * 
 */
package technopoly;

/**
 * @author hugoharbinson
 *
 */
public class Campus {

	/**
	 * default constructor
	 */
	public Campus() {
		// TODO Auto-generated constructor stub
	}

	public static void updateNumberOfCampuses(Company company) {
		if (!company.isHasCampus()) {
			int numberOfCampuses = company.getNumberOfCampuses();
			numberOfCampuses++;
			company.setNumberOfCampuses(numberOfCampuses);
		} else {
			System.out.println("You've already built a campus here. Cangratulations it's fully developed");
		}
	}

}
