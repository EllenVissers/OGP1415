package jumpingalien.model;

import jumpingalien.model.Slime;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class of schools involving a number of slimes.
 * @invar A school is either empty or contains only valid slimes
 * 			| (getSize() == 0) || (for (Slime slime : school)
 * 			|							(slime != null))
 * 
 * @author Ellen Vissers, Nina Versin
 * @version 1.0
 */
public class School {
	
	/**
	 * Initialize a school with a given array of slimes.
	 * @param 	slimes
	 * 			The slimes in this school.
	 */
	public School(ArrayList<Slime> slimes) {
		this.setSlimes(slimes);
	}
	
	/**
	 * Initialize an empty school.
	 */
	public School() {
		this.slimes = new ArrayList<Slime>(World.maxNumberOfGameObjects);
	}
	
	/**
	 * Variable registering the slimes in this school.
	 */
	private ArrayList<Slime> slimes;
	
	/**
	 * Return an array of the slimes in this school.
	 * @return 	An array of slimes.
	 * 			| this.slimes
	 */
	public ArrayList<Slime> getSlimes() {
		return this.slimes;
	}
	
	/**
	 * Set the given array of slimes as the slimes of this school.
	 * @param 	slimes
	 * 			The given array of slimes.
	 */
	private void setSlimes(ArrayList<Slime> slimes) {
		for (Slime s : slimes)
			slimes.add(s);
	}
	
	/**
	 * Return the number of slimes in this school.
	 * @return	The size of this school.
	 * 			| getSlimes().size()
	 */
	protected int getSize() {
		return getSlimes().size();
	}
	
	/**
	 * Add a given slime to this school.
	 * @param 	slime
	 * 			The slime that has to be added.
	 * @post	The slime is added to the school.
	 * 			| new.getSlimes().contains(slime)
	 * @post	All of the other slimes in this school gave 1 hitpoint to the new slime.
	 * 			| new(slime).getHitPoints() == slime.getHitPoints() + this.getSize()
	 * 			| && for (Slime s : this.getSlimes())
				|        new(s).getHitPoints() = s.getHitPoints() - 1;
	 */
	@Raw
	protected void addSlime(Slime slime) {
		if ( ! (getSlimes().contains(slime)))
		{
			int length = getSize();
			slime.setHitPoints(slime.getHitPoints() + length);
			for (Slime s : getSlimes())
				s.setHitPoints(s.getHitPoints() - 1);
			getSlimes().add(slime);
			slime.setSchool(this);
		}
	}
	
	/**
	 * Remove a certain slime from this school.
	 * @param 	slime
	 * 			The slime that has to be removed.
	 * @post 	The slime is removed.
	 * 			|! new.getSlimes().contains(slime)
	 * @post	The slime gave one hitpoint to all of the other slimes in this school.
	 *  		| new(slime).getHitPoints() == slime.getHitPoints() - new.getSize()
	 * 			| && for (Slime s : this.getSlimes())
				|        new(s).getHitPoints() = s.getHitPoints() + 1;
	 */
	protected void removeSlime(Slime slime) {
		if (this.getSlimes().contains(slime))
		{
			getSlimes().remove(slime);
			int length = getSlimes().size();
			for (Slime s : getSlimes()) {
				s.setHitPoints(s.getHitPoints() + 1);
			}
			slime.setHitPoints(slime.getHitPoints() - length);
		}
	}	
}
