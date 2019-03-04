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
    private ArrayList<Square> field1 = new ArrayList<>();
    private ArrayList<Square> field2 = new ArrayList<>();
    private ArrayList<Square> field3 = new ArrayList<>();
    private ArrayList<Square> field4 = new ArrayList<>();


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

    public ArrayList<Square> getField1() {
        return field1;
    }

    public void setField1(ArrayList<Square> field1) {
        this.field1 = field1;
    }

    public ArrayList<Square> getField2() {
        return field2;
    }

    public void setField2(ArrayList<Square> field2) {
        this.field2 = field2;
    }

    public ArrayList<Square> getField3() {
        return field3;
    }

    public void setField3(ArrayList<Square> field3) {
        this.field3 = field3;
    }

    public ArrayList<Square> getField4() {
        return field4;
    }

    public void setField4(ArrayList<Square> field4) {
        this.field4 = field4;
    }

    public void addFields(ArrayList<Square> squares) {
        for (Square square : squares) {
            switch (square.getField()) {
                case "field1":
                    field1.add(square);
                    break;
                case "field2":
                    field2.add(square);
                    break;
                case "field3":
                    field3.add(square);
                    break;
                case "field4":
                    field4.add(square);
                default:
                    break;
            }
        }
    }
}
