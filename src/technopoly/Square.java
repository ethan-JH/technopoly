package technopoly;

public class Square {
	private String name;
	private int position;
	private int value;
	private boolean hasHotel = false;
	private int numberOfHouses = 0;
	private String field;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Square(String name, int position, int value, String field) {
		this.name = name;
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public void sendSquareDetails(Player player) {
		
	}

	public boolean isHasHotel() {
		return hasHotel;
	}

	public void setHasHotel(boolean hasHotel) {
		this.hasHotel = hasHotel;
	}

	public int getNumberOfHouses() {
		return numberOfHouses;
	}

	public void setNumberOfHouses(int numberOfHouses) {
		this.numberOfHouses = numberOfHouses;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
