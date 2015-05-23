package jumpingalien.model.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Tile;
import jumpingalien.model.World;

import org.junit.BeforeClass;
import org.junit.Test;

public class TileTest {

	private static double delta = 1e-4;
	private static World w;
	private static Tile t1, t2, t3, t4, t5;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		w = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			w.setFeatureAt(i, 0, 1);
			w.setFeatureAt(i,39,1);
		}
		for (int i = 0; i < 40; i++) {
			w.setFeatureAt(0, i, 1);
			w.setFeatureAt(49, i, 1);
		}
		for (int i = 1; i < 49; i++)
			for (int j = 1; j < 39; j++)
				w.setFeatureAt(i,j,0);
		t1 = new Tile(0,0,w,1);
		t2 = new Tile(99,249,w,2);
		t3 = new Tile(749,149,w,0);
		t4 = new Tile(1249,699,w,3);
		t5 = new Tile(0,549,w,1);
	}

	@Test
	public void constructor() {
		assertEquals(t2.getXPosition(),99,delta);
		assertEquals(t2.getYPosition(),249,delta);
		assertTrue(t2.getWorld() == w);
		assertEquals(t2.getFeature(),2);
	}
	
	@Test
	public void getXPositionTest() {
		assertEquals(t1.getXPosition(),0,delta);
		assertEquals(t3.getXPosition(),749,delta);
	}

	@Test
	public void getYPositionTest() {
		assertEquals(t1.getYPosition(),0,delta);
		assertEquals(t3.getYPosition(),149,delta);
	}

	@Test
	public void getWorldTest() {
		assertTrue(t1.getWorld() == w);
		assertTrue(t3.getWorld() == w);
	}
	
	@Test
	public void getFeatureTest() {
		assertEquals(t4.getFeature(),3);
		assertEquals(t5.getFeature(),1);
	}
	
	@Test
	public void isPassableTest() {
		assertTrue(t3.isPassable());
		assertFalse(t1.isPassable());
	}

	@Test
	public void isMagmaTest() {
		assertTrue(t4.isMagma());
		assertFalse(t2.isMagma());
	}

	@Test
	public void isAirTest() {
		assertTrue(t3.isAir());
		assertFalse(t1.isAir());
	}

	@Test
	public void isWaterTest() {
		assertTrue(t2.isWater());
		assertFalse(t5.isWater());
	}

	@Test
	public void getWidthTest() {
		assertEquals(t4.getWidth(),50,delta);
	}

	@Test
	public void getHeightTest() {
		assertEquals(t5.getHeight(),50,delta);
	}

	



}
