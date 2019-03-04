/**
 *
 */
package technopoly;

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
    ArrayList<Player> playerList = new ArrayList<Player>();
    private Player currentPlayer;
    Board board;
    String currentSquare;

    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }

    public void startGame() {
        requestNumberOfPlayers();
    }

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

    public void generateBoard() {
        board = new Board();
        ArrayList<Square> squares = new ArrayList<Square>();
        // change these names when we get our fields
        //move to board?
        String[] squareNames = {"Square_1", "Square_2", "Square 3", "Square 4", "Square 5", "Square 6", "Square 7",
                "Square 8", "Square 9", "Square 10"};

        squares.add(new Square("Go", 1, 0,"N/A"));
        squares.add(new Square("Free Parking", 6, 0, "N/A"));

        for (int i = 0; i < 2; i++) {
            squares.add(new Square(squareNames[i], (i + 1), 50, "Field1"));
        }
        for (int i = 2; i < 5; i++) {
            squares.add(new Square(squareNames[i], (i + 1), 100, "Field2"));
        }
        for (int i = 5; i < 8; i++) {
            squares.add(new Square(squareNames[i], (i + 1), 150, "Field3"));
        }
        for (int i = 8; i < 10; i++) {
            squares.add(new Square(squareNames[i], (i + 1), 200, "Field4"));
        }
        board.setSquares(squares);

    }

    public void displayTurnOptions() {
        boolean done = false;
        int response;

        do { // do we want something to load games?
            System.out.println("Hello " + currentPlayer.getName() + " , please choose an option:");
            System.out.println("1. Roll dice");
            System.out.println("2. Sell property");
            System.out.println("3. Develop property");
            System.out.println("4. View resources");
            System.out.println("5. Display board");
            System.out.println("6. End turn");
            System.out.println("7. Forfeit game");
            System.out.println("8. Save & quit");

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
                        sellProperty();
                        break;
                    case 3:
                        developProperty();
                        break;
                    case 4:
                        currentPlayer.displayOwnedSquares();
                        System.out.println("You currently have £" + currentPlayer.getResource());
                        break;
                    case 5:
                        board.displayBoard();
                        break;
                    case 6:
                        done = endTurn(done);
                        break;
                    case 7:
                        endGame();
                        break;
                    case 8: // save and quit
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

    private void sellProperty() {
        boolean doneSellProperty = false;


        String propertySelect;
        currentPlayer.displayOwnedSquares();
        if (currentPlayer.getOwnedSquares().size() > 0) {

            System.out.println(
                    "Type the name of the property you would like to sell, or type 'back' to go back.");
            do {
                try {
                    propertySelect = scanner.next();
                    for (int i = 0; i < currentPlayer.getOwnedSquares().size(); i++) {
                        if (currentPlayer.getOwnedSquares().get(i).getName()
                                .equalsIgnoreCase(propertySelect)) {
                            doneSellProperty = confirmSellProperty(doneSellProperty, i);
                        } else if (propertySelect.equalsIgnoreCase("back")) {
                            doneSellProperty = true;
                        } else {
                            System.out.println(
                                    "Sorry, that wasn't a valid input. Please type the name of a property you would like to sell and press return, or type 'back' to go back.");

                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println(
                            "Sorry, that's not a property you own! Try again, or alternatively type 'back' to go back.");
                }
            } while (!doneSellProperty);

        }
    }

    private boolean confirmSellProperty(boolean doneSellProperty, int i) {
        boolean confirmSellProperty = false;
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
                            System.out.println("You have sold " + square.getName() + " for "
                                    + square.getValue() + " and now have "
                                    + currentPlayer.getResource());
                            currentPlayer.getOwnedSquares().remove(i);
                            System.out.println(
                                    "Would you like to sell another property? (Y/N)");
                            try {
                                confirm = scanner.next();
                                switch (confirm) {
                                    case "Y":
                                    case "y":
                                        if (currentPlayer.getOwnedSquares().size() == 0) {
                                            System.out.println(
                                                    "Looks like you don't have any properties left to sell!");
                                            doneSellProperty = true;
                                            break;
                                        }
                                        System.out.println(
                                                "Type the name of the property you would like to sell, or type 'back' to go back.");
                                        confirmSellProperty = true;
                                        break;
                                    case "N":
                                    case "n":
                                        doneSellProperty = true;
                                        confirmSellProperty = true;
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
                                "Type the name of the property you would like to sell, or type 'back' to go back.");
                        confirmSellProperty = true;
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
        } while (!confirmSellProperty);
        return doneSellProperty;
    }

    private boolean endTurn(boolean done) {
        String confirmEnd;
        boolean endConfirmed = false;
        do {
            System.out.println("Are you sure you would like to end your turn? (Y/N)");
            try {
                confirmEnd = scanner.next();
                switch (confirmEnd) {
                    case "Y":
                    case "y":
                        System.out.println("Alright, advancing turn!");
                        endConfirmed = true;
                        done = true;
                        if (currentPlayer == playerList.get((playerList.size() - 1))) {
                            currentPlayer = playerList.get(0);

                        } else {
                            currentPlayer = playerList.get(currentPlayer.getPlayerNumber());
                        }
                        displayTurnOptions();

                        break;
                    case "N":
                    case "n":
                        System.out.println("Alright, let's go back");
                        endConfirmed = true;
                        break;
                    default:
                        System.out.println(
                                "Sorry, that's not a valid input. Please type Y for yes or N for no and hit return.");

                }

            } catch (InputMismatchException e) {
                System.out.println("Sorry, that's an invalid input. Please type Y for yes or N for no.");
            }
        } while (!endConfirmed);
        return done;
    }

    public void updatePlayerPosition(int movement, Player player) {
        if ((player.getPosition() + movement) <= 12) {
            player.setPosition(player.getPosition() + movement);
        } else {
            player.setPosition((player.getPosition() + movement) - 12);
        }
    }

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

    public void developProperty() {
        //test this
        boolean doneDevelopProperty = false;
        boolean confirmed = false;
        String property;
        String confirm;
        String houseHotel = null;
        String developAnother;
        String field;
        int numberOwned = 0;
        int numberInField = 0;
        if (currentPlayer.getOwnedSquares().size() > 0) {
            do {
                currentPlayer.displayOwnedSquares();
                System.out.println("Type the name of the property you would like to develop and press return, or type 'back' to go back!");
                try {
                    property = scanner.next();
                    for (Square square : currentPlayer.getOwnedSquares()) {
                        if (property.equalsIgnoreCase(square.getName())) {
                            System.out.println("Are you sure you would like to develop " + square.getName() + "?(Y/N)");
                            field = square.getField();
                            for(Square ownedSquare: currentPlayer.getOwnedSquares()){
                                if(ownedSquare.getField().equals(square.getField())){
                                    numberOwned += 1;
                                }
                            }
                            for(Square allSquares: board.getSquares()){
                                if(allSquares.getField().equals(field)){
                                    numberInField += 1;
                                }
                            }
                            if(numberOwned == numberInField) {
                                try {
                                    confirm = scanner.next();
                                    switch (confirm) {
                                        case "Y":
                                        case "y":
                                            do {
                                                System.out.println("Would you like to place a house or a hotel?");
                                                try {
                                                    houseHotel = scanner.next();
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Sorry, that's not a valid response. Please type 'house' to place a house or 'hotel' to place a hotel and press return.");
                                                }
                                                switch (houseHotel) {
                                                    case "House":
                                                    case "house":
                                                        if (currentPlayer.getResource() >= 100) {
                                                            if (square.getNumberOfHouses() < 4) {
                                                                square.setNumberOfHouses(square.getNumberOfHouses() + 1);
                                                                currentPlayer.setResource(currentPlayer.getResource() - 100);
                                                                System.out.println("OK, a house has now been placed on " + square.getName() + ". Would you like to develop another property?(Y/N)");
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
                                                                            doneDevelopProperty = true;
                                                                            break;
                                                                        default:
                                                                            System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                                                            break;
                                                                    }
                                                                } catch (InputMismatchException e) {
                                                                    System.out.println("Sorry, that's not a valid response. Please type Y for yes or N for no and press return.");
                                                                }
                                                            } else {
                                                                System.out.println("Sorry, you cannot have more than four houses on a property!");
                                                                break;
                                                            }
                                                        } else {
                                                            System.out.println("Sorry, you have insufficient resources build a house!");
                                                            break;
                                                        }

                                                        break;
                                                    case "Hotel":
                                                    case "hotel":
                                                        if (currentPlayer.getResource() >= 300) {
                                                            if (!square.isHasHotel()) {
                                                                if (square.getNumberOfHouses() == 4) {
                                                                    currentPlayer.setResource(currentPlayer.getResource() - 300);
                                                                    square.setHasHotel(true);
                                                                    System.out.println("OK, a hotel has now been placed on " + square.getName() + ". You now have " + currentPlayer.getResource() + " resources. Would you like to develop another property?(Y/N)");
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
                                                                                doneDevelopProperty = true;
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
                                                                    System.out.println("Sorry, you need to have four houses on a property before you can build a hotel!");
                                                                    break;
                                                                }
                                                            } else {
                                                                System.out.println("Sorry, you can only build one hotel per field!");
                                                                break;
                                                            }
                                                        } else {
                                                            System.out.println("Sorry, you have insufficient resource to build a hotel!");
                                                            break;
                                                        }
                                                    default:
                                                        System.out.println("Sorry, that's not a valid response. Please type 'house' to place a house or 'hotel' to place a hotel and press return.");
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
                                System.out.println("Sorry, you need to own all the properties in a field to develop it! Let's go back.");
                                doneDevelopProperty = true;
                            }

                        } else if (property.equalsIgnoreCase("back")) {
                            System.out.println("OK, let's go back!");
                            doneDevelopProperty = true;
                        } else {
                            System.out.println("Sorry, that's not a property you own! Please type the name of the property you wish to develop and press return.");
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Sorry, that's not a property you own! Please type the name of the property you wish to develop and press return.");
                }

            } while (!doneDevelopProperty);
        } else {
            System.out.println("It appears that you don't have any properties to develop!");
        }
    }
}
