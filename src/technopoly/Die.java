/**
 * 
 */
package technopoly;
//using this instead of Math.Random because it's threadsafe
import java.util.Random;
/**
 * @author Luke
 *
 */
public class Die {
	private int rollNumber;
	Random random;
	
	public int rollDie() {
		random = new Random();
		rollNumber = (random.nextInt(6) + 1);
		return rollNumber;
	}
	

}
