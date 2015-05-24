package jumpingalien.model.tests;
import jumpingalien.model.*;
import static org.junit.Assert.*;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.part2.internal.Resources;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameObjectTest {
	
	private static double delta = 1e-3;
	private static Plant g1, g4, g7;
	private static Shark g2, g5;
	private static Slime g3, g6;
	private static Mazub g8;
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
		g1 = new Plant(49, 49, s1 ,null);
		g2 = new Shark(580, 49, s2, null);
		g3 = new Slime(1200, 49,s3,school,null);
		g4 = new Plant(413, 49, s1,null);
		g5 = new Shark(864, 49, s2,null);
		g6 = new Slime(864, 162, s3, school,null);
		g7 = new Plant(49, 49, s1,null);
		g8 = new Mazub(469,49,1.0,4.3,0.9,-10,Orientation.RIGHT,Resources.ALIEN_SPRITESET,150,w2,0,1.0,3.0,false,true,0.6,false);
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
		assertEquals(-0.5,g1.getXVelocity(),delta);
		assertEquals(0,g1.getYVelocity(),delta);
		assertEquals(0,g1.getXAcc(),delta);
		assertEquals(0,g1.getYAcc(),delta);
		assertTrue(Orientation.LEFT == g1.getOrientation());
		assertEquals(1,g1.getHitPoints());
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
		assertEquals(-0.5,g4.getXVelocity(),delta);
		assertEquals(1,g8.getXVelocity(),delta);
	}

	@Test
	public void getYVelocity_SingleCase() {
		assertEquals(0,g5.getYVelocity(),delta);
		assertEquals(4.3,g8.getYVelocity(),delta);
	}

	@Test
	public void getXAcc_SingleCase() {
		assertEquals(0,g4.getXAcc(),delta);
		assertEquals(-0.7,g6.getXAcc(),delta);
	}

	@Test
	public void getYAcc_SingleCase() {
		assertEquals(0,g1.getYAcc(),delta);
		assertEquals(-10,g8.getYAcc(),delta);
	}

	@Test
	public void getSprites_SingleCase() {
		assertTrue(s2 == g2.getSprites());
		assertTrue(s1 == g4.getSprites());
	}

	@Test
	public void getOrientation_SingleCase() {
		assertTrue(Orientation.RIGHT == g8.getOrientation());
		assertTrue(Orientation.LEFT == g5.getOrientation());
	}

	@Test
	public void getHitPoints_SingleCase() {
		assertEquals(100,g5.getHitPoints());
		assertEquals(1,g7.getHitPoints());
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
		assertEquals(g4.getCurrentSprite().getHeight(),g4.getYSize());
		assertEquals(g6.getCurrentSprite().getHeight(),g6.getYSize());
	}
	
	@Test
	public void getTerminatedTime_SingleCase() {
		assertEquals(0,g4.getTerminatedTime(),delta);
		assertEquals(0.0,g8.getTerminatedTime(),delta);
	}
	
	@Test
	public void IsTerminated_SingleCase() {
		assertFalse(g7.isTerminated());
		assertFalse(g1.isTerminated());
	}

	@Test
	public void getCurrentSprite_Zero() {
		assertTrue(g3.getSprites()[0] == g3.getCurrentSprite());
		assertTrue(g5.getSprites()[0] == g5.getCurrentSprite());
	}
	
	@Test
	public void getCurrentSprite_One() {
		for (int i = 0; i<5; i++)
			g4.advanceTime(0.1);
		assertTrue(g4.getSprites()[1] == g4.getCurrentSprite());
	}
}
