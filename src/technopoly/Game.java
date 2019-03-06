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
 *
 */
public class Game {
    Scanner scanner = new Scanner(System.in);
    private int numberOfPlayers;
    private boolean correctNumberOfPlayers = false;
    private boolean confirmed = false;
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Player currentPlayer;
    private Board board;
    private String currentSquare;

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
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

        System.out.println("How many people are playing? Choose 1, 2, 3 or 4!");
        do {
            try {
                numberOfPlayers = scanner.nextInt();
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
                        System.out.println(
                                "Oops, that doesn't seem right. Please type Y for yes or N for no and press return!");
                    }
                } while (!confirmed);

            } catch (InputMismatchException e) {
                System.out.println(
                        "Oops, that doesn't seem right. Please type 1, 2, 3 or 4 to select the number of players and press return!");
                scanner.next();
            }
        } while (!correctNumberOfPlayers);
        scanner.close();
    }

    /**
     * requests player names, loops until they are all confirmed
     * @param numberOfPlayers
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
        // set player 1 to go first
        for (Player element : playerList) {
            if (element.getPlayerNumber() == 1) {
                currentPlayer = element;
            }
        }
        //print out a welcome message to all the players
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
        //currently 'value' is used to store both the cost to purchase and the amount someone gains/loses
        //from stopping there - change?

        squares.add(new GO("Funding Round", 1, 200,"N/A"));
             squares.add(new StreamingService("Netflix", 2, 60, "Streaming Service", "Not Owned", 0,0 , false, 40, 40, 12));
             squares.add(new Utility("TECHCOIN MINE", 3, 150, "Utility", 0, 30));
             squares.add(new StreamingService("Hulu", 4, 60, "Streaming Service", "Not Owned", 0, 0, false, 40, 40, 12));
             squares.add(new Tax("Digital Tax", 5, 200, "Digital Tax"));
             squares.add(new Chance("Chance", 6, 0, "Chance", "Chance"));
             squares.add(new Retail("Ebay", 7, 140, "Retail", "Not Owned", 0, 0, false, 105, 105, 28));
             squares.add(new Utility("DATA CENTRE", 8, 150, "DATA CENTRE", 0, 30));
             squares.add(new Retail("Alibaba", 9, 140, "Retail", "Not Owned", 0, 0, false, 105, 105, 28));
             squares.add(new Retail("Amazon", 10, 160, "Retail", "Not Owned", 0, 0, false, 120, 120, 32));
             squares.add(new Holiday("Holiday", 11, 0, "Holiday"));
             squares.add(new SocialMedia("Twitter", 12, 260, "Social Media", "Not Owned", 0,0,false, 195, 195, 52));
             squares.add(new SocialMedia("Instagram",13, 260, "Social Media", "Not Owned", 0,0,false, 195, 195, 52));
             squares.add(new Utility("TECHCOIN MINE", 14, 150, "TECHCOIN MINE", 0, 30));
             squares.add(new SocialMedia("Facebook", 15, 280, "Social Media", "Not Owned", 0,0,false, 210, 210, 56));
             squares.add(new Chance("Chance", 16, 0, "Chance", "Chance"));
             squares.add(new Tax("Hacked", 17, 100, "Hacked"));
             squares.add(new TechGiant("Apple", 18, 350, "Tech Giant", "Not Owned", 0, 0, false, 265, 265, 70));
             squares.add(new Utility("DATA CENTRE", 19, 150, "DATA CENTRE", 0, 30));
             squares.add(new TechGiant("Microsoft", 20, 400, "Tech Giant", "Not Owned", 0, 0, false, 300, 300, 80));
             board.setSquares(squares);
       
        board.setSquares(squares);
    
    }

    /**
     * displays the current player's turn options at the beginning of their turn. Loops until they end their turn or roll the dice.
     */
    public void displayTurnOptions() {
        boolean done = false;
        int response;

        do { // do we want something to load games?
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
                        Die d1 = new Die();
                        Die d2 = new Die();
                        int movement = (d1.rollDie() + d2.rollDie());
                        updatePlayerPosition(movement, currentPlayer);
                        for(Square square: board.getSquares()){
                            if(square.getPosition() == currentPlayer.getPosition()){
                                currentSquare = square.getName();
                            }
                        }
                        System.out.println("You rolled a "+movement + ", you have landed on " + currentSquare);
                        break;
                    case 2:
                        sellBusiness();
                        break;
                    case 3:
                        growBusiness();
                        break;
                    case 4:
                        currentPlayer.displayOwnedSquares();
                        System.out.println("You currently have £" + currentPlayer.getResource());
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

    /**
     * allows player to sell owned businesses
     */
    private void sellBusiness() {
        boolean doneSellBusiness = false;


        String businessSelect;
        currentPlayer.displayOwnedSquares();
        if (currentPlayer.getOwnedSquares().size() > 0) {

            System.out.println(
                    "Type the name of the property you would like to sell, or type 'back' to go back.");
            do {
                try {
                    businessSelect = scanner.next();
                    //checks player owns the business they've named
                    for (int i = 0; i < currentPlayer.getOwnedSquares().size(); i++) {
                        if (currentPlayer.getOwnedSquares().get(i).getName()
                                .equalsIgnoreCase(businessSelect)) {
                            doneSellBusiness = confirmSellBusiness(doneSellBusiness, i);
                            //allows player to go back if they type 'back'
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
     * is called within sell business, further logic to confirm the player is done selling
     * @param doneSellProperty
     * @param i
     * @return
     */
    private boolean confirmSellBusiness(boolean doneSellProperty, int i) {
        boolean confirmSellBusiness = false;
        String confirm;
        do {
            System.out.println("Are you sure you would like to sell "
                    + currentPlayer.getOwnedSquares().get(i).getName() + "(Y/N)?");
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
                        if (currentPlayer.getOwnedSquares().get(i).getName()
                                .equals(square.getName())) {
                            currentPlayer.setResource(
                                    currentPlayer.getResource() + square.getValue());
                            square.setHasCampus(false);
                            System.out.println("You have sold " + square.getName() + " for "
                                    + square.getValue() + " and now have "
                                    + currentPlayer.getResource());
                            currentPlayer.getOwnedSquares().remove(i);
                            System.out.println(
                                    "Would you like to sell another business? (Y/N)");
                            try {
                                confirm = scanner.next();
                                switch (confirm) {
                                    case "Y":
                                    case "y":
                                        if (currentPlayer.getOwnedSquares().size() == 0) {
                                            System.out.println(
                                                    "Looks like you don't have any businesses left to sell!");
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
                                        System.out.println(
                                                "Sorry, that's not a valid input! Please type Y for yes or N for no.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println(
                                        "Sorry, that's not a valid input! Please type Y for yes or N for no.");
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
                        System.out.println(
                                "Sorry, that wasn't a valid input. Please type Y for yes or N for no.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println(
                        "Sorry, that wasn't a valid input. Please type Y for yes or N for no.");
            }
        } while (!confirmSellBusiness);
        return doneSellProperty;
    }

    /**
     * is called when someone goes below zero (paying rent/tax etc. on squares); may wish to add the value of properties to this?
     * @return
     */
    private void endGame(Player currentPlayer) {
        System.out.println(currentPlayer.getName() + " has run out of money; game over!");
        playerList.sort(Comparator.comparing(Player::getResource));
        for(Player player: playerList){
            System.out.println(player.getName() + ": "+player.getResource());
        }
    }

    /**
     * updates the player position after they move
     * @param movement
     * @param player
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
        //test this
        boolean doneGrowBusiness = false;
        boolean confirmed = false;
        String business;
        String confirm;
        String officeCampus = null;
        String developAnother;
        String field;
        int numberOwned = 0;
        int numberInField = 0;
        if (currentPlayer.getOwnedSquares().size() > 0) {
            do {
                currentPlayer.displayOwnedSquares();
                System.out.println("Type the name of the business you would like to grow and press return, or type 'back' to go back!");
                try {
                    business = scanner.next();
                    for (Square square : currentPlayer.getOwnedSquares()) {
                        if (business.equalsIgnoreCase(square.getName())) {
                            System.out.println("Are you sure you would like to grow " + square.getName() + "?(Y/N)");
                            field = square.getField();
                            for(Square ownedSquare: currentPlayer.getOwnedSquares()){
                                //numberOwned keeps track of the number of businesses in a field the player owns
                                if(ownedSquare.getField().equals(square.getField())){
                                    numberOwned += 1;
                                }
                            }
                            //finds how many squares there are on the board within the field of the business selected by the user
                            for(Square allSquares: board.getSquares()){
                                if(allSquares.getField().equals(field)){
                                    numberInField += 1;
                                }
                            }
                            //compares number of businesses in field owned by player and number on the board; only allows player to build offices/campuses if they own all the businesses in a field
                            if(numberOwned == numberInField) {
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
                                                    System.out.println("Sorry, that's not a valid response. Please type 'office' to build an office or 'campus' to build a campus and press return.");
                                                }
                                                switch (officeCampus) {
                                                    case "Office":
                                                    case "office":
                                                        //cost to build 100, may change this?
                                                        if (currentPlayer.getResource() >= 100) {
                                                            //prevents user from building more than four offices for a business
                                                            if(square.getNumberOfOffices() < 4) {
                                                                square.setNumberOfOffices(square.getNumberOfOffices() + 1);
                                                                currentPlayer.setResource(currentPlayer.getResource() - 100);
                                                                System.out.println("OK, an office has now been built for " + square.getName() + ". Would you like to grow another business?(Y/N)");
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
                                                                            System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                                                            break;
                                                                    }
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                                                }
                                                            } else {
                                                                System.out.println("Sorry, you cannot have more than four offices for a business!");
                                                                break;
                                                            }
                                                        } else {
                                                            System.out.println("Sorry, you have insufficient resources to build an office!");
                                                            break;
                                                        }

                                                        break;
                                                    case "Campus":
                                                    case "campus":
                                                        //cost to build 300, could change this?
                                                        if (currentPlayer.getResource() >= 300) {
                                                            if (!square.isHasCampus()) {
                                                                //must have four offices to place a campus
                                                                if (square.getNumberOfOffices() == 4) {
                                                                    currentPlayer.setResource(currentPlayer.getResource() - 300);
                                                                    square.setHasCampus(true);
                                                                    System.out.println("OK, a campus has now been built for " + square.getName() + ". You now have " + currentPlayer.getResource() + " resources. Would you like to develop another property?(Y/N)");
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
                                                                                System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                                                                break;
                                                                        }
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                                                    }
                                                                    break;
                                                                } else {
                                                                    System.out.println("Sorry, you need to build four offices for a business before you can build a campus!");
                                                                    break;
                                                                }
                                                            } else {
                                                                System.out.println("Sorry, you can only build one campus per field!");
                                                                break;
                                                            }
                                                        } else {
                                                            System.out.println("Sorry, you have insufficient resource to build a campus!");
                                                            break;
                                                        }
                                                    default:
                                                        System.out.println("Sorry, that's not a valid response. Please type 'office' to build an office or 'campus' to build a campus and press return.");
                                                        break;
                                                }
                                            } while (!confirmed);
                                            break;
                                        case "N":
                                        case "n":
                                            break;
                                        default:
                                            System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                }
                            } else {
                                System.out.println("Sorry, you need to own all the businesses in a field to develop it! Let's go back.");
                                doneGrowBusiness = true;
                            }

                        } else if (business.equalsIgnoreCase("back")) {
                            System.out.println("OK, let's go back!");
                            doneGrowBusiness = true;
                        } else {
                            System.out.println("Sorry, that's not a business you own! Please type the name of the business you wish to grow and press return.");
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Sorry, that's not a business you own! Please type the name of the business you wish to grow and press return.");
                }

            } while (!doneGrowBusiness);
        } else {
            System.out.println("It appears that you don't have any businesses to develop!");
        }
    }
}
