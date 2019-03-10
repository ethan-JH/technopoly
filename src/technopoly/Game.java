/**
 * contains Technopoly game classes
 */
package technopoly;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**Game engine for Technopoly
 * @author Luke
 *
 */
public class Game {
	Scanner scanner = new Scanner(System.in);
	private int numberOfPlayers;
	private boolean correctNumberOfPlayers = false;
	private boolean confirmed = false;
	private ArrayList<Player> playerList = new ArrayList<>();
	private Player currentPlayer;
	private Board board = new Board();
	private String currentSquare;


	/**
	 * starts the game
	 */
	public void startGame() {
		requestNumberOfPlayers();
	}

	/**
	 * requests the number of players, loops until this is confirmed
	 */
	public void requestNumberOfPlayers() {
		do {
		System.out.println("How many people are playing? Choose 2, 3 or 4!");
			try {
				numberOfPlayers = scanner.nextInt();
				if(numberOfPlayers >= 2 && numberOfPlayers <= 4) {
					System.out.println("Are you sure you would like to play with " + numberOfPlayers + " players? Y/N");
					confirmNumberPlayers();
				} else {
					System.out.println("Sorry, you can only choose between 2 and 4 players!");
				}

			} catch (InputMismatchException e) {
				System.out.println(
						"Oops, that doesn't seem right. Please type 2, 3 or 4 to select the number of players and press return!");
				scanner.next();
			}
		} while (!correctNumberOfPlayers);
		scanner.close();
	}

	public void confirmNumberPlayers() {
		String confirm;
		do {
			try {
				confirm = scanner.next();

				switch (confirm) {
				case "Y":
					correctNumberOfPlayers = true;
					confirmed = true;
					requestPlayerNames(numberOfPlayers);
					break;
				case "y":
					correctNumberOfPlayers = true;
					confirmed = true;
					requestPlayerNames(numberOfPlayers);
					break;
				case "N":
					requestNumberOfPlayers();
					break;
				case "n":
					requestNumberOfPlayers();
					break;
				default:
					System.out.println(
							"Sorry, that's not a valid response! Type Y for yes or N for no and press return.");

					break;

				}

			} catch (InputMismatchException e) {
				System.out.println(
						"Oops, that doesn't seem right. Please type Y for yes or N for no and press return!");
			}
		} while (!confirmed);
	}

	/**
	 * requests player names, loops until they are all confirmed
	 * 
	 * @param numberOfPlayers (the number of players confirmed in confirmNumberPlayers)
	 */
	public void requestPlayerNames(int numberOfPlayers) {
		String name = null;
		String confirm;
		boolean nameConfirmed;
		boolean nameInputCorrect;
		for (int i = 0; i < numberOfPlayers; i++) {
			nameConfirmed = false;
			nameInputCorrect = false;
			System.out.println("Player " + (i + 1) + " please enter your first name!");
			do {
				try {
					name = scanner.next();

					try {
						do {
							System.out.println("Your name is " + name + ", is that right? (Y/N)");
							confirm = scanner.next();
							switch (confirm) {
							case "Y":
							case "y":
								nameInputCorrect = true;
								nameConfirmed = true;
								break;
							case "N":
							case "n":
								System.out.println("Alright, so what's your real name?");
								nameInputCorrect = true;
								break;
							default:
								System.out.println(
										"Sorry, that's not a valid response! Type Y for yes or N for no and press return.");
								break;
							}

						} while (!nameInputCorrect);
					} catch (InputMismatchException e) {
						System.out.println(
								"Oops, that doesn't seem right. Please type Y for yes or N for no and press return!");
					}
				} catch (InputMismatchException e) {
					System.out.println("Sorry, please input your name (it cannot be a number!)");
					scanner.next();
				}
				playerList.add(new Player(1, name, (i + 1)));
			} while (!nameConfirmed);

		}
		setUp();
	}

	public void setUp() {
		// set player 1 to go first
		setFirstPlayer();
		// print out a welcome message to all the players
		welcomePlayers();
		//generate the board
		board.generateBoard();
		//for testing purposes, remove
		currentPlayer.getOwnedCompanies().add((Company)board.getSquares().get(1));
		//display turn options to first player
		displayTurnOptions();
	}

	public void welcomePlayers() {
		System.out.print("Welcome, ");
		for (int i = 0; i < playerList.size(); i++) {
			if (i < (playerList.size() - 1)) {
				System.out.print(playerList.get(i).getName() + ", ");
			} else {
				System.out.print("and" + playerList.get(i).getName());
			}
		}
		System.out.print("...to Technopoly!! \n\n");
	}

	public void setFirstPlayer() {
		for (Player player : playerList) {
			if (player.getPlayerNumber() == 1) {
				currentPlayer = player;
			}
		}
	}


	/**
	 * displays the current player's turn options at the beginning of their turn.
	 * Loops until they end their turn or roll the dice.
	 */
	public void displayTurnOptions() {
		boolean done = false;
		int response;

		do {
			System.out.println("Hello " + currentPlayer.getName() + " , please choose an option:");
			System.out.println("1. Roll dice");
			System.out.println("2. Sell property");
			System.out.println("3. Grow business");
			System.out.println("4. View resources");
			System.out.println("5. Display board");
			System.out.println("6. Forfeit game");

			try {
				response = scanner.nextInt();
				switch (response) {
				case 1:
					rollDice();
					break;
				case 2:
					sellBusiness();
					break;
				case 3:
					growBusiness();
					break;
				case 4:
					currentPlayer.displayResources();
					break;
				case 5:
					board.displayBoard();
					break;
				case 6:
					endGame();
					break;
				default:
					System.out.println("Sorry, " + response
							+ " does not match a menu item! Please type a number between 1 and 8 (inclusive)!");
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println(
						"Oops, that doesn't seem right! Please input the number of the menu item you would like to select and press return.");
				scanner.next();
			}
		} while (!done);
	}

	public void rollDice() {
		Die d1 = new Die();
		Die d2 = new Die();
		Square destinationSquare = null;
		int movement = (d1.rollDie() + d2.rollDie());
		updatePlayerPosition(movement, currentPlayer);
		for (Square square : board.getSquares()) {
			if (square.getPosition() == currentPlayer.getPosition()) {
				currentSquare = square.getName();
				destinationSquare = square;
			}
		}
		System.out.println("You rolled a " + movement + ", you have landed on " + currentSquare);
		destinationSquare.sendSquareDetails(currentPlayer);

		advanceCurrentPlayer();


	}

	public void advanceCurrentPlayer() {
		if(currentPlayer.getPlayerNumber() == playerList.size()){
			currentPlayer = playerList.get(0);
		} else{
			for(Player player: playerList){
				if(player.getPlayerNumber() == (currentPlayer.getPlayerNumber() + 1)){
					currentPlayer = player;
				}
			}
		}
	}

	/**
	 * allows player to sell owned businesses
	 */
	private void sellBusiness() {
		boolean doneSellBusiness = false;

		String businessSelect;
		currentPlayer.displayOwnedSquares();
		if (currentPlayer.getOwnedCompanies().size() > 0) {

			System.out.println("Type the name of the property you would like to sell, or type 'back' to go back.");
			do {
				try {
					businessSelect = scanner.next();
					// checks player owns the business they've named
					for (int i = 0; i < currentPlayer.getOwnedCompanies().size(); i++) {
						if (currentPlayer.getOwnedCompanies().get(i).getName().equalsIgnoreCase(businessSelect)) {
							doneSellBusiness = confirmSellBusiness(doneSellBusiness, i);
							// allows player to go back if they type 'back'
						} else if (businessSelect.equalsIgnoreCase("back")) {
							doneSellBusiness = true;
						} else {
							System.out.println(
									"Sorry, that wasn't a valid input. Please type the name of a property you would like to sell and press return, or type 'back' to go back.");

						}
					}

				} catch (InputMismatchException e) {
					System.out.println(
							"Sorry, that's not a property you own! Try again, or alternatively type 'back' to go back.");
				}
			} while (!doneSellBusiness);

		}
	}

	/**
	 * is called within sell business, further logic to confirm the player is done
	 * selling
	 * 
	 * @param doneSellProperty
	 * @param i
	 * @return
	 */
	private boolean confirmSellBusiness(boolean doneSellProperty, int i) {
		boolean confirmSellBusiness = false;
		String confirm;
		do {
			System.out.println("Are you sure you would like to sell " + currentPlayer.getOwnedCompanies().get(i).getName()
					+ "(Y/N)?");
			try {
				confirm = scanner.next();
				switch (confirm) {
				case "Y":
				case "y":
					Company company = null;
					for (int j = 0; j < board.getSquares().size(); j++) {

						if (currentPlayer.getOwnedCompanies().get(i).getName()
								.equals(board.getSquares().get(j).getName())) {
							company = (Company)board.getSquares().get(j);
						}
					}
					if (currentPlayer.getOwnedCompanies().get(i).getName().equals(company.getName())) {
						currentPlayer.setResource(currentPlayer.getResource() + company.getValue());
						company.setHasCampus(false);
						System.out.println("You have sold " + company.getName() + " for " + company.getValue()
								+ " and now have " + currentPlayer.getResource());
						currentPlayer.getOwnedCompanies().remove(i);
						System.out.println("Would you like to sell another business? (Y/N)");
						try {
							confirm = scanner.next();
							switch (confirm) {
							case "Y":
							case "y":
								if (currentPlayer.getOwnedCompanies().size() == 0) {
									System.out.println("Looks like you don't have any businesses left to sell!");
									doneSellProperty = true;
									break;
								}
								System.out.println(
										"Type the name of the business you would like to sell, or type 'back' to go back.");
								confirmSellBusiness = true;
								break;
							case "N":
							case "n":
								doneSellProperty = true;
								confirmSellBusiness = true;
								break;
							default:
								System.out
										.println("Sorry, that's not a valid input! Please type Y for yes or N for no.");
							}
						} catch (InputMismatchException e) {
							System.out.println("Sorry, that's not a valid input! Please type Y for yes or N for no.");
						}

					}

					break;
				case "N":
				case "n":
					System.out.println("Alright, let's go back!");
					System.out.println(
							"Type the name of the business you would like to sell, or type 'back' to go back.");
					confirmSellBusiness = true;
					break;
				default:
					System.out.println("Sorry, that wasn't a valid input. Please type Y for yes or N for no.");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Sorry, that wasn't a valid input. Please type Y for yes or N for no.");
			}
		} while (!confirmSellBusiness);
		return doneSellProperty;
	}

	/**
	 * is called when someone goes below zero (paying rent/tax etc. on squares); displays list of final resources for players and names a winner
	 */
	private void endGame(Player currentPlayer) {
		String winner = currentPlayer.getName();
		int highestResources = currentPlayer.getTotalResources();

		System.out.println(currentPlayer.getName() + " has run out of money; game over!");
		playerList.sort(Comparator.comparing(Player::getResource));
		for (Player player : playerList) {
			System.out.println(player.getName() + ": " + player.getTotalResources());
			if (player.getTotalResources() > highestResources){
				winner = player.getName();
				highestResources = player.getTotalResources();
			}
		}
		System.out.println("The winner is " + winner + ". Congratulations!");
	}

	/**
	 * updates the player position after they move
	 * 
	 * @param movement (equates to dice roll in rollDice)
	 * @param player (this is the current player)
	 */
	public void updatePlayerPosition(int movement, Player player) {
		if ((player.getPosition() + movement) <= 20) {
			player.setPosition(player.getPosition() + movement);
		} else {
			player.setPosition((player.getPosition() + movement) - 20);
		}
	}

	/**
	 * ends the game if someone chooses the option to forfeit
	 */
	public void endGame() {
		String response;
		boolean done = false;
		do {
			try {
				System.out.println("Are you sure you would like to end the game? (Y/N)");
				response = scanner.next();
				if (response.equals("Y") || response.equals("y")) {
					System.out.println(currentPlayer.getName() + " has forfeited the game - game over!");
					// results?
					done = true;

				} else if (response.equals("N") || response.equals("n")) {
					System.out.println("Let's go back to the menu, then!");
					System.out.println();
					displayTurnOptions();
				} else {
					System.out.println("Sorry, that's not a valid response! Please type Y for yes or N for no!");
				}
			} catch (InputMismatchException e) {
				System.out.println("Sorry, didn't catch that! Type Y for yes or N for No");
				scanner.next();
			}
		} while (!done);
	}

	/**
	 * allows player to build offices or campuses
	 */
	public void growBusiness() {
		boolean doneGrowBusiness = false;
		boolean confirmed = false;
		String business;
		String confirm;
		String officeCampus = null;
		String developAnother;
		String field;
		int numberOwned = 0;
		int numberInField = 0;
		if (currentPlayer.getOwnedCompanies().size() > 0) {
			do {
				currentPlayer.displayOwnedSquares();
				System.out.println(
						"Type the name of the business you would like to grow and press return, or type 'back' to go back!");
				try {
					business = scanner.next();
					for (Company company : currentPlayer.getOwnedCompanies()) {
						if (business.equalsIgnoreCase(company.getName())) {
							System.out.println("Are you sure you would like to grow " + company.getName() + "?(Y/N)");
							field = company.getField();
							for (Square ownedSquare : currentPlayer.getOwnedCompanies()) {
								// numberOwned keeps track of the number of businesses in a field the player
								// owns
								if (ownedSquare.getField().equals(company.getField())) {
									numberOwned += 1;
								}
							}
							// finds how many squares there are on the board within the field of the
							// business selected by the user
							for (Square allSquares : board.getSquares()) {
								if (allSquares.getField().equals(field)) {
									numberInField += 1;
								}
							}
							// compares number of businesses in field owned by player and number on the
							// board; only allows player to build offices/campuses if they own all the
							// businesses in a field
							if (numberOwned == numberInField) {
								try {
									confirm = scanner.next();
									switch (confirm) {
									case "Y":
									case "y":
										do {
											System.out.println("Would you like to build an office or a campus?");
											try {
												officeCampus = scanner.next();
											} catch (InputMismatchException e) {
												System.out.println(
														"Sorry, that's not a valid response. Please type 'office' to build an office or 'campus' to build a campus and press return.");
											}
											switch (officeCampus) {
											case "Office":
											case "office":
												// cost to build 100, may change this?
												if (currentPlayer.getResource() >= 100) {
													// prevents user from building more than four offices for a business
													if (company.getNumberOfOffices() < 4) {
														company.setNumberOfOffices(company.getNumberOfOffices() + 1);
														currentPlayer.setResource(currentPlayer.getResource() - 100);
														System.out.println("OK, an office has now been built for "
																+ company.getName()
																+ ". Would you like to grow another business?(Y/N)");
														try {
															developAnother = scanner.next();
															switch (developAnother) {
															case "Y":
															case "y":
																confirmed = true;
																break;
															case "N":
															case "n":
																confirmed = true;
																doneGrowBusiness = true;
																break;
															default:
																System.out.println(
																		"Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
																break;
															}
														} catch (InputMismatchException e) {
															System.out.println(
																	"Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
														}
													} else {
														System.out.println(
																"Sorry, you cannot have more than four offices for a business!");
														break;
													}
												} else {
													System.out.println(
															"Sorry, you have insufficient resources to build an office!");
													break;
												}

												break;
											case "Campus":
											case "campus":
												// cost to build 300, could change this?
												if (currentPlayer.getResource() >= 300) {
													if (!company.isHasCampus()) {
														// must have four offices to place a campus
														if (company.getNumberOfOffices() == 4) {
															currentPlayer
																	.setResource(currentPlayer.getResource() - 300);
															company.setHasCampus(true);
															System.out.println("OK, a campus has now been built for "
																	+ company.getName() + ". You now have "
																	+ currentPlayer.getResource()
																	+ " resources. Would you like to develop another property?(Y/N)");
															try {
																developAnother = scanner.next();
																switch (developAnother) {
																case "Y":
																case "y":
																	confirmed = true;
																	break;
																case "N":
																case "n":
																	confirmed = true;
																	doneGrowBusiness = true;
																	break;
																default:
																	System.out.println(
																			"Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
																	break;
																}
															} catch (InputMismatchException e) {
																System.out.println(
																		"Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
															}
															break;
														} else {
															System.out.println(
																	"Sorry, you need to build four offices for a business before you can build a campus!");
															break;
														}
													} else {
														System.out.println(
																"Sorry, you can only build one campus per field!");
														break;
													}
												} else {
													System.out.println(
															"Sorry, you have insufficient resource to build a campus!");
													break;
												}
											default:
												System.out.println(
														"Sorry, that's not a valid response. Please type 'office' to build an office or 'campus' to build a campus and press return.");
												break;
											}
										} while (!confirmed);
										break;
									case "N":
									case "n":
										break;
									default:
										System.out.println(
												"Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
										break;
									}
								} catch (InputMismatchException e) {
									System.out.println(
											"Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
								}
							} else {
								System.out.println(
										"Sorry, you need to own all the businesses in a field to develop it! Let's go back.");
								doneGrowBusiness = true;
							}

						} else if (business.equalsIgnoreCase("back")) {
							System.out.println("OK, let's go back!");
							doneGrowBusiness = true;
						} else {
							System.out.println(
									"Sorry, that's not a business you own! Please type the name of the business you wish to grow and press return.");
						}
					}

				} catch (InputMismatchException e) {
					System.out.println(
							"Sorry, that's not a business you own! Please type the name of the business you wish to grow and press return.");
				}

			} while (!doneGrowBusiness);
		} else {
			System.out.println("It appears that you don't have any businesses to develop!");
		}
	}
}
