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
        //currently 'value' is used to store both the cost to purchase and the amount someone gains/loses
        //from stopping there - change?
        squares.add(new Square("Funding Round", 1, 200,"N/A"));
        squares.add(new Square("Netflix", 2, 60, "Streaming"));
        squares.add(new Square("TechCoin Mine", 3, 150, "N/A"));
        squares.add(new Square("Hulu", 4, 60, "Streaming"));
        squares.add(new Square("Digital Tax", 5, 70, "N/A"));
        squares.add(new Square("Chance", 6, 0, "N/A"));
        squares.add(new Square("eBay", 7, 140, "eCommerce"));
        squares.add(new Square("Data Centre", 8, 140, "N/A"));
        squares.add(new Square("Alibaba", 9, 140, "eCommerce"));
        squares.add(new Square("Amazon", 10, 160, "eCommerce"));
        squares.add(new Square("Holiday", 11, 0, "N/A"));
        squares.add(new Square("Twitter", 12, 260, "Social Media"));
        squares.add(new Square("Instagram", 13, 140, "Social Media"));
        squares.add(new Square("Techcoin Mine", 14, 150, "N/A"));
        squares.add(new Square("Facebook", 15, 140, "Social Media"));
        squares.add(new Square("Chance", 16, 140, "N/A"));
        squares.add(new Square("Hacked", 17, 200, "N/A"));
        squares.add(new Square("Apple", 18, 350, "Software Giant"));
        squares.add(new Square("Data Centre", 19, 140, "N/A"));
        squares.add(new Square("Microsoft", 20, 400, "Software giant"));
        board.setSquares(squares);

    }

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
                    for (int i = 0; i < currentPlayer.getOwnedSquares().size(); i++) {
                        if (currentPlayer.getOwnedSquares().get(i).getName()
                                .equalsIgnoreCase(businessSelect)) {
                            doneSellBusiness = confirmSellBusiness(doneSellBusiness, i);
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
        if ((player.getPosition() + movement) <= 20) {
            player.setPosition(player.getPosition() + movement);
        } else {
            player.setPosition((player.getPosition() + movement) - 20);
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
                                                System.out.println("Would you like to build an office or a campus?");
                                                try {
                                                    officeCampus = scanner.next();
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Sorry, that's not a valid response. Please type 'office' to build an office or 'campus' to build a campus and press return.");
                                                }
                                                switch (officeCampus) {
                                                    case "Office":
                                                    case "office":
                                                        if (currentPlayer.getResource() >= 100) {
                                                            if (square.getNumberOfHouses() < 4) {
                                                                square.setNumberOfHouses(square.getNumberOfHouses() + 1);
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
                                                        if (currentPlayer.getResource() >= 300) {
                                                            if (!square.isHasCampus()) {
                                                                if (square.getNumberOfHouses() == 4) {
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
