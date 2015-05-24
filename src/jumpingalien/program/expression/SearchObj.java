package jumpingalien.program.expression;
import java.util.ArrayList;
import java.util.Map;
import jumpingalien.program.type.Type;
import jumpingalien.model.AllObjects;
import jumpingalien.model.GameObject;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.program.type.ObjectType;

@SuppressWarnings(value="all")
public class SearchObj extends Expression {

	public SearchObj(SourceLocation loc, Expression dir) {
		super(loc);
		this.direction = dir;
	}
	
	private Expression direction;
	
	public Expression getDirection() {
		return this.direction;
	}
	
	@Override
	public AllObjects evaluate(Map<String,Type> globals) {
		GameObject obj = (GameObject) ((ObjectType)globals.get("this")).getValue();
		World world = obj.getWorld();
		double xmin = obj.getXPosition();
		double xmax = xmin + obj.getCurrentSprite().getWidth()-1;
		double ymin = obj.getYPosition();
		double ymax = ymin + obj.getCurrentSprite().getHeight()-1;
		ArrayList<GameObject> all = world.getAllGameObjects();
		IProgramFactory.Direction dir = (Direction) getDirection().evaluate(globals);
		if (dir == IProgramFactory.Direction.RIGHT)
		{
			for (int i=1; i<=(world.getWorldWidth()-1-xmax); i++)
			{
				for (GameObject other : all)
				{
					double oymin = other.getYPosition();
					double oymax = other.getYPosition()+other.getCurrentSprite().getHeight()-1;
					if ((other.getXPosition() == xmin+i) && (((oymin>=ymin) && (oymin<=ymax))
							||((oymax>=ymin) && (oymax<=ymax)) ||((ymin>=oymin) && (ymax<=oymax)) ||((ymax>=oymin) && (ymax<=oymax))))
						return other;
				}
			}
		}
		if (dir == IProgramFactory.Direction.LEFT)
		{
			for (int i=1; i<=xmin; i++)
			{
				for (GameObject other : all)
				{
					double oymin = other.getYPosition();
					double oymax = other.getYPosition()+other.getCurrentSprite().getHeight()-1;
					if ((other.getXPosition() == xmin-i) && (((oymin>=ymin) && (oymin<=ymax))
							||((oymax>=ymin) && (oymax<=ymax)) ||((ymin>=oymin) && (ymax<=oymax)) ||((ymax>=oymin) && (ymax<=oymax))))
						return other;
				}
			}
		}
		if (dir == IProgramFactory.Direction.UP)
		{
			for (int i=1; i<=(world.getWorldHeight()-1-ymax); i++)
			{
				for (GameObject other : all)
				{
					double oxmin = other.getXPosition();
					double oxmax = other.getXPosition()+other.getCurrentSprite().getWidth()-1;
					if ((other.getYPosition() == ymin+i) && (((oxmin>=xmin) && (oxmin<=xmax))
							||((oxmax>=xmin) && (oxmax<=xmax)) ||((xmin>=oxmin) && (xmax<=oxmax)) ||((xmax>=oxmin) && (xmax<=oxmax))))
						return other;
				}
			}
		}
		if (dir == IProgramFactory.Direction.DOWN)
		{
			for (int i=1; i<=ymin; i++)
			{
				for (GameObject other : all)
				{
					double oxmin = other.getXPosition();
					double oxmax = other.getXPosition()+other.getCurrentSprite().getWidth()-1;
					if ((other.getXPosition() == ymin-i) && (((oxmin>=xmin) && (oxmin<=xmax))
							||((oxmax>=xmin) && (oxmax<=xmax)) ||((xmin>=oxmin) && (xmax<=oxmax)) ||((xmax>=oxmin) && (xmax<=oxmax))))
						return other;
				}
			}
		}
		return null;
	}
	
}
