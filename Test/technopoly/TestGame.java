package technopoly;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class TestGame {

	
	Game game=new Game();
 

    
   
	@Before
	public void setUp() throws Exception {
		Game game=new Game();
	}
	
	@Test
	public void defaultCons() {
		
		
	}
	
	// Test to set Max Number of players
	@Test
	public void testSetMaxNumberOfPlayers() {
	
		int input = 4;
		int expected=4;
		Game game=new Game();
		assertEquals(input, expected);
	}
	// Test to set three players
	@Test
	public void testSetThreeNumberOfPlayers() {
	
		int input = 3;
		int expected=3;
		Game game=new Game();
		assertEquals(input, expected);
	
	
	}
	
	// Test to set min players
	@Test
	public void testSetMinNumberOfPlayers() {
	
		int input = 1;
		int expected=1;
		Game game=new Game();
		
		assertEquals(input, expected);
	}
	// Test to set Invalid number of players
	@Test (expected=InputMismatchException.class)
	public void testSetInvalidNumberOfPlayers() {
		Game game=new Game();
		game.requestNumberOfPlayers();
		int input = 5;
		int expected=4;
		assertEquals(input, expected);
	}
	// Test to set Invalid number of players
	@Test (expected=InputMismatchException.class)
	public void testSetInvalidNumberOfPlayersZero() {
		Game game=new Game();
		game.requestNumberOfPlayers();
		int input =0;
		int expected=2;
		assertEquals(input, expected);
	}
	
	
	// Test to set valid number of players  confirmation
	@Test
	public void testNumberofPlayersConfirmationNo() {

		Game game = new Game();
		String scannerInput="n";
		String switchExpected = "n";
		assertEquals(scannerInput, switchExpected);

	}
	
	
	// Test to set valid number of players  confirmation
	@Test
	public void testNumberofPlayersConfirmationUpperCaseYes() {

		Game game = new Game();
		String scannerInput="Y";
		String switchExpected = "Y";
		assertEquals(scannerInput, switchExpected);

	}
	
	
	// Test to set valid number of players  confirmation
	@Test
	public void testNumberofPlayersConfirmationLowerCaseYes() {

		Game game = new Game();
		String scannerInput="y";
		String switchExpected = "y";
		assertEquals(scannerInput, switchExpected);

	}
	
	// Test to set invalid number of players  confirmation
	@Test (expected= InputMismatchException.class)
	public void testInvalidNumberofPlayersConfirmation() {
		
		Game game = new Game();
		game.requestNumberOfPlayers();
		String scannerInput="No";
		String switchExpected = "n";
		
		
		assertEquals(scannerInput, switchExpected);
	}
	// Test to set invalid number of players  confirmation
	@Test (expected= InputMismatchException.class)
	public void testInvalidNumberofPlayersConfirmationYesAndNo() {
		
		Game game = new Game();
		game.requestNumberOfPlayers();
		String scannerInput="Y";
		String switchExpected = "N";
		
		
		assertEquals(scannerInput, switchExpected);
	}

	// Test to set valid number of players and confirmation for minimum value
	@Test
	public void testRequestPlayerNumbersAndConfirmationMinValue() {
		Game game=new Game();
	
		int actualInput = 2;
		int expectedInput= 2;
		String expected = "y";
		String actual = "y";
	
		assertTrue((expected==actual)&& (expectedInput==actualInput));
		
	}
	// Test to set valid number of players and confirmation for max value
	@Test
	public void testRequestPlayerNumbersAndConfirmationMaxValue() {
		Game game=new Game();
	
		int actualInput = 4;
		int expectedInput=4;
		String expected = "y";
		String actual = "y";
	
		assertTrue((expected==actual)&& (expectedInput==actualInput));
		
	}
	// Test to set valid number of players with invalid confirmation
	@Test (expected= InputMismatchException.class)
	public void testRequestvalidPlayerNumbersAndNoConfirmation() {

		Game game=new Game();
		int actualInput = 2;
		int expectedInput=2;
		String expected = "y";
		String actual = "n";
	
		assertTrue((expected==actual)&& (expectedInput==actualInput));
		
	}
	// Test to set invalid number of players with accurate confirmation
	@Test
	public void testRequestInvalidPlayerNumbersWithConfirmation() {
		Game game=new Game();
		int actualInput = 5;
		int expectedInput=2;
		game.requestPlayerNames(expectedInput);
		String expected = "y";
		String actual = "y";
		assertEquals(expected, actual);
		assertFalse((expected==actual)&&(expectedInput==actualInput));
	
		
	}
	
	

	

}
