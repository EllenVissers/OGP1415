package jumpingalien.model.tests;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.model.Orientation;
import jumpingalien.util.ModelException;
import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * A testing class for Mazub.
 * @author Ellen Vissers, Nina Versin
 * @version	2.0
 */
public class MazubTest {
	
	/**
	 * A variable that registers the range in which two doubles are considered equal.
	 */
	private static double delta = 1e-3;
	
	private static Mazub m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18;
	private static World w1, w2, w3;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		w1 = new World(15,200,75,750,375,185,60);
		w2 = new World(50,40,25,500,175,35,21);
		w3 = new World(15,200,75,750,375,160,73);
		for (int i = 0; i < 50; i++) {
			w2.setFeatureAt(i, 0, 1);
			w2.setFeatureAt(i,39,1);
		}
		for (int i = 0; i < 40; i++) {
			w2.setFeatureAt(0, i, 1);
			w2.setFeatureAt(49, i, 1);
		}
		
		m0 = new Mazub(587, 49, 0, -6.32, 0.9, -10, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 0, 1.5, 3.5, false, false, 0.6, false);
		m1 = new Mazub(50, 150, 1.5, 6.4, 0.9, -10, Orientation.RIGHT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w1, 2, 1.1, 3.0, false, true, 0, false);
		m2 = new Mazub(623, 412, JumpingAlienSprites.ALIEN_SPRITESET);
		m3 = new Mazub(999, 49, -1.5, 0, 0.9, -10, Orientation.RIGHT, JumpingAlienSprites.ALIEN_SPRITESET, 150, w2, 1, 1.0, 4.0, true, false, 0, false);
		m4 = new Mazub(842, 49, 0, 0, 0.9, -10, Orientation.LEFT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 0, 1.0, 2.5, false, false, 0, false);
		m5 = new Mazub(587, 767, 0, -6.32, 0, 0, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 0, 1.5, 3.5, false, false, 0.4, false);
		m6 = new Mazub(1023, 49, 2.43, 8.0, 0.9, -10, Orientation.RIGHT, JumpingAlienSprites.ALIEN_SPRITESET, 175, w2, 2, 2.0, 3.0, false, true, 0, false);
		m7 = new Mazub(1023, 767, -2.725, 0, 0.9, -10, Orientation.LEFT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w3, 3, 1.2, 3.2, true, false, 0, true);
		m8 = new Mazub(842, 49, 0, 0, 0.9, -10, Orientation.RIGHT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 2, 1.0, 2.5, false, false, 0, false);
		m9 = new Mazub(64, 49, 0.0, 0 , 0.9, -10, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 200, w2, 0, 1.2, 3.2, false, false, 0, false);
		m10 = new Mazub(843, 327, 0, 1.85, 0.9, -10, Orientation.LEFT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w1, 0, 1.5, 3.0, false, false, 0, false);
		m11 = new Mazub(210, 49, -1.6, 0, -0.9, 0, Orientation.LEFT, JumpingAlienSprites.ALIEN_SPRITESET, 50, w2, 0, 1.0, 3.1, false, true, 0, false);
		m12 = new Mazub(340, 49, -2.1, 0, -0.9, 0, Orientation.LEFT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 0, 1.0, 3.0, true, false, 0, false);
		m13 = new Mazub(210, 49, 2.97, 0, 0.9, 0, Orientation.RIGHT, JumpingAlienSprites.ALIEN_SPRITESET, 150, w2, 0, 1.0, 3.0, false, true, 0, false);
		m14 = new Mazub(340, 49, 0, 0, 0, 0, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 0, 1.0, 3.0, true, false, 0, false);
		m15 = new Mazub(587, 49, 0, 0, 0.9, -10, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 125, w2, 0, 1.5, 3.5, false, false, 0, false);
		m16 = new Mazub(15, 580, -2.1, -3.7, 0.9, -10, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 100, w3, 0, 1.0, 3.0, true, false, 0, false);
		m17 = new Mazub(623, 49, 2.0, 0, 0.9, -10, Orientation.RIGHT, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 0, 1.0, 4.0, false, true, 0, false);
		m18 = new Mazub(150, 49, 0, 0, 0, 0, Orientation.NONE, JumpingAlienSprites.ALIEN_SPRITESET, 100, w2, 3, 1.2, 3.2, false, false, 0, false);
	}

	@Test
	public final void extendedConstructor_LegalCase() throws ModelException {
		assertEquals(50,(int) Math.round(m1.getXPosition()));
		assertEquals(150,(int) Math.round(m1.getYPosition()));
		assertEquals(1.5,m1.getXVelocity(),delta);
		assertEquals(6.4,m1.getYVelocity(),delta);
		assertEquals(0.9,m1.getXAcc(),delta);
		assertEquals(-10,m1.getYAcc(),delta);
		assertTrue((m1.getSprites() == JumpingAlienSprites.ALIEN_SPRITESET));
		assertTrue((Orientation.RIGHT == m1.getOrientation()));
		assertEquals(100,m1.getHitPoints());
		assertTrue(w1 == m1.getWorld());
		assertFalse(m1.isTerminated());
		assertEquals(2,m1.getNb());
		assertEquals(1.1,m1.getStartVel(),delta);
		assertEquals(3.0,m1.getMaxVel(),delta);
		assertFalse(m1.getLeftButton());
		assertTrue(m1.getRightButton());
		assertEquals(0,m1.getImmuneTime(),delta);
		assertFalse(m1.isDucking());
	}

	@Test(expected = ModelException.class)
	public final void extendedConstructor_IllegalCase() throws ModelException {
		new Mazub(50, 150, 1.5, 6.4, 0.9, -10, Orientation.RIGHT, null, 100, w1, 2, 1.1, 3.0, false, true, 0, false);
	}

	@Test
	public final void secondConstructor_LegalCase() throws ModelException {
		assertEquals(623,(int) Math.round(m2.getXPosition()));
		assertEquals(412,(int) Math.round(m2.getYPosition()));
		assertEquals(0,m2.getXVelocity(),delta);
		assertEquals(0,m2.getYVelocity(),delta);
		assertEquals(0,m2.getXAcc(),delta);
		assertEquals(0,m2.getYAcc(),delta);
		assertTrue((m2.getSprites() == JumpingAlienSprites.ALIEN_SPRITESET));
		assertTrue((Orientation.NONE == m2.getOrientation()));
		assertEquals(Mazub.startHitPoints,m2.getHitPoints());
		assertTrue(null == m2.getWorld());
		assertFalse(m2.isTerminated());
		assertEquals(0,m2.getNb());
		assertEquals(1.0,m2.getStartVel(),delta);
		assertEquals(3.0,m2.getMaxVel(),delta);
		assertFalse(m2.getLeftButton());
		assertFalse(m2.getRightButton());
		assertEquals(0,m2.getImmuneTime(),delta);
		assertFalse(m2.isDucking());
	}
	
	@Test(expected = ModelException.class)
	public final void secondConstructor_IllegalCase() throws ModelException {
		new Mazub(512, 235, null);
	}
	
	@Test
	public final void getLeftButton_SingleCase() {
		assertTrue(m16.getLeftButton());
		assertFalse(m1.getLeftButton());
	}
	
	@Test
	public final void getRightButton_SingleCase() {
		assertTrue(m1.getRightButton());
		assertFalse(m3.getRightButton());
	}
	
	@Test
	public final void getStartVel_SingleCase() {
		assertEquals(1.1,m1.getStartVel(),delta);
		assertEquals(Mazub.startVelX,m2.getStartVel(),delta);
	}
	
	@Test
	public final void getMaxVel_SingleCase() {
		assertEquals(3.5,m0.getMaxVel(),delta);
		assertEquals(Mazub.maxSpeed,m2.getMaxVel(),delta);
	}
	
	@Test
	public final void getNb_SingleCase() {
		assertEquals(2,m1.getNb());
		assertEquals(0,m5.getNb());
	}
	
	@Test
	public final void getImmuneTime_SingleCase() {
		assertEquals(0,m3.getImmuneTime(),delta);
		assertEquals(0.4,m5.getImmuneTime(),delta);
	}
	
	@Test
	public final void isMovingLeft_TrueCase() {
		assertTrue(m7.isMovingLeft());
		assertTrue(m3.isMovingLeft());
	}
	
	@Test
	public final void isMovingLeft_FalseCase() {
		assertFalse(m1.isMovingLeft());
		assertFalse(m6.isMovingLeft());
		assertFalse(m0.isMovingLeft());
	}
	
	@Test
	public final void isMovingRight_TrueCase() {
		assertTrue(m1.isMovingRight());
		assertTrue(m13.isMovingRight());
	}

	@Test
	public final void isMovingRight_FalseCase() {
		assertFalse(m7.isMovingRight());
		assertFalse(m3.isMovingRight());
		assertFalse(m0.isMovingRight());
	}

	@Test
	public final void isImmune_TrueCase() {
		assertTrue(m0.isImmune());
	}
	
	@Test
	public final void isFalling_SingleCase() {
		assertTrue(m16.isFalling());
		assertFalse(m18.isFalling());
	}
	
	@Test
	public final void isDucking_SingleCase() {
		assertTrue(m7.isDucking());
		assertFalse(m6.isDucking());
	}
	
	public final void isImmune_FalseCase() {
		assertFalse(m3.isImmune());
	}
	
	@Test
	public final void startMove_LeftCase() {
		m8.startMove(Orientation.LEFT);
		assertEquals(-m8.getStartVel(),m8.getXVelocity(),delta);
		assertEquals(0,m8.getNb());
		assertTrue(Orientation.LEFT == m8.getOrientation());
		m8.endMove(Orientation.LEFT);
		for (int i = 0; i < 10; i++) {
			m8.advanceTime(0.1);
		}
	}
	
	@Test
	public final void startMove_RightCase() {
		m5.startMove(Orientation.RIGHT);
		assertEquals(m5.getStartVel(),m5.getXVelocity(),delta);
		assertEquals(0,m5.getNb());
		assertTrue(Orientation.RIGHT == m5.getOrientation());
	}
	
	@Test
	public final void endMove_SingleCase() {
		m5.endMove(Orientation.RIGHT);
		m8.endMove(Orientation.LEFT);
		assertEquals(0,m5.getXVelocity(),delta);
		assertEquals(0,m8.getXVelocity(),delta);
	}
	
	@Test
	public final void startJump_SingleCase() {
		m9.startJump();
		assertEquals(Mazub.startVelY,m9.getYVelocity(),delta);
		assertEquals(0,m9.getNb());
	}
	
	@Test
	public final void endJump_SingleCase() {
		m9.endJump();
		assertEquals(0,m9.getYVelocity(),delta);
		double vel = m5.getYVelocity();
		m5.endJump();
		assertEquals(vel,m5.getYVelocity(),delta);
	}
	
	@Test
	public final void startDuck_SingleCase() {
		m10.startDuck();
		assertEquals(Mazub.maxSpeedDuck,m10.getMaxVel(),delta);
		assertEquals(m10.getSprites()[1].getHeight(),m10.getYSize());
		assertEquals(0,m10.getNb());
	}
	
	@Test
	public final void endDuck_SingleCase() {
		m10.endDuck();
		assertEquals(Mazub.maxSpeed,m10.getMaxVel(),delta);
		assertEquals(m10.getSprites()[0].getHeight(),m10.getYSize());
	}
	
	@Test(expected = ModelException.class)
	public final void advanceTime_InvalidTime() throws ModelException {
		m10.advanceTime(0.5);
		m10.advanceTime(-4.3);
	}
	
	@Test
	public final void advanceTime_NotMoving() throws ModelException {
		m17.endMove(Orientation.RIGHT);
		int posx = (int) Math.round(m17.getXPosition());
		int posy = (int) Math.round(m17.getYPosition());
		double velx = m17.getXVelocity();
		double vely = m17.getYVelocity();
		m17.advanceTime(0.1);
		assertEquals(posx,(int) Math.round(m17.getXPosition()));
		assertEquals(posy,(int) Math.round(m17.getYPosition()));
		assertEquals(velx,m17.getXVelocity(),delta);
		assertEquals(vely,m17.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_NormalRightJump() {
		m11.startMove(Orientation.RIGHT);
		m11.startJump();
		m11.advanceTime(0.1);
		assertEquals(220,(int) Math.round(m11.getXPosition()));
		assertEquals(124,(int) Math.round(m11.getYPosition()));
		assertEquals(1.09,m11.getXVelocity(),delta);
		assertEquals(7.0,m11.getYVelocity(),delta);
		m11.endJump();
		for (int i = 0; i<15; i++)
			m11.advanceTime(0.1);
	}
	
	@Test
	public final void advanceTime_NormalLeftJump() {
		m12.startJump();
		m12.advanceTime(0.1);
		assertEquals(319,(int) Math.round(m12.getXPosition()));
		assertEquals(124,(int) Math.round(m12.getYPosition()));
		assertEquals(7,m12.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Right_ReachMaxSpeed() {
		m13.advanceTime(0.1);
		assertEquals(240,(int) Math.round(m13.getXPosition()));
		assertEquals(49,(int) Math.round(m13.getYPosition()));
		assertEquals(3.0,m13.getXVelocity(),delta);
		assertEquals(0,m13.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Left_ReachMaxSpeed() {
		m11.advanceTime(0.1);
		assertEquals(510,(int) Math.round(m11.getXPosition()));
		assertEquals(49,(int) Math.round(m11.getYPosition()));
		assertEquals(2.53,m11.getXVelocity(),delta);
		assertEquals(0,m11.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Duck_NotMoving() {
		m14.endMove(Orientation.LEFT);
		for (int i = 0; i < 10; i++) {
			m14.advanceTime(0.1);
		}
		m14.startDuck();
		m14.advanceTime(0.1);
		assertTrue(m14.getSprites()[1] == m14.getCurrentSprite());
		assertEquals(m14.getMaxVel(),Mazub.maxSpeedDuck,delta);
		assertEquals(m14.getNb(),0);
		m14.endDuck();
	}
	
	@Test
	public final void advanceTime_Duck_MovingRight() {
		m17.startDuck();
		m17.advanceTime(0.1);
		assertEquals(633,(int) Math.round(m17.getXPosition()));
		assertEquals(49,(int) Math.round(m17.getYPosition()));
		assertEquals(1.0,m17.getXVelocity(),delta);
		assertEquals(0,m17.getYVelocity(),delta);
		m17.endDuck();
		m17.endMove(Orientation.RIGHT);
	}
	
	@Test
	public final void getCurrentSprite_Zero(){
		m5.endDuck();
		m5.endJump();
		m5.endMove(Orientation.RIGHT);
		for (int i = 0; i < 15; i++)
			m5.advanceTime(0.1);
		assertTrue(m5.getCurrentSprite() == m5.getSprites()[0]);
	}
	
	@Test
	public final void getCurrentSprite_One() {
		m18.startDuck();
		for (int i = 0; i<15; i++)
			m18.advanceTime(0.1);
		assertTrue(m18.getSprites()[1] == m18.getCurrentSprite());
		m18.endDuck();
	}
	
	@Test
	public final void getCurrentSprite_Two() {
		m5.startMove(Orientation.RIGHT);
		m5.endMove(Orientation.RIGHT);
		m5.advanceTime(0.1);
		assertTrue(m5.getSprites()[2] == m5.getCurrentSprite());
		for (int i = 0; i < 10; i++) {
			m5.advanceTime(0.1);
		}
		assertTrue(m5.getSprites()[0] == m5.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Three() {
		m0.startMove(Orientation.LEFT);
		m0.endMove(Orientation.LEFT);
		m0.advanceTime(0.1);
		assertTrue(m0.getSprites()[3] == m0.getCurrentSprite());
		for (int i = 0; i < 10; i++) {
			m0.advanceTime(0.1);
		}
		assertTrue(m0.getSprites()[0] == m0.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Four() {
		m3.startMove(Orientation.RIGHT);
		m3.startJump();
		assertTrue(m3.getSprites()[4] == m3.getCurrentSprite());
		m3.endJump();
		m3.endMove(Orientation.RIGHT);
	}
	
	@Test
	public final void getCurrentSprite_Five() {
		m3.startMove(Orientation.LEFT);
		m3.startJump();
		assertTrue(m3.getSprites()[5] == m3.getCurrentSprite());
		m3.endJump();
		m3.endMove(Orientation.LEFT);
	}
	
	@Test
	public final void getCurrentSprite_Six() {
		m8.startDuck();
		m8.startMove(Orientation.RIGHT);
		assertTrue(m8.getSprites()[6] == m8.getCurrentSprite());
		m8.endMove(Orientation.RIGHT);
		m8.advanceTime(0.1);
		assertTrue(m8.getSprites()[6] == m8.getCurrentSprite());
		for (int i = 0; i < 10; i++) {
			m8.advanceTime(0.1);
		}
		assertTrue(m8.getSprites()[1] == m8.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Seven() {
		m4.startDuck();
		m4.startMove(Orientation.LEFT);
		assertTrue(m4.getSprites()[7] == m4.getCurrentSprite());
		m4.endMove(Orientation.LEFT);
		m4.advanceTime(0.1);
		assertTrue(m4.getSprites()[7] == m4.getCurrentSprite());
		for (int i = 0; i < 10; i++) {
			m4.advanceTime(0.1);
		}
		assertTrue(m4.getSprites()[1] == m4.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Eight() {
		m15.startMove(Orientation.RIGHT);
		assertTrue(m15.getCurrentSprite() == m15.getSprites()[8]);
		for (int i = 9; i < 9+(m15.getSprites().length-10)/2; i++) {
			m15.advanceTime(0.076);
			assertTrue(m15.getSprites()[i] == m15.getCurrentSprite());
		}
	}
	
	@Test
	public final void getCurrentSprite_Nine(){
		m6.endJump();
		for (int i = 0; i < 10; i++) {
			m6.advanceTime(0.1);
		}
		m6.startMove(Orientation.LEFT);
		assertTrue(m6.getCurrentSprite() == m6.getSprites()[9+(m6.getSprites().length-10)/2]);
		for (int i = 9+(m6.getSprites().length-10)/2; i < 10+(m6.getSprites().length-10); i++) 
		{
			m6.advanceTime(0.075);
			assertTrue(m6.getSprites()[i] == m6.getCurrentSprite());
		}
	}
	
}
