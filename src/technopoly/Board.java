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
    private ArrayList<Square> squares = new ArrayList<Square>();


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

