package technopoly;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HolidayTest {
	String name = "name";
	int position = 1;
	int value = 0;
	String field = "field";
	Holiday h = new Holiday();
	Holiday hol = new Holiday(name, position, value, field);
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSendSquareDetails() {
		fail("Not yet implemented");
	}

	@Test
	public void testHolidayDefaultConstructor() {
		assertNotNull(h);
	}

	@Test
	public void testHolidayConstructorWithArgs() {
		assertNotNull(hol);
	}

}
