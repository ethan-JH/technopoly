package technopoly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class OfficeTest {
	Office o = new Office();
	String name = "name";
	int position = 1;
	int value = 1;
	String field = "field";
	int squareOwnership = 1;
	int numberOfCampuses = 0;
	int numberOfOffices = 4;
	int fourOffices = 4;
	int officeCost = 1;
	int campusCost = 1;
	int subscription = 1;
	
	TechGiant t = new TechGiant(name, position, value, field, squareOwnership, 0, numberOfCampuses, false, officeCost, campusCost, subscription);
	SocialMedia s = new SocialMedia(name, position, value, field, squareOwnership, fourOffices, numberOfCampuses, false, officeCost, campusCost, subscription);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testOfficeDefaultConstructor() {
		assertNotNull(o);
	}

	@Test
	public void testUpdateNumberOfOfficesWhenOfficeNumberIs0() {
		o.updateNumberOfOffices(t);
		int expected = numberOfOffices;
		int actual = t.getNumberOfOffices();
		assertEquals(expected, actual);
		
	}

	/**
	 * maximum number of offices is 4, so when called this method should not update
	 */
	@Test
	public void testUpdateNumberOfOfficesWhenOfficeNumberIs4() {
		o.updateNumberOfOffices(s);
		int expected = fourOffices;
		int actual = s.getNumberOfOffices();
		assertEquals(expected, actual);
	}
}
