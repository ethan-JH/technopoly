package technopoly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ChanceTest {
	String name = "name";
	int playerNumber = 1;
	int position = 1;
	int value = 0;
	String field = "field";
	String chanceResponse = "chanceResponse";
	
	Chance c = new Chance();
	Chance chance = new Chance(name, position, value, field, chanceResponse);
	Player player = new Player(position, name, playerNumber);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	public void testChanceDefaultConstructor() {
		assertNotNull(c);
	}
	
	@Test 
	public void testChanceConstructorWithArgs() {
		assertNotNull(chance);
		
	}
	@Test
	public void testSendSquareDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetterSetterChanceResponse() {
		String expected = chanceResponse;
		chance.setChanceResponse(expected);
		String actual = chance.getChanceResponse();
		assertEquals(expected, actual);
	}

	/**
	 * Testing switch statement that switches based on RNG. Each case sets the chance response. Think this is fine for now as not sure how else you would test a switch statement that switches based on RNG
	 */
	@Test
	public void testGenerateChanceResponseCase0() {
		chance.generateChanceResponse(player);
		assertNotNull(chance.getChanceResponse());
		
	}
	


}
