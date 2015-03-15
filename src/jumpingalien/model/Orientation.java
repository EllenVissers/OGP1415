package jumpingalien.model;

/**
 * A class of orientation in which a Mazub can be oriented.
 * @author Ellen Vissers, Nina Versin
 * @version 1.0 
 */
public enum Orientation {

	RIGHT {
		public char getSymbol() {
			return 'R';
		}
	},
	LEFT {
		public char getSymbol() {
			return 'L';
		}
	},
	NONE {
		public char getSymbol() {
			return 'N';
		}
	};
	
	public abstract char getSymbol();
}
