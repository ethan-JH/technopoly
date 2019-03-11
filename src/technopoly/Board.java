/**
 *
 */
package technopoly;

import java.util.ArrayList;

/**
 * @author Luke
 * class to hold board which contains an ArrayList of the Squares used in the game
 */
public class Board {

	
	// ArrayList to hold squares
    private ArrayList<Square> squares = new ArrayList<Square>();
    
    /**
     * default constructor
     */
    public Board() {
    	
    }
    
    /**
     * constructor with args
     * @param squares
     */
    public Board(ArrayList<Square> squares) {
    	this.squares = squares;
    }


    public ArrayList<Square> getSquares() {
        return squares;
    }
    
    /**
     * sets the squares
     * @param squares
     */
    public void setSquares(ArrayList<Square> squares) {
        this.squares = squares;
    }
    
    /**
     * displays all the squares on the board and the order they appear in
     * tells the current player what square they are on
     */
    public void displayBoard(Player player) {
        for (int i = 0; i < squares.size(); i++) {
            System.out.printf("%d. %s" ,(i + 1), squares.get(i).getName());
            if(player.getPosition() == squares.get(i).getPosition()) {
            	System.out.printf(" : You are at this square");
            }
            System.out.println();
        }
        System.out.println();
    }
    
  
   
}


