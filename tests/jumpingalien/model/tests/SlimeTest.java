package jumpingalien.model.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import jumpingalien.model.*;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Nina
 *
 */
public class SlimeTest {
	
	private static Slime s1,s2,s3,s4,s5,s6,s7,s8,s9,s10;
	private static Sprite[] s;
	private static double delta = 1e-3;
	private static School sc1,sc2,sc3;
	private static ArrayList<Slime> a;
	private static ArrayList<Slime> b;
	private static ArrayList<Slime> c;
	private static World w1;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		w1 = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 40; i++) {
			w1.setFeatureAt(i, 0, 1);
			w1.setFeatureAt(i,25,1);
		}
		for (int i = 0; i < 25; i++) {
			w1.setFeatureAt(0, i, 1);
			w1.setFeatureAt(40, i, 1);
		}
		s = new Sprite[] { Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT };
		a = new ArrayList<Slime>(); 
		b = new ArrayList<Slime>();
		c = new ArrayList<Slime>();
		sc1 = new School();
		sc2 = new School();
		sc3 = new School();
		s1 = new Slime(10,20,s,sc1);
		s2 = new Slime(0,50,s,sc2);
		s3 = new Slime(6,0,s,sc3);
		s4 = new Slime(15,60,s,sc1);
		s5 = new Slime(110,35,s,sc2);
		s6 = new Slime(100,200,s,sc3);
		s7 = new Slime(100,49,s,sc1);
		s8 = new Slime(0,120,s,sc2);
		s9 = new Slime(40,30,s,sc3);
		s10 = new Slime(5,85,s,sc1);
		a.add(s1);
		a.add(s4);
		a.add(s7);
		a.add(s10);
		b.add(s2);
		b.add(s5);
		b.add(s8);
		c.add(s3);
		c.add(s6);
		c.add(s9);
		w1.addSlime(s7);
		w1.addSlime(s6);
	}

	@Test
	public void Costructor_LegalCase() {
		assertEquals(10,s1.getXPosition(),delta);
		assertEquals(50,s2.getYPosition(),delta);
		assertTrue(s == s3.getSprites());
		assertEquals(sc1,s1.getSchool());
		assertEquals(0,s5.getXVelocity(),delta);
		assertEquals(0,s5.getYVelocity(),delta);
		assertEquals(-0.7,s6.getXAcc(),delta);
		assertEquals(0,s6.getYAcc(),delta);
		assertEquals(Orientation.LEFT,s2.getOrientation());
		assertEquals(98,s2.getHitPoints());
		assertEquals(null,s9.getWorld());
		assertFalse(s10.isTerminated());
		
		
	}
	
	@Test (expected = ModelException.class)
	public void Constructor_IllegalCase() {
		new Slime(90,20,null,null);
	}
	
	@Test
	public void getSchool_SingleCase() {
		assertEquals(sc1,s1.getSchool());
		assertEquals(sc2,s2.getSchool());
		assertEquals(sc3,s3.getSchool());
	}
	
	@Test(expected = ModelException.class)
	public final void advanceTime_InvalidTime() throws ModelException {
		s10.advanceTime(0.5);
		s6.advanceTime(-4.3);
	}
	
	
	@Test
	public void advanceTime_Moving() {
		s7.advanceTime(0.1);
		assertEquals(0.0,s7.getYVelocity(),delta);
		assertEquals(99.65,s7.getXPosition(),delta);
		assertEquals(-0.07,s7.getXVelocity(),delta);
		assertEquals(49,s7.getYPosition(),delta);
		
	}
	
	@Test
	public void advanceTime_Falling() {
		s6.advanceTime(0.1);
		assertEquals(-0.07,s6.getXVelocity(),delta);
		assertEquals(99.65,s6.getXPosition(),delta);
		assertEquals(-1.0,s6.getYVelocity(),delta);
		assertEquals(195,s6.getYPosition(),delta);
		
	}

}

