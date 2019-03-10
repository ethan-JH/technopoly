/**
 *
 */
package technopoly;

import java.util.ArrayList;

/**
 * @author Luke
 *
 */
public class Board {
    private ArrayList<Square> squares = new ArrayList<>();

    /**
     * generates the board, with squares of appropriate values and fields
     */
    public void generateBoard(){

        squares.add(new GO("Funding Round", 1, 200, "N/A"));
        squares.add(new StreamingService("Netflix", 2, 60, "Streaming Service", 0, 0, 0, false, 40, 40, 12));
        squares.add(new Utility("TECHCOIN MINE", 3, 150, "Utility", 0, 30));
        squares.add(new StreamingService("Hulu", 4, 60, "Streaming Service", 0, 0, 0, false, 40, 40, 12));
        squares.add(new Tax("Digital Tax", 5, 200, "Digital Tax"));
        squares.add(new Chance("Chance", 6, 0, "Chance", "Chance"));
        squares.add(new Retail("Ebay", 7, 140, "Retail", 0, 0, 0, false, 105, 105, 28));
        squares.add(new Utility("DATA CENTRE", 8, 150, "DATA CENTRE", 0, 30));
        squares.add(new Retail("Alibaba", 9, 140, "Retail", 0, 0, 0, false, 105, 105, 28));
        squares.add(new Retail("Amazon", 10, 160, "Retail", 0, 0, 0, false, 120, 120, 32));
        squares.add(new Holiday("Holiday", 11, 0, "Holiday"));
        squares.add(new SocialMedia("Twitter", 12, 260, "Social Media", 0, 0, 0, false, 195, 195, 52));
        squares.add(new SocialMedia("Instagram", 13, 260, "Social Media", 0, 0, 0, false, 195, 195, 52));
        squares.add(new Utility("TECHCOIN MINE", 14, 150, "TECHCOIN MINE", 0, 30));
        squares.add(new SocialMedia("Facebook", 15, 280, "Social Media", 0, 0, 0, false, 210, 210, 56));
        squares.add(new Chance("Chance", 16, 0, "Chance", "Chance"));
        squares.add(new Tax("Hacked", 17, 100, "Hacked"));
        squares.add(new TechGiant("Apple", 18, 350, "Tech Giant", 0, 0, 0, false, 265, 265, 70));
        squares.add(new Utility("DATA CENTRE", 19, 150, "DATA CENTRE", 0, 30));
        squares.add(new TechGiant("Microsoft", 20, 400, "Tech Giant", 0, 0, 0, false, 300, 300, 80));
    }


    public ArrayList<Square> getSquares() {
        return squares;
    }

    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }

    public void displayBoard() {
        //can this also include player positions?
        for (int i = 0; i < squares.size(); i++) {
            System.out.println((i + 1) + ". " + squares.get(i).getName());
            System.out.println();
        }
    }

    public void selectDestinationSquare(int position, Player player) {
        for (Square element : squares) {
            if (element.getPosition() == player.getPosition()) {
                element.sendSquareDetails(player);
            }
        }
    }


    }

