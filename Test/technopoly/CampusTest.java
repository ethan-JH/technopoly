package technopoly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class CampusTest {

	Campus c = new Campus();
	String name = "name";
	int position = 1;
	int value = 1;
	String field = "field";
	int squareOwnership = 1;
	int numberOfOffices = 1;
	int numberOfCampuses = 1;
	int officeCost = 1;
	int campusCost = 1;
	int subscription = 1;
	TechGiant t = new TechGiant(name, position, value, field, squareOwnership, numberOfOffices, 1, true, officeCost, campusCost, subscription);
	SocialMedia s = new SocialMedia(name, position, value, field, squareOwnership, numberOfOffices, 0, false, officeCost, campusCost, subscription);
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@Test
	public void testCampusDefaultConstructor() {
		assertNotNull(c);
	}
	
	@Test
	public void testIfCampusNumberDoesNotIncreaseIfCampusAlreadyBuilt() {
		int expected = numberOfCampuses;
		c.updateNumberOfCampuses(t);
		int actual = t.getNumberOfCampuses();
		assertEquals(expected, actual);
	}
	@Test
	public void testIfNumberOfCampusesIsIncreased() {
		c.updateNumberOfCampuses(s);
		int expected = numberOfCampuses;
		int actual = s.getNumberOfCampuses();
		assertEquals(expected, actual);
		
		
	}

}
