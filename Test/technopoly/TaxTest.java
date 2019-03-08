package technopoly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TaxTest {
	String name = "name";
	int position = 1;
	int value = 0;
	String field = "field";
	Tax t = new Tax();
	Tax tax = new Tax(name, position, value, field);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSendSquareDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testTaxDefaultConstructor() {
		assertNotNull(t);
	}

	@Test
	public void testTaxConstructorWithArgs() {
		assertNotNull(tax);
	}

}
