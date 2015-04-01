package jumpingalien.model;

import jumpingalien.model.GameObject;

/**
 * An exception that is thrown when a game object collides with another game object.
 * @author Ellen Vissers, Nina Versin
 * @version	2.0
 */
public class CollisionException extends Exception {

	private static final long serialVersionUID = 113601848752683609L;
	
	private GameObject object;
	private Orientation orientation;
	private boolean vertical;
	
	public CollisionException(GameObject object, Orientation orientation) {
		this.object = object;
		this.orientation = orientation;
	}
	
	public CollisionException(GameObject object, boolean vertical) {
		this.object = object;
		this.vertical = vertical;
	}
	
	public GameObject getObject() {
		return this.object;
	}
	
	public Orientation getOrientation() {
		return this.orientation;
	}
	
	public boolean getVertical() {
		return this.vertical;
	}
	
}
