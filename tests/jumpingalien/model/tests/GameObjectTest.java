package jumpingalien.model.tests;
import jumpingalien.model.Shark;
import jumpingalien.model.Plant;
import jumpingalien.model.Slime;
import static org.junit.Assert.*;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.model.World;
import jumpingalien.model.Orientation;
import jumpingalien.model.School;
import jumpingalien.part2.internal.Resources;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameObjectTest {
	
	private static double delta = 1e-3;
	private static Plant g1, g4, g7;
	private static Shark g2, g5;
	private static Slime g3, g6;
	private static World w1, w2;
	private static Sprite[] s1, s2, s3;
	private static School school;

	@BeforeClass
	public static void setUpBeforeClass() {
		w1 = new World(15,200,75,750,375,185,60);
		w2 = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			w2.setFeatureAt(i, 0, 1);
			w2.setFeatureAt(i,39,1);
		}
		for (int i = 0; i < 40; i++) {
			w2.setFeatureAt(0, i, 1);
			w2.setFeatureAt(49, i, 1);
		}
		
		
		s1 = new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT };
		s2 = new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT };
		s3 = new Sprite[] { Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT };
		school = new School();
		
		//double x, y, vx, vy, ax, ay, Orientation, sprites, hitPoints, world, terminated, terminatedTime, program
		g1 = new Plant(49, 49, s2 ,null);
		g2 = new Shark(580, 49, s2, null);
		g3 = new Slime(1200, 49,s3,school,null);
		g4 = new Plant(413, 49, s1,null);
		g5 = new Shark(864, 49, s2,null);
		g6 = new Slime(864, 162, s3, school,null);
		g7 = new Plant(49, 49, s1,null);
		w2.addPlant(g7);
		w2.addPlant(g4);
		w2.addPlant(g1);
		w2.addShark(g5);
		w2.addShark(g2);
		w2.addSlime(g3);
		w1.addSlime(g6);
	}

	@Test
	public void Constructor_LegalCase() {
		assertEquals(49,g1.getXPosition(),delta);
		assertEquals(49,g1.getYPosition(),delta);
		assertEquals(0,g1.getXVelocity(),delta);
		assertEquals(0,g1.getYVelocity(),delta);
		assertEquals(0,g1.getXAcc(),delta);
		assertEquals(0,g1.getYAcc(),delta);
		assertTrue(Orientation.NONE == g1.getOrientation());
		assertEquals(100,g1.getHitPoints());
		assertTrue(w2 == g1.getWorld());
		assertFalse(g1.isTerminated());
		assertTrue(s1 == g1.getSprites());
		assertEquals(0,g1.getTerminatedTime(),delta);
	}
	
	@Test(expected = ModelException.class)
	public void Constructor_IllegalCase() {
		new Plant(80,65,null,null);
	}

	@Test
	public void getXPosition_SingleCase() {
		assertEquals(49,g1.getXPosition(),delta);
		assertEquals(1200,g3.getXPosition(),delta);
	}

	@Test
	public void getYPosition_SingleCase() {
		assertEquals(49,g2.getYPosition(),delta);
		assertEquals(162,g6.getYPosition(),delta);
	}

	@Test
	public void getXVelocity_SingleCase() {
		assertEquals(1.6,g2.getXVelocity(),delta);
		assertEquals(-2.14,g3.getXVelocity(),delta);
	}

	@Test
	public void getYVelocity_SingleCase() {
		assertEquals(0,g5.getYVelocity(),delta);
		assertEquals(5.3,g6.getYVelocity(),delta);
	}

	@Test
	public void getXAcc_SingleCase() {
		assertEquals(0.9,g4.getXAcc(),delta);
		assertEquals(-0.9,g5.getXAcc(),delta);
	}

	@Test
	public void getYAcc_SingleCase() {
		assertEquals(0,g1.getYAcc(),delta);
		assertEquals(-10,g6.getYAcc(),delta);
	}

	@Test
	public void getSprites_SingleCase() {
		assertTrue(s2 == g2.getSprites());
		assertTrue(s1 == g4.getSprites());
	}

	@Test
	public void getOrientation_SingleCase() {
		assertTrue(Orientation.RIGHT == g2.getOrientation());
		assertTrue(Orientation.LEFT == g5.getOrientation());
	}

	@Test
	public void getHitPoints_SingleCase() {
		assertEquals(170,g3.getHitPoints());
		assertEquals(40,g5.getHitPoints());
	}

	@Test
	public void getWorld_SingleCase() {
		assertTrue(w2 == g4.getWorld());
		assertTrue(w1 == g6.getWorld());
	}
	@Test
	public void getXSize_SingleCase() {
		assertEquals(g3.getCurrentSprite().getWidth(),g3.getXSize());
		assertEquals(g5.getCurrentSprite().getWidth(),g5.getXSize());
	}

	@Test
	public void getYSize_SingleCase() {
		assertEquals(g4.getCurrentSprite().getHeight(),g4.getXSize());
		assertEquals(g6.getCurrentSprite().getHeight(),g6.getXSize());
	}
	
	@Test
	public void getTerminatedTime_SingleCase() {
		assertEquals(0,g4.getTerminatedTime(),delta);
		assertEquals(0.8,g7.getTerminatedTime(),delta);
	}
	
	@Test
	public void IsTerminated_SingleCase() {
		assertTrue(g7.isTerminated());
		assertFalse(g1.isTerminated());
	}

	@Test
	public void getCurrentSprite_Zero() {
		assertTrue(g3.getSprites()[0] == g3.getCurrentSprite());
		assertTrue(g5.getSprites()[0] == g5.getCurrentSprite());
	}
	
	@Test
	public void getCurrentSprite_One() {
		assertTrue(g2.getSprites()[1] == g2.getCurrentSprite());
		assertTrue(g4.getSprites()[1] == g4.getCurrentSprite());
	}
}
