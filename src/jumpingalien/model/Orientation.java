package jumpingalien.model;

/**
 * A class of ...
 * @author Ellen Vissers
 * @version 1.0 
 */
public enum Orientation {

	RIGHT {
		public char getSymbol() {
			return 'R';
			// Ook kunnen de bekende symbolen gebruikt worden, zie modeloplossing
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
	
	// aankondingen dat we deze methode definiëren voor onze objecten
	public abstract char getSymbol();
}
