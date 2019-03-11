/**
 *
 */
package technopoly;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Luke
 * class which contains game details and methods and main method for playing game
 */
public class Game {
	
	// instantiate scanner
	Scanner scanner = new Scanner(System.in);
	
	// instance vars
	private int numberOfPlayers;
	private boolean correctNumberOfPlayers = false;
	private boolean confirmed = false;
	private ArrayList<Player> playerList = new ArrayList<Player>();
	private Player currentPlayer;
	private Board board;
	private Square currentSquare;
	
	// instantiate squares
	private GO fundingRound = new GO("Funding Round", 1, 200, "GO");
	private StreamingService netflix = new StreamingService("Netflix", 2, 60, "Streaming Service", 0, 0, 0, false, 40, 40, 12);
	private Utility mine1 = new Utility("TECHCOIN MINE", 3, 150, "Utility", 0, 30); 
	private StreamingService hulu = new StreamingService("Hulu", 4, 60, "Streaming Service", 0, 0, 0, false, 40, 40, 12);
	private Tax digitalTax = new Tax("Digital Tax", 5, 200, "Tax");
	private Chance chance1 = new Chance("Chance", 6, 0, "Chance", "Chance");
	private Retail ebay = new Retail("Ebay", 7, 140, "Retail", 0, 0, 0, false, 105, 105, 28);
	private Utility dataCentre1 = new Utility("DATA CENTRE", 8, 150, "DATA CENTRE", 0, 30);
	private Retail alibaba = new Retail("Alibaba", 9, 140, "Retail", 0, 0, 0, false, 105, 105, 28);
	private Retail amazon = new Retail("Amazon", 10, 160, "Retail", 0, 0, 0, false, 120, 120, 32);
	private Holiday holiday = new Holiday("Holiday", 11, 0, "Holiday");
	private SocialMedia twitter = new SocialMedia("Twitter", 12, 260, "Social Media", 0, 0, 0, false, 195, 195, 52);
	private SocialMedia instagram = new SocialMedia("Instagram", 13, 260, "Social Media", 0, 0, 0, false, 195, 195, 52);
	private Utility mine2 = new Utility("TECHCOIN MINE", 14, 150, "TECHCOIN MINE", 0, 30);
	private SocialMedia facebook = new SocialMedia("Facebook", 15, 280, "Social Media", 0, 0, 0, false, 210, 210, 56);
	private Chance chance2 = new Chance("Chance", 16, 0, "Chance", "Chance");
	private Tax hacked = new Tax("Hacked", 17, 100, "Hacked");
	private TechGiant apple = new TechGiant("Apple", 18, 350, "Tech Giant", 0, 0, 0, false, 265, 265, 70);
	private Utility dataCentre2 = new Utility("DATA CENTRE", 19, 150, "DATA CENTRE", 0, 30);
	private TechGiant microsoft = new TechGiant("Microsoft", 20, 400, "Tech Giant", 0, 0, 0, false, 300, 300, 80);
	
	/**
	 * default constructor
	 */
	public Game() {
		
	}
	
	public Game(Scanner scanner) {
		this.scanner = scanner;
	}
	
	// methods
	
	/**
	 * get the number of players
	 * @return
	 */
	public int getNumberOfPlayers() {
		return this.numberOfPlayers;
	}
	
	/**
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * @return the correctNumberOfPlayers
	 */
	public boolean isCorrectNumberOfPlayers() {
		return correctNumberOfPlayers;
	}

	/**
	 * @param correctNumberOfPlayers the correctNumberOfPlayers to set
	 */
	public void setCorrectNumberOfPlayers(boolean correctNumberOfPlayers) {
		this.correctNumberOfPlayers = correctNumberOfPlayers;
	}

	/**
	 * @return the confirmed
	 */
	public boolean isConfirmed() {
		return confirmed;
	}

	/**
	 * @param confirmed the confirmed to set
	 */
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * @return the playerList
	 */
	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * @param playerList the playerList to set
	 */
	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	/**
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the currentSquare
	 */
	public Square getCurrentSquare() {
		return currentSquare;
	}

	/**
	 * @param currentSquare the currentSquare to set
	 */
	public void getCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}

	
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
		
		String confirm;

		System.out.println("How many people are playing? Choose 2, 3 ,4 or 5!");
		do {
			try {
				numberOfPlayers = scanner.nextInt();
				
				if( numberOfPlayers>= 2 && numberOfPlayers <= 5) {
					System.out.println("Are you sure you would like to play with " + numberOfPlayers + " players? Y/N");
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
							System.out.println("Oops, that doesn't seem right. Please type Y for yes or N for no and press return!");
						}
					} while (!confirmed);
				} else {
					System.out.println("Number of players can only be 2, 3, 4, or 5. Try again!");
				}
			} catch (InputMismatchException e) {
				System.out.println(
						"Oops, that doesn't seem right. Please type 2, 3, 4, or 5 to select the number of players and press return!");
				scanner.next();
			}
		} while (!correctNumberOfPlayers);
	}

	/**
	 * requests player names, loops until they are all confirmed
	 * 
	 * @param numberOfPlayers
	 */
	public void requestPlayerNames(int numberOfPlayers) {
		String name = null;
		String confirm;
		boolean nameConfirmed;
		boolean nameInputCorrect;
		for (int loop = 1; loop <= numberOfPlayers; loop++) {
			nameConfirmed = false;
			nameInputCorrect = false;
			System.out.println("Player " + (loop) + " please enter your first name!");
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
				playerList.add(new Player(1, name, loop, 1500, 0, 0, 0, 0, 0));
			} while (!nameConfirmed);

		}
		
		// set player 1 to go first
		for (Player element : playerList) {
			if (element.getPlayerNumber() == 1) {
				currentPlayer = element;
			}
		}
		// print out a welcome message to all the players
		System.out.print("Welcome, ");
		for (int i = 0; i < playerList.size(); i++) {
			if (i <= 2) {
				System.out.print(playerList.get(i).getName() + ", ");
			} else {
				System.out.print("and" + playerList.get(i).getName());
			}
		}
		System.out.print("...to Technopoly!! \n\n");
		generateBoard();
		currentPlayer.getOwnedSquares().add(board.getSquares().get(1));
		displayTurnOptions();
	}

	/**
	 * generates the board, with squares of appropriate values and fields
	 */
	public void generateBoard() {

		board = new Board();
		ArrayList<Square> squares = new ArrayList<Square>();
		
		// add squares to arrayList
		squares.add(fundingRound);
		squares.add(netflix);
		squares.add(mine1);
		squares.add(hulu);
		squares.add(digitalTax);
		squares.add(chance1);
		squares.add(ebay);
		squares.add(dataCentre1);
		squares.add(alibaba);
		squares.add(amazon);
		squares.add(holiday);
		squares.add(twitter);
		squares.add(instagram);
		squares.add(mine2);
		squares.add(facebook);
		squares.add(chance2);
		squares.add(hacked);
		squares.add(apple);
		squares.add(dataCentre2);
		squares.add(microsoft);
		
		// set board
		board.setSquares(squares);

	}

	/**
	 * displays the current player's turn options at the beginning of their turn.
	 * Loops until they end their turn or roll the dice.
	 */
	public void displayTurnOptions() {
		boolean done = false;
		int response;

		do { 
			System.out.println("It's " + currentPlayer.getName() + "'s turn");
			System.out.println("Hello " + currentPlayer.getName() + " , please choose an option:");
			System.out.println("1. Roll dice (Your turn will end after you choose this option)");
			System.out.println("2. Sell property");
			System.out.println("3. Grow business");
			System.out.println("4. View resources");
			System.out.println("5. Display board");
			System.out.println("6. Forfeit game");

			try {
				response = scanner.nextInt();
				switch (response) {
				case 1:
					Die d1 = new Die();
					Die d2 = new Die();
					int movement = (d1.rollDie() + d2.rollDie());
					updatePlayerPosition(movement, currentPlayer);
					for (Square square : board.getSquares()) {
						if (square.getPosition() == currentPlayer.getPosition()) {
							currentSquare = square;
						}
					}
					System.out.println("You rolled a " + movement + ", you have landed on " + currentSquare.getName());
					currentSquare.sendSquareDetails(currentPlayer, playerList, scanner);
					System.out.println(currentPlayer.getName()+"'s turn is over!");
					System.out.println();
					endTurn();
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
					board.displayBoard(currentPlayer);
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

	/**
	 * allows player to sell owned businesses
	 */
	private void sellBusiness() {
		boolean doneSellBusiness = false;

		String businessSelect;
		currentPlayer.displayOwnedSquares();
		if (currentPlayer.getOwnedSquares().size() > 0) {

			System.out.println("Type the name of the property you would like to sell, or type 'back' to go back.");
			do {
				try {
					businessSelect = scanner.next();
					// checks player owns the business they've named
					for (int i = 0; i < currentPlayer.getOwnedSquares().size(); i++) {
						if (currentPlayer.getOwnedSquares().get(i).getName().equalsIgnoreCase(businessSelect)) {
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
			System.out.println("Are you sure you would like to sell " + currentPlayer.getOwnedSquares().get(i).getName()
					+ "(Y/N)?");
			try {
				confirm = scanner.next();
				switch (confirm) {
				case "Y":
				case "y":
					Square square = null;
					for (int j = 0; j < board.getSquares().size(); j++) {

						if (currentPlayer.getOwnedSquares().get(i).getName()
								.equals(board.getSquares().get(j).getName())) {
							square = board.getSquares().get(j);
						}
					}
					if (currentPlayer.getOwnedSquares().get(i).getName().equals(square.getName())) {
						currentPlayer.setResource(currentPlayer.getResource() + square.getValue());
						System.out.println("You have sold " + square.getName() + " for " + square.getValue()
								+ " and now have " + currentPlayer.getResource());
						currentPlayer.getOwnedSquares().remove(i);
						System.out.println("Would you like to sell another business? (Y/N)");
						try {
							confirm = scanner.next();
							switch (confirm) {
							case "Y":
							case "y":
								if (currentPlayer.getOwnedSquares().size() == 0) {
									System.out.println("Looks like you don't have any businesses left to sell!");
									confirmSellBusiness = true;
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
	 * is called when someone goes below zero (paying rent/tax etc. on squares); may
	 * wish to add the value of properties to this?
	 * 
	 * @return
	 */
	private void endGame(Player currentPlayer) {
		System.out.println(currentPlayer.getName() + " has run out of money; game over!");
		displayFinalRankings();
		System.exit(0);
	}

	/**
	 * updates the player position after they move
	 * 
	 * @param movement
	 * @param player
	 */
	public void updatePlayerPosition(int movement, Player player) {
		if ((player.getPosition() + movement) <= 20) {
			player.setPosition(player.getPosition() + movement);
		} else {
			player.setPosition((player.getPosition() + movement) - 20);
			int passGoResource = player.getResource() + 200;
			player.setResource(passGoResource);
			System.out.println("You have passed the investment round and will collect 200 Techcoin");
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
					displayFinalRankings();
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
		System.exit(0);
	}
	
	/**
	 * allows player to choose a company to build 
	 * offices or campuses on if they own all companies in that field
	 */
	public void growBusiness() {
		
		String company;
		boolean doneGrowBusiness = false;
		int officeCost;
		int campusCost;
		
		if(currentPlayer.getNumberOfStreamingServiceOwned() == 2 || currentPlayer.getNumberOfRetailOwned() == 3 ||
				currentPlayer.getNumberOfSocialMediaOwned() == 3 || currentPlayer.getNumberOfTechGiantOwned() == 2) {
			do {
				System.out.println("Enter the name of the company you'd like to build on, or enter 'back' to go back: ");
				try {
					company = scanner.nextLine();
					
					if(currentPlayer.getNumberOfStreamingServiceOwned() == 2 && company.equalsIgnoreCase("Netflix") || 
							company.equalsIgnoreCase("Hulu")) {
						officeCost = netflix.getOfficeCost();
						campusCost = netflix.getCampusCost(); 
						buildCompany(company, officeCost, campusCost);
					} else if (currentPlayer.getNumberOfRetailOwned() == 3 && company.equalsIgnoreCase("Ebay") || 
							company.equalsIgnoreCase("Alibaba") || company.equalsIgnoreCase("Amazon")) {
						officeCost = ebay.getOfficeCost();
						campusCost = ebay.getCampusCost(); 
						buildCompany(company, officeCost, campusCost);
					} else if (currentPlayer.getNumberOfSocialMediaOwned() == 3 && company.equalsIgnoreCase("Twitter") || 
							company.equalsIgnoreCase("Instagram") || company.equalsIgnoreCase("Facebook")) {
						officeCost = twitter.getOfficeCost();
						campusCost = twitter.getCampusCost(); 
						buildCompany(company, officeCost, campusCost);
					} else if (currentPlayer.getNumberOfTechGiantOwned() == 2 && company.equalsIgnoreCase("Apple") || 
							company.equalsIgnoreCase("Microsoft")) {
						officeCost = apple.getOfficeCost();
						campusCost = microsoft.getCampusCost();
						buildCompany(company, officeCost, campusCost);
					} else if (company.equalsIgnoreCase("back")) {
						doneGrowBusiness = true;
					} else {
						System.out.println("You must enter the name of a company you own and own all companies in that field.");
					}
					
				} catch (InputMismatchException e) {
					System.out.println("You must enter a valid company name that you own.");
				}
			} while (!doneGrowBusiness);
		} else {
			System.out.println("You must own all of the companies in a field in order to build! Try again when you do.");
		}
	}
	
	/**
	 * gives player option to choose between an office or campus, 
	 * and then updates their resources and the number of offices/campuses at that company
	 * @param company
	 * @param officeCost
	 * @param campusCost
	 */
	public void buildCompany(String company, int officeCost, int campusCost) {
		String buildType;
		boolean doneBuildCompany = false;
		for (int loop = 0; loop < currentPlayer.getOwnedCompanies().size(); loop++) {
			if(currentPlayer.getOwnedCompanies().get(loop).getName().equalsIgnoreCase(company)) {
				do {
					try {
						System.out.println("Would you like to add an office or a campus to " + currentPlayer.getOwnedCompanies().get(loop).getName() + "?");
						System.out.println("An office on " + company + " costs " + officeCost + " Techcoin");
						System.out.println("A campus on " + company + " costs " + campusCost + " Techcoin");
						System.out.println("Enter 'office' for office, 'campus' for campus, or 'back' to go back : ");
						buildType = scanner.nextLine();
						
						if(buildType.equalsIgnoreCase("office")) {
							if(currentPlayer.getOwnedCompanies().get(loop).getNumberOfOffices() < 4) {
								if(currentPlayer.getResource() - officeCost <= 0) {
									System.out.println("Oops, you can't afford to build an office at "+ company +"!");
								} else {
									int newNumberOfOffices = currentPlayer.getOwnedCompanies().get(loop).getNumberOfOffices() + 1;
									currentPlayer.getOwnedCompanies().get(loop).setNumberOfOffices(newNumberOfOffices);
									currentPlayer.getOwnedCompanies().get(loop).updateResource(-officeCost, currentPlayer);
									currentPlayer.getOwnedCompanies().get(loop).addSubscription(newNumberOfOffices, 0);
									System.out.println("You now have " + currentPlayer.getOwnedCompanies().get(loop).getNumberOfOffices() + "/4 offices at " + company);
								}
								
							} else {
								System.out.println("You cannot build any more offices at" + currentPlayer.getOwnedCompanies().get(loop).getName() + ". Build a campus instead!");
							}
						} else if(buildType.equalsIgnoreCase("campus")) {
							if(!currentPlayer.getOwnedCompanies().get(loop).isHasCampus() && currentPlayer.getOwnedCompanies().get(loop).getNumberOfOffices() == 4) {
								if(currentPlayer.getResource() - campusCost <= 0) {
									System.out.println("Oops, you can't afford to build a campus at "+ company +"!");
								} else {
									int campusNumber = 1;
									currentPlayer.getOwnedCompanies().get(loop).setNumberOfCampuses(campusNumber);
									currentPlayer.getOwnedCompanies().get(loop).setHasCampus(true);
									currentPlayer.getOwnedCompanies().get(loop).updateResource(-campusCost, currentPlayer);
									currentPlayer.getOwnedCompanies().get(loop).addSubscription(4, campusNumber);
									System.out.println("You now have a campus on " + company + "! You can't build anymore on this company.");
								}
							} else if(currentPlayer.getOwnedCompanies().get(loop).isHasCampus()){
								System.out.println("You already have a campus on " + company + "! You can't build anymore on this company.");
							} else if (currentPlayer.getOwnedCompanies().get(loop).getNumberOfOffices() < 4) {
								System.out.println("You must have 4 offices at a company before you can build a campus!");
							}
							
						} else if(buildType.equalsIgnoreCase("back")) {
							doneBuildCompany = true;
						} 
						
					} catch (InputMismatchException e) {
						System.out.println("That is not a valid input, please enter 'office' for office, 'campus' for campus, or 'cancel' to go back");
					}
				} while (!doneBuildCompany);
			}
			
		}
	}
	
	/**
	 * displays final rankings of players by their resources
	 */
	public void displayFinalRankings() {
		int playerPlacing = 1;
		for(Player player : playerList) {
			player.addTotalResources();
		}
		
		playerList.sort(Comparator.comparing(Player::getResource));
		System.out.println("Congratulations "+ playerList.get(0).getName() +", you won Technopoly!");
		for (Player player : playerList) {
			System.out.println(playerPlacing + ". " + player.getName() + ": " + player.getResource() + " Techcoins and " + player.getOwnedSquares().size() + " companies");
			playerPlacing++;
		}
		System.out.println("Thanks for playing Technopoly!");
	}
	
	/**
	 * checks if players all have over 0 resources, if not ends game
	 * then changes currentPlayer to next player in arrayList
	 */
	public void endTurn() {
		
		for(Player player : playerList) {
			if(player.getResource() <= 0) {
				endGame(player);
			} 
		}
		
		int currentPlayerNumber = currentPlayer.getPlayerNumber();
		
		// checks that a round of turns has been completed and resets to player 1 if true
		if (currentPlayerNumber == playerList.size()) {
			currentPlayerNumber = 1;
		} else {
			currentPlayerNumber++;
		}
		
		for(Player nextPlayer : playerList) {
			if (nextPlayer.getPlayerNumber() == currentPlayerNumber) {
				currentPlayer = nextPlayer;
				displayTurnOptions();
			}
		}
		
	}
}
