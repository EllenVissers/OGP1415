package jumpingalien.model.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Buzam;
import jumpingalien.model.Orientation;
import jumpingalien.model.World;
import jumpingalien.part3.internal.Resources;
import jumpingalien.program.program.Program;
import jumpingalien.util.Sprite;

import org.junit.BeforeClass;
import org.junit.Test;

public class BuzamTest {

	private static double delta = 1e-4;
	private static Buzam b1, b2, b3;
	private static Program p;
	private static Sprite[] spr;
	private static World w;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spr = Resources.BUZAM_SPRITESET;
		w = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			w.setFeatureAt(i, 0, 1);
			w.setFeatureAt(i,39,1);
		}
		for (int i = 0; i < 40; i++) {
			w.setFeatureAt(0, i, 1);
			w.setFeatureAt(49, i, 1);
		}
		b1 = new Buzam(574,49,spr);
		b2 = new Buzam(423,49,spr,p);
		b3 = new Buzam(387,49,spr);
		w.addBuzam(b3);
	}

	@Test
	public void constructorWithoutProgram() {
		assertEquals(b1.getXPosition(),574,delta);
		assertEquals(b1.getYPosition(),49,delta);
		assertTrue(b1.getSprites() == spr);
		assertNull(b1.getProgram());
	}

	@Test
	public void constructorWithProgram() {
		assertEquals(b2.getXPosition(),423,delta);
		assertEquals(b2.getYPosition(),49,delta);
		assertTrue(b2.getSprites() == spr);
		assertTrue(b2.getProgram() == p);
	}

	@Test
	public void advanceTimeTest() {
		assertEquals(b3.getXPosition(),387,delta);
		assertEquals(b3.getYPosition(),49,delta);
		assertEquals(b3.getXVelocity(),0,delta);
		assertEquals(b3.getYVelocity(),0,delta);
		assertEquals(b3.getXAcc(),0,delta);
		assertEquals(b3.getYAcc(),0,delta);
		assertTrue(b3.getOrientation() == Orientation.NONE);
		assertEquals(b3.getHitPoints(),500);
		b3.startMove(Orientation.RIGHT);
		b3.startJump();
		b3.advanceTime(0.1);
		assertEquals(b3.getXPosition(),397.45,delta);
		assertEquals(b3.getYPosition(),124,delta);
		assertEquals(b3.getXVelocity(),1.09,delta);
		assertEquals(b3.getYVelocity(),7,delta);
		assertEquals(b3.getXAcc(),0.9,delta);
		assertEquals(b3.getYAcc(),-10,delta);
		assertTrue(b3.getOrientation() == Orientation.RIGHT);
		assertEquals(b3.getHitPoints(),500);
		
	}

}
