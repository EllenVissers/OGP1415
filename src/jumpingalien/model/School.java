package jumpingalien.model;

import jumpingalien.model.Slime;
import java.util.ArrayList;
import java.util.List;
//import java.lang.Iterable;

public class School {

	public School(List<Slime> slimes) {
		this.slimes = slimes;
	}
	
	public School() {
		this(new ArrayList<Slime>());
	}
	
	private List<Slime> slimes;
	
	public List<Slime> getSlimes() {
		return this.slimes;
	}
	
	private void setSlimes(List<Slime> slimes) {
		this.slimes = slimes;
	}
	
	private void addSlime(Slime slime) {
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
	
	private void removeSlime(Slime slime) {
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
