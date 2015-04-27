package jumpingalien.model.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Shark;
import jumpingalien.model.Orientation;
import jumpingalien.model.World;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.Sprite;

import org.junit.BeforeClass;
import org.junit.Test;

public class SharkTest {

	private static double delta = 1e-3;
	private static Shark s1, s2;
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
		for (int i = 1; i < 39; i++)
			for (int j = 1; j < 49; j++)
				w1.setFeatureAt(i, j, 2);
		
		s = new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT };
		
		s1 = new Shark(49, 49, s);
		s2 = new Shark(580, 547, s);
		w1.addShark(s1);
		w1.addShark(s2);
	}

	@Test
	public void Constructor_LegalCase() {
		assertEquals(49,s1.getXPosition(),delta);
		assertEquals(49,s1.getYPosition(),delta);
		assertEquals(0,s1.getXVelocity(),delta);
		assertEquals(0,s1.getYVelocity(),delta);
		assertEquals(-Shark.accx,s1.getXAcc(),delta);
		assertEquals(0,s1.getYAcc(),delta);
		assertTrue(Orientation.LEFT == s1.getOrientation());
		assertEquals(100,s1.getHitPoints());
		assertTrue(w1 == s1.getWorld());
		assertFalse(s1.isTerminated());
		assertTrue(s == s1.getSprites());
		assertEquals(0,s1.getTerminatedTime(),delta);
	}

	@Test
	public void advanceTime_SingleCase() {
		double oldx = s2.getXPosition();
		double oldy = s2.getYPosition();
		double oldvx = s2.getXVelocity();
		double oldvy = s2.getYVelocity();
		s2.advanceTime(0.15);
		assertTrue(oldx != s2.getXPosition());
		assertTrue(oldvx != s2.getXVelocity());
		for (int i = 1; i<15; i++)
			s2.advanceTime(0.15);
		assertTrue(oldx != s2.getXPosition());
		assertTrue(oldy != s2.getYPosition());
		assertTrue(oldvx != s2.getXVelocity());
		assertTrue(oldvy != s2.getYVelocity());
	}

}
