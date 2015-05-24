package jumpingalien.model;
import jumpingalien.model.GameObject;

/**
 * An exception that is thrown when a game object collides with another game object.
 * @author Ellen Vissers, Nina Versin
 * @version	2.0
 */
@SuppressWarnings("all")
public class CollisionException extends Exception {
	
	private GameObject object;
	private Orientation orientation;
	private boolean vertical;
	private boolean collided;
	
	public CollisionException(GameObject object, Orientation orientation, boolean collided) {
		this.object = object;
		this.orientation = orientation;
		this.collided = collided;
	}
	
	public CollisionException(GameObject object, boolean vertical, boolean collided) {
		this.object = object;
		this.vertical = vertical;
		this.collided = collided;
	}
	
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
	
	public boolean getCollided() {
		return this.collided;
	}
	
}
