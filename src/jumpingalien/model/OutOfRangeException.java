package jumpingalien.model;

/**
 * An exception that is thrown when a given value does not lie within the given range between low and high.
 * @author Ellen Vissers, Nina Versin
 * @version	2.0
 */
public class OutOfRangeException extends Exception {

	private static final long serialVersionUID = 111601815794403609L;
	
	private final double low;
	private final double high;
	private double wrong;
	
	public OutOfRangeException(double wrong, double low, double high) {
		this.wrong = wrong;
		this.low = low;
		this.high = high;
	}
	
	public double getLow() {
		return low;
	}
	
	public double getHigh() {
		return high;
	}
	
	public double getWrong() {
		return wrong;
	}
	
}
