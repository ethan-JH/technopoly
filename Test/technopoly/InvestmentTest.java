package technopoly;

import static org.junit.Assert.*;



import org.junit.BeforeClass;
import org.junit.Test;

public class InvestmentTest {
	String name = "name";
	int position = 1;
	int value = 0;
	String field = "field";
	Investment invest = new Investment();
	Investment investment = new Investment(name, position, value, field);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSendSquareDetails() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testGODefaultConstructor() {
		assertNotNull(invest);
	}
	@Test
	public void testGOConstructorWithArgs() {
		assertNotNull(investment);
	}

}
