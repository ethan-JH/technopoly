package technopoly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class UtilityTest {
	String name = "name";
	int position = 1;
	int value = 0;
	String field = "field";
	int squareOwnership = 0;
	int subscription = 10;
	String playerName  = "name";
	int playerNumber = 1;
	int numberOfUtilitiesOwned =1;
	
	Utility u = new Utility();
	Utility utility = new Utility(name, position, value, field, squareOwnership, subscription);
	Player player = new Player(position, name, playerNumber);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSendSquareDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilityDefaultConstructor() {
		assertNotNull(u);
	}

	@Test
	public void testUtilityConstructorWithArgs() {
		assertNotNull(utility);
	}

	@Test
	public void testGetterSetterSquareOwnership() {
		int expected = 1; 
		utility.setSquareOwnership(expected);
		int actual = utility.getSquareOwnership();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetterSetterSubscription() {
		int expected = 1;
		utility.setSubscription(expected);
		int actual = utility.getSubscription();
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateSquareOwnership() {
		int expected = playerNumber;
		utility.updateSquareOwnership(player);
		int actual = utility.getSquareOwnership();
		assertEquals(expected, actual);
	}

	@Test
	public void testUpdateUtilitiesOwned() {
		player.setNumberOfUtilitiesOwned(0);
		utility.updateUtilitiesOwned(player);
		int expected = numberOfUtilitiesOwned;
		int actual = player.getNumberOfUtilitiesOwned();
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testMultiplySubscription() {
		player.setNumberOfUtilitiesOwned(2);
		int expected = numberOfUtilitiesOwned*subscription;
		int actual = utility.getSubscription();
		assertEquals(expected ,actual);
	}

}
