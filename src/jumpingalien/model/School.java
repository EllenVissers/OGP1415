package jumpingalien.model;

import jumpingalien.model.Slime;
import java.util.ArrayList;

public class School {

	public School(ArrayList<Slime> slimes) {
		this.setSlimes(slimes);
	}
	
	public School() {
		this(new ArrayList<Slime>());
	}
	
	private ArrayList<Slime> slimes;
	
	public ArrayList<Slime> getSlimes() {
		return this.slimes;
	}
	
	private void setSlimes(ArrayList<Slime> slimes) {
		this.slimes = slimes;
	}
	
	public int getSize() {
		return getSlimes().size();
	}
	
	public void addSlime(Slime slime) {
		//if ( ( ! (this.getSlimes().contains(slime))) && (slime.getSchool() == null) )
		if ( ! (this.getSlimes().contains(slime)))
		{
			int length = this.getSlimes().size();
			slime.setHitPoints(slime.getHitPoints() + length);
			for (Slime s : this.getSlimes())
				s.setHitPoints(s.getHitPoints() - 1);
			this.getSlimes().add(slime);
			slime.setSchool(this);
		}
	}
	
	public void removeSlime(Slime slime) {
		if (this.getSlimes().contains(slime))
		{
			this.getSlimes().remove(slime);
			slime.setSchool(null);
			int length = this.getSlimes().size();
			for (Slime s : this.getSlimes()) {
				s.setHitPoints(s.getHitPoints() + 1);
			}
			slime.setHitPoints(slime.getHitPoints() - length);
		}
	}
	
}
