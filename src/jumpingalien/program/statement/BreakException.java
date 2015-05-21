package jumpingalien.program.statement;

@SuppressWarnings("all")
public class BreakException extends Exception {
	
	public BreakException(double time) {
		this.time = time;
	}
	
	private double time;
	
	public double getTime() {
		return this.time;
	}
	
}
