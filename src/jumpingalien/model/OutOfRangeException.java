package jumpingalien.model;


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
