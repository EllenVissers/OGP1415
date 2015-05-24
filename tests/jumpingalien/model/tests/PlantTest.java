package jumpingalien.model.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Plant;
import jumpingalien.model.Orientation;
import jumpingalien.model.World;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.Sprite;

import org.junit.BeforeClass;
import org.junit.Test;

public class PlantTest {

	private static double delta = 1e-3;
	private static Plant p1, p2, p3;
	private static World w1;
	private static Sprite[] s;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		w1 = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			w1.setFeatureAt(i, 0, 1);
			w1.setFeatureAt(i,39,1);
		}
		for (int i = 0; i < 40; i++) {
			w1.setFeatureAt(0, i, 1);
			w1.setFeatureAt(49, i, 1);
		}
		
		s = new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT };
		
		p1 = new Plant(125,86,s);
		p2 = new Plant(641,247,s);
		p3 = new Plant(1547,138,s);
		w1.addPlant(p1);
		w1.addPlant(p2);
		w1.addPlant(p3);
	}

	@Test
	public void Constructor_SingleCase() {
		assertEquals(125,p1.getXPosition(),delta);
		assertEquals(86,p1.getYPosition(),delta);
		assertEquals(-Plant.speed,p1.getXVelocity(),delta);
		assertEquals(0,p1.getYVelocity(),delta);
		assertEquals(0,p1.getXAcc(),delta);
		assertEquals(0,p1.getYAcc(),delta);
		assertTrue(Orientation.LEFT == p1.getOrientation());
		assertEquals(1,p1.getHitPoints());
		assertTrue(w1 == p1.getWorld());
		assertFalse(p1.isTerminated());
		assertTrue(s == p1.getSprites());
		assertEquals(0,p1.getTerminatedTime(),delta);
	}

	//641,247
	//1547,138
	@Test
	public void advanceTime_SingleCase() {
		p2.advanceTime(0.1);
		assertEquals(636,p2.getXPosition(),delta);
		assertEquals(247,p2.getYPosition(),delta);
		assertEquals(-Plant.speed,p2.getXVelocity(),delta);
		assertEquals(0,p2.getYVelocity(),delta);
		assertEquals(0,p2.getXAcc(),delta);
		assertEquals(0,p2.getYAcc(),delta);
		assertTrue(Orientation.LEFT == p2.getOrientation());
		for (int i = 0; i<5; i++)
			p3.advanceTime(0.1);
		assertEquals(1542,p3.getXPosition(),delta);
		assertEquals(138,p3.getYPosition(),delta);
		assertEquals(Plant.speed,p3.getXVelocity(),delta);
		assertEquals(0,p3.getYVelocity(),delta);
		assertEquals(0,p3.getXAcc(),delta);
		assertEquals(0,p3.getYAcc(),delta);
		assertTrue(Orientation.RIGHT == p3.getOrientation());
	}
	
}
