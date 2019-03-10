package technopoly;

import static org.junit.Assert.*;



import org.junit.BeforeClass;
import org.junit.Test;

public class GOTest {
	String name = "name";
	int position = 1;
	int value = 0;
	String field = "field";
	GO g = new GO();
	GO go = new GO(name, position, value, field);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSendSquareDetails() {
		fail("Not yet implemented");
		
	}

	@Test
	public void testGODefaultConstructor() {
		assertNotNull(g);
	}
	@Test
	public void testGOConstructorWithArgs() {
		assertNotNull(go);
	}

}
