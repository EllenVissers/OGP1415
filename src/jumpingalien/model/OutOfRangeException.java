package jumpingalien.model;

/**
 * An exception that is thrown when a given value does not lie within the given range between low and high.
 * @author Ellen Vissers, Nina Versin
 * @version	1.0
 */
public class OutOfRangeException extends Exception {

	private static final long serialVersionUID = 111601815794403609L;
	
	private final int low;
	private final int high;
	private int wrong;
	
	public OutOfRangeException(int wrong, int low, int high) {
		this.wrong = wrong;
		this.low = low;
		this.high = high;
	}
	
	public int getLow() {
		return low;
	}
	
	public int getHigh() {
		return high;
	}
	
	public int getWrong() {
		return wrong;
	}
	
}
