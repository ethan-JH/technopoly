package technopoly;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDie {

	int expected, actual;

	Die die;
	
	@Before
	public void setUp() throws Exception {
	}
	//Test valid RollDie
	@Test
	public void testRollDie() {
		
		Die die= new Die();
		actual=die.rollDie();
		
		assertTrue(actual<=6 && actual>=1);
		
	}
	//Test invalidRollDie
	@Test 
	public void testInvalidRollDie() {
		
		Die die= new Die();
		actual=die.rollDie();
		
		assertFalse(actual>=7 || actual<=0);
		
	}
}
