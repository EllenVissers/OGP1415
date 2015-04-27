package jumpingalien.model.tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part2.internal.Resources;
import jumpingalien.util.Sprite;

/**
 * A test class for World.
 * @author Nina Versin, Ellen Vissers
 */
public class WorldTest {
	
	private static World w1,w2,w3,w4,w5,w6,w7,w8,w9,w10,w11,w12,w13,w14,w15,w16,w17,w18;
	private static int x1,x2,y1,y2;
	private static Slime s1;
	private static Plant p1,p2;
	private static Mazub m1,m2;
	private static Shark sh1;
	private static double delta = 1e-3;
	private static Sprite[] sp1,sp2,sp3;
	private static ArrayList<Slime> a;
	private static School school;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		w1 = new World(50,40,25,500,175,35,21);
		w2 = new World(32,100,45,860,320,130,90);
		w3 = new World(40,350,80,550,175,80,80);
		w4 = new World(5,210,60,900,200,190,20);
		w5 = new World(60,800,100,750,500,220,90);
		w6 = new World(10,150,60,600,275,185,35);
		w7 = new World(80,215,90,780,425,250,80);
		w8 = new World(67,845,35,870,480,210,85);
		w9 = new World(24,310,25,700,415,200,55);
		w10 = new World(45,300,85,280,580,185,35);
		w11 = new World(20,410,75,750,375,150,60);
		w12 = new World(50,60,65,760,300,190,10);
		w13 = new World(45,500,55,650,365,240,50);
		w14 = new World(70,300,35,150,125,310,70);
		w15 = new World(25,290,95,450,450,460,85);
		w16 = new World(30,200,80,340,250,350,55);
		w17 = new World(100,560,15,320,150,100,30);
		w18 = new World(35,100,65,350,400,380,60);
		for (int i = 0; i < 40; i++) {
			w1.setFeatureAt(i, 0, 1);
			w1.setFeatureAt(i,25,1);
		}
		for (int i = 0; i < 25; i++) {
			w1.setFeatureAt(0, i, 1);
			w1.setFeatureAt(40, i, 1);
		}
		
		x1 = 50;
		x2 = 125;
		y1 = 80;
		y2 = 45;
		
		a= new ArrayList<Slime>();
		a.add(s1);
		school = new School();
		
		sp1 = new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT };
		sp2 = new Sprite[] { Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT };
		sp3 = new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT };
		p1 = new Plant(10,0,sp1);
		p2 = new Plant(641,247,sp1);
		s1 = new Slime(120,40,sp2,school);
		sh1 = new Shark(30,10,sp3);
		m1 = new Mazub(50,0,JumpingAlienSprites.ALIEN_SPRITESET);
		m2 = new Mazub(20,40,JumpingAlienSprites.ALIEN_SPRITESET);
		w9.addSlime(s1);
		w9.addPlant(p2);
		w1.setMazub(m2);
	}

	private Object[] n;
	
	@Test
	public final void Constructor_LegalCase(){
		assertEquals(50,w1.getTileSize());
		assertEquals(40,w1.getNbTilesX());
		assertEquals(25,w1.getNbTilesY());
		assertEquals(500,w1.getVisibleWindowWidth());
		assertEquals(175,w1.getVisibleWindowHeight());
		assertEquals(35,w1.getTargetTileX());
		assertEquals(21,w1.getTargetTileY());	
	}
	
	@Test
	public final void getTileSize_SingleCase(){
		assertEquals(80,w7.getTileSize());
		assertEquals(20,w11.getTileSize());
		
	}
	
	@Test
	public final void getNbTilesX_SingleCase() {
		assertEquals(40,w1.getNbTilesX());
		assertEquals(60,w12.getNbTilesX());
		
	}
	
	@Test
	public final void getNbTilesY_SingleCase() {
		assertEquals(65,w18.getNbTilesY());
		assertEquals(95,w15.getNbTilesY());
	}
	
	@Test
	public final void getVisibleWindowdHeight_SingleCase() {
		assertEquals(320,w2.getVisibleWindowHeight());
		assertEquals(125,w14.getVisibleWindowHeight());
	}
	
	@Test
	public final void getVisibleWindowWidth_SingleCase() {
		assertEquals(550,w3.getVisibleWindowWidth());
		assertEquals(280,w10.getVisibleWindowWidth());
	}
	
	@Test
	public final void getWorldHeight_SingleCase() {
		assertEquals(2400,w16.getWorldHeight());
		assertEquals(2345,w8.getWorldHeight());
	}
	
	@Test
	public final void getWorldWidth_SingleCase() {
		assertEquals(13500,w10.getWorldWidth());
		assertEquals(1500,w6.getWorldWidth());
		
	}
	
	@Test
	public final void getTargetTileX_SingleCase() {
		assertEquals(220,w5.getTargetTileX());
		assertEquals(310,w14.getTargetTileX());
		
	}
	
	@Test
	public final void getTargetTileY_SingleCase() {
		assertEquals(80,w7.getTargetTileY());
		assertEquals(50,w13.getTargetTileY());
		
	}
	
	@Test
	public final void getTile_SingleCase() {
		assertEquals(5,w6.getTile(x1,y1)[0]);
		assertEquals(8,w6.getTile(x1,y1)[1]);
		assertEquals(1,w14.getTile(x2,y2)[0]);
		assertEquals(0,w14.getTile(x2,y2)[1]);
		
	}
	
	@Test
	public final void getWon_FalseCase() {
		assertFalse(w6.getWon());
		assertFalse(w14.getWon());
		
	}
	
	
	@Test
	public final void getGameOver_FalseCase() {
		assertFalse(w18.getGameOver());
		assertFalse(w8.getGameOver());
		
	}
	
	
	@Test
	public final void getFeatureAt_SingleCase() {
		assertEquals(0,w2.getFeatureAt(0,13));
		assertEquals(1,w1.getFeatureAt(30,0));
		
	}
	
	@Test
	public final void setFeatureAt_SingleCase() {
		w2.setFeatureAt(12,0,2);
		assertEquals(2,w2.getFeatureAt(12,0));
		w16.setFeatureAt(6,3,3);
		assertEquals(3,w16.getFeatureAt(6,3));
		w16.setFeatureAt(6, 3, 2);
	}
	
	@Test
	public final void startGame_TrueCase() {
		w12.startGame();
		assertTrue(w12.isStarted);
	}
	
	@Test
	public final void startGame_FalseCase() {
		assertFalse(w8.isStarted);
		assertFalse(w18.isStarted);
	}
	
	@Test
	public final void setMazub_singleCase() {
		assertEquals(w1,m2.getWorld());
	}
	
	@Test
	public final void getAllPlants_singleCase() {
		assertTrue(w9.getAllPlants().get(0) == p2);
	}
	
	@Test
	public final void getAllSlimes_singleCase() {
		assertEquals(w9.getAllSlimes().get(0),s1);
	}
	
	@Test
	public final void getAllAliens_singleCase() {
		assertEquals(w1.getAllAliens().get(0),m2);
	}
	
	@Test
	public final void getAllSharks_singleCase() {
		assertTrue(w2.getAllSharks().size() == 0);
		w4.addShark(sh1);
		assertEquals(w4.getAllSharks().get(0),sh1);
	}
	
	@Test
	public final void addPlant_sinlgeCase() {
		w2.addPlant(p1);
		assertEquals(p1,w2.getAllPlants().get(0));
	}
	
	@Test
	public final void addSlime_sinlgeCase() {
		w2.addSlime(s1);
		assertEquals(s1,w2.getAllSlimes().get(0));
	}
	
	@Test
	public final void addShark_sinlgeCase() {
		w2.addShark(sh1);
		assertEquals(sh1,w2.getAllSharks().get(0));
	}
	
	@Test
	public final void getBottomLeftPixelOfTile_SingleCase() {
		assertEquals(w11.getBottomLeftPixelOfTile(5,35)[0],100);
		assertEquals(w11.getBottomLeftPixelOfTile(5,35)[1],700);
		
	}
	
	@Test
	public final void getTilePositions_SingleCase() {
		n= new int[][]{{0,1},{1,1},{2,1},{3,1}};
		assertArrayEquals(w4.getTilePositions(2, 6, 15, 8), n);
	}
	
	@Test
	public final void advanceTime_Moving(){
		w9.advanceTime(0.1);
		assertEquals(636,p2.getXPosition(),delta);
		assertEquals(247,p2.getYPosition(),delta);
		assertEquals(-Plant.speed,p2.getXVelocity(),delta);
		assertEquals(0,p2.getYVelocity(),delta);
	}
	
	@Test
	public final void getVisibleWindow_SingleCase() {
		w17.setMazub(m1);
		assertEquals(0,w17.getVisibleWindow()[0]);
		assertEquals(319,w17.getVisibleWindow()[2]);
		assertEquals(0,w17.getVisibleWindow()[1]);
		assertEquals(149,w17.getVisibleWindow()[3]);
		
	}
	
	@Test
	public final void getCollided_SingleCase(){
		assertEquals(w3.getCollided().size(),0);
	}
}

