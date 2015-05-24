package jumpingalien.program.tests;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.*;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.type.*;
import org.junit.BeforeClass;
import org.junit.Test;
import jumpingalien.part2.internal.Resources;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.util.Sprite;

public class TypeTest {

	private static double delta = 1e-3;
	private static DoubleType dt1, dt2, dt3;
	private static BoolType bt1, bt2, bt3;
	private static DirectionType drt1, drt2, drt3;
	private static ObjectType ot1, ot2, ot3, ot4, ot5, ot6;
	private static GameObject o1, o2, o3, o4, o5, o6;
	private static World w1, w2;
	private static Sprite[] s1, s2, s3, s4, s5;
	private static School sh;
	private static SourceLocation sl;
	private static Map<String,Type> globals;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		w1 = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 40; j++) {
				w1.setFeatureAt(i, j, 2);
				w1.setFeatureAt(i,j,2);
			}
		}
		w2 = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 40; j++) {
				w2.setFeatureAt(i, j, 0);
				w2.setFeatureAt(i,j,0);
			}
		}
		s1 = new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT };
		s2 = new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT };
		s3 = new Sprite[] { Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT };
		s4 = JumpingAlienSprites.ALIEN_SPRITESET;
		s5 = Resources.ALIEN_SPRITESET;
		sh = new School();
		o1 = new Plant(12,34,s1);
		o2 = new Slime(38,11,s3,sh);
		o3 = new Shark(47,26,s2);
		o4 = new Buzam(23,9,s5);
		o5 = new Mazub(32,31,s4);
		o6 = new Plant(29,17,s1);
		w1.addShark((Shark) o3);
		w1.addPlant((Plant) o1);
		w2.addSlime((Slime) o2);
		w2.setMazub((Mazub) o5);
		w2.addBuzam((Buzam) o4);
		globals = new HashMap<String,Type>();
		sl = new SourceLocation(6,8);
		
		dt1 = new DoubleType();
		dt2 = new DoubleType(25.0);
		dt3 = new DoubleType();
		bt1 = new BoolType();
		bt2 = new BoolType(false);
		bt3 = new BoolType();
		drt1 = new DirectionType();
		drt2 = new DirectionType(Direction.UP);
		drt3 = new DirectionType();
		ot1 = new ObjectType();
		ot2 = new ObjectType(o1);
		ot3 = new ObjectType(o2);
		ot4 = new ObjectType(o3);
		ot5 = new ObjectType(o4);
		ot6 = new ObjectType(o5);
	}

	@Test
	public void Constructor_LegalCase() {
		assertEquals(dt1.getValue(),0,delta);
		assertTrue(bt1.getValue());
		assertTrue(drt1.getValue() == Direction.RIGHT);
		assertNull(ot1.getValue());
	}
	
	@Test
	public void getValueDoubleTypeTest() {
		assertEquals(dt2.getValue(),25,delta);
	}
	
	@Test
	public void getValueBoolTypeTest() {
		assertFalse(bt2.getValue());
	}
	
	@Test
	public void getValueDirectionTypeTest() {
		assertTrue(drt2.getValue() == Direction.UP);
	}
	
	@Test
	public void getValueObjectTypeTest() {
		assertTrue(ot2.getValue() instanceof Plant);
		assertTrue(ot3.getValue() instanceof Slime);
		assertTrue(ot4.getValue() instanceof Shark);
		assertTrue(ot5.getValue() instanceof Buzam);
	}
	
	@Test
	public void setDoubleTypeTest() {
		dt3.set(new Constant<Double>(sl,35.0),globals);
		assertEquals(dt3.getValue(),35,delta);
	}
	
	@Test
	public void setBoolTypeTest() {
		bt3.set(new Constant<Boolean>(sl,false),globals);
		assertFalse(bt3.getValue());
	}
	
	@Test
	public void setDirectionTypeTest() {
		drt3.set(new Constant<Direction>(sl,Direction.DOWN),globals);
		assertTrue(drt3.getValue() == Direction.DOWN);
	}
	
	@Test
	public void setObjectTypeTest() {
		ot6.set(new Constant<AllObjects>(sl,o6),globals);
		assertTrue(ot6.getValue() instanceof Plant);
	}

}
