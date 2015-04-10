package jumpingalien.model.tests;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.model.Orientation;
import jumpingalien.util.ModelException;
import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
//import jumpingalien.model.GameObject;

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
	
	private static Mazub m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23;
	private static World w1, w2, w3;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		w1 = new World(15,200,75,750,375,185,60);
		w2 = new World(15,250,100,750,375,235,45);
		w3 = new World(15,200,75,750,375,160,73);
		m0 = new Mazub(587, 767, 0, -6.32, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.5, 3.5, false, false, w1, 0, false);
		m1 = new Mazub(50, 150, 1.5, 6.4, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.RIGHT, 2, 1.1, 3.0, false, true, w1, 0, false);
		m2 = new Mazub(623, 412, JumpingAlienSprites.ALIEN_SPRITESET);
		m3 = new Mazub(999, 150, -1.5, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.RIGHT, 1, 1.0, 4.0, true, false, w3, 0, false);
		m4 = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
		m5 = new Mazub(587, 767, 0, -6.32, 0, 0, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.5, 3.5, false, false, w2, 0, false);
		m6 = new Mazub(1023, 395, 2.43, 8.0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.RIGHT, 2, 2.0, 3.0, false, true, w2, 0, false);
		m7 = new Mazub(1023, 767, -2.725, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.LEFT, 3, 1.2, 3.2, true, false, w3, 0, true);
		m8 = new Mazub(842, 248, 0, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.RIGHT, 2, 1.0, 2.5, false, false, w1, 0, false);
		m9 = new Mazub(512, 0, 3.0, 0 , 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.RIGHT, 0, 1.2, 3.2, false, true, w3, 0, false);
		m10 = new Mazub(843, 327, 0, 1.85, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.LEFT, 0, 1.5, 3.0, false, false, w1, 0, false);
		m11 = new Mazub(210, 160, 1.6, 4.0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.1, false, true, w2, 0, false);
		m12 = new Mazub(340, 580, -2.1, -3.7, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.0, true, false, w2, 0, false);
		m13 = new Mazub(210, 0, 2.95, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.0, false, false, w1, 0, false);
		m14 = new Mazub(340, 580, -2.95, -3.7, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.0, true, false, w3, 0, false);
		m15 = new Mazub(1020, 160, 1.6, 4.0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.1, false, true, w1, 0, false);
		m16 = new Mazub(15, 580, -2.1, -3.7, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.0, true, false, w3, 0, false);
		m17 = new Mazub(210, 760, 1.6, 4.0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.1, false, true, w3, 0, false);
		m18 = new Mazub(340, 20, -2.1, -3.7, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.0, 3.0, true, false, w2, 0, false);
		m19 = new Mazub(1023, 767, 2.725, -3.8, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.LEFT, 3, 1.2, 3.2, false, true, w1, 0, false);
		m20 = new Mazub(0, 0, JumpingAlienSprites.ALIEN_SPRITESET);
		m21 = new Mazub(623, 0, 2.0, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.RIGHT, 0, 1.0, 4.0, false, true, w2, 0, false);
		m22 = new Mazub(587, 0, 0, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.NONE, 0, 1.5, 3.5, false, false, w2, 0, false);
		m23 = new Mazub(842, 0, 0, 0, 0.9, -10, JumpingAlienSprites.ALIEN_SPRITESET, Orientation.LEFT, 0, 1.0, 2.5, false, false, w1, 0, false);
	}

	@Test
	public final void extendedConstructor_LegalCase() throws ModelException {
		assertEquals(50,(int) Math.round(m1.getXPosition()));
		assertEquals(150,(int) Math.round(m1.getYPosition()));
		assertEquals(1.5,m1.getXVelocity(),delta);
		assertEquals(6.4,m1.getYVelocity(),delta);
		assertTrue((m1.getSprites() == JumpingAlienSprites.ALIEN_SPRITESET));
		assertTrue((Orientation.RIGHT == m1.getOrientation()));
		assertEquals(92,m1.getYSize());
		assertEquals(2,m1.getNb());
		assertEquals(1.1,m1.getStartVel(),delta);
		assertEquals(3.0,m1.getMaxVel(),delta);
	}

	@Test(expected = ModelException.class)
	public final void extendedConstructor_IllegalCase() throws ModelException {
		new Mazub(50, 150, 1.5, 6.4, 0.9, -10, null, Orientation.RIGHT, 2, 1.1, 3.0, false, true, w1, 0, false);
	}

	@Test
	public final void secondConstructor_LegalCase() throws ModelException {
		assertEquals(623,(int) Math.round(m2.getXPosition()));
		assertEquals(412,(int) Math.round(m2.getYPosition()));
		assertEquals(0,m2.getXVelocity(),delta);
		assertEquals(0,m2.getYVelocity(),delta);
		assertTrue((m2.getSprites() == JumpingAlienSprites.ALIEN_SPRITESET));
		assertTrue((Orientation.NONE == m2.getOrientation()));
		assertEquals(92,m2.getYSize());
		assertEquals(0,m2.getNb());
		assertEquals(1.0,m2.getStartVel(),delta);
		assertEquals(3.0,m2.getMaxVel(),delta);
	}
	
	@Test(expected = NullPointerException.class)
	public final void secondConstructor_IllegalCase() throws NullPointerException {
		new Mazub(512, 235, null);
	}
	
	@Test
	public final void getLeftButton_SingleCase() {
		assertTrue(m3.getLeftButton());
		assertFalse(m13.getLeftButton());
	}
	
	@Test
	public final void getRightButton_SingleCase() {
		assertTrue(m11.getRightButton());
		assertFalse(m16.getRightButton());
	}
	
	@Test
	public final void getXPosition_SimpleCase() {
		assertEquals(999,(int) Math.round(m3.getXPosition()));
		assertEquals(1023,(int) Math.round(m19.getXPosition()));
	}
	
	@Test
	public final void getXPosition_BoundaryCase() {
		assertEquals(0,(int) Math.round(m20.getXPosition()));
		assertEquals(1023,(int) Math.round(m7.getXPosition()));
		assertEquals(1023,(int) Math.round(m6.getXPosition()));
	}
	
	@Test
	public final void getYPosition_SimpleCase() {
		assertEquals(150,(int) Math.round(m1.getYPosition()));
		assertEquals(395,(int) Math.round(m6.getYPosition()));
	}
	
	@Test
	public final void getYPosition_BoundaryCase() {
		assertEquals(0,(int) Math.round(m20.getYPosition()));
		assertEquals(767,(int) Math.round(m7.getYPosition()));
	}
	
	@Test
	public final void getXVelocity_SingleCase() {
		assertEquals(0,m20.getXVelocity(),delta);
		assertEquals(1.5,m1.getXVelocity(),delta);
		assertEquals(-2.725,m7.getXVelocity(),delta);
	}
	
	@Test
	public final void getYVelocity_SingleCase() {
		assertEquals(0,m20.getYVelocity(),delta);
		assertEquals(-3.8,m19.getYVelocity(),delta);
	}
	
	@Test
	public final void getYSize_SingleCase() {
		assertEquals(92,m20.getYSize());
		assertEquals(70,m7.getYSize());
	}
	
	@Test
	public final void getStartVel_SingleCase() {
		assertEquals(1.1,m1.getStartVel(),delta);
		assertEquals(1.2,m19.getStartVel(),delta);
	}
	
	@Test
	public final void getMaxVel_SingleCase() {
		assertEquals(3.0,m1.getMaxVel(),delta);
		assertEquals(3.2,m19.getMaxVel(),delta);
	}
	
	@Test
	public final void getNb_SingleCase() {
		assertEquals(2,m1.getNb());
		assertEquals(0,m20.getNb());
		assertEquals(1,m3.getNb());
	}
	
	@Test
	public final void getOrientation_SingleCase() {
		assertTrue(Orientation.RIGHT == m1.getOrientation());
		assertTrue(Orientation.NONE == m20.getOrientation());
		assertTrue(Orientation.LEFT == m7.getOrientation());
	}
	
	@Test
	public final void isMovingLeft_TrueCase() {
		assertTrue(m7.isMovingLeft());
		assertTrue(m3.isMovingLeft());
	}
	
	@Test
	public final void isMovingLeft_FalseCase() {
		assertFalse(m19.isMovingLeft());
		assertFalse(m6.isMovingLeft());
		assertFalse(m20.isMovingLeft());
	}
	
	@Test
	public final void isMovingRight_TrueCase() {
		assertTrue(m1.isMovingRight());
		assertTrue(m19.isMovingRight());
	}

	@Test
	public final void isMovingRight_FalseCase() {
		assertFalse(m7.isMovingRight());
		assertFalse(m3.isMovingRight());
		assertFalse(m20.isMovingRight());
	}

	@Test
	public final void isJumping_TrueCase() {
		assertTrue(m1.isJumping());
		assertTrue(m6.isJumping());
	}
	
	public final void isJumping_FalseCase() {
		assertFalse(m20.isJumping());
		assertFalse(m7.isJumping());
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
		//m21.setRightButton(false);
		m21.endMove(Orientation.RIGHT);
		int posx = (int) Math.round(m21.getXPosition());
		int posy = (int) Math.round(m21.getYPosition());
		double velx = m21.getXVelocity();
		double vely = m21.getYVelocity();
		m21.advanceTime(0.1);
		assertEquals(posx,(int) Math.round(m21.getXPosition()));
		assertEquals(posy,(int) Math.round(m21.getYPosition()));
		assertEquals(velx,m21.getXVelocity(),delta);
		assertEquals(vely,m21.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_NormalRightJump() {
		m11.advanceTime(0.1);
		assertEquals(226,(int) Math.round(m11.getXPosition()));
		assertEquals(195,(int) Math.round(m11.getYPosition()));
		assertEquals(1.69,m11.getXVelocity(),delta);
		assertEquals(3.0,m11.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_NormalLeftJump() {
		m12.advanceTime(0.1);
		assertEquals(319,(int) Math.round(m12.getXPosition()));
		assertEquals(538,(int) Math.round(m12.getYPosition()));
		assertEquals(-4.7,m12.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Right_ReachMaxSpeed() {
		m13.advanceTime(0.1);
		assertEquals(240,(int) Math.round(m13.getXPosition()));
		assertEquals(0,(int) Math.round(m13.getYPosition()));
		assertEquals(3.0,m13.getXVelocity(),delta);
		assertEquals(0,m13.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Left_ReachMaxSpeed() {
		m11.advanceTime(0.1);
		assertEquals(244,(int) Math.round(m11.getXPosition()));
		assertEquals(220,(int) Math.round(m11.getYPosition()));
		assertEquals(1.78,m11.getXVelocity(),delta);
		assertEquals(2.0,m11.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Right_OutOfBounds() {
		m15.advanceTime(0.1);
		assertEquals(1023,(int) Math.round(m15.getXPosition()));
		assertEquals(195,(int) Math.round(m15.getYPosition()));
		assertEquals(1.69,m15.getXVelocity(),delta);
		assertEquals(3.0,m15.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Left_OutOfBounds() {
		m16.advanceTime(0.1);
		assertEquals(0,(int) Math.round(m16.getXPosition()));
		assertEquals(538,(int) Math.round(m16.getYPosition()));
		assertEquals(-4.7,m16.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Jump_OutOfBoundsTop() {
		m17.advanceTime(0.1);
		assertEquals(226,(int) Math.round(m17.getXPosition()));
		assertEquals(767,(int) Math.round(m17.getYPosition()));
		assertEquals(1.69,m17.getXVelocity(),delta);
		assertEquals(0,m17.getYVelocity(),delta);
	}
	
	@Test
	public final void advanceTime_Jump_OutOfBoundsBottom() {
		m18.advanceTime(0.1);
		assertEquals(319,(int) Math.round(m18.getXPosition()));
		assertEquals(0,(int) Math.round(m18.getYPosition()));
		assertEquals(-2.01,m18.getXVelocity(),delta);
		assertEquals(0,m18.getYVelocity(),delta);
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
		m21.startDuck();
		m21.advanceTime(0.1);
		assertEquals(633,(int) Math.round(m21.getXPosition()));
		assertEquals(0,(int) Math.round(m21.getYPosition()));
		assertEquals(1.0,m21.getXVelocity(),delta);
		assertEquals(0,m21.getYVelocity(),delta);
		m21.endDuck();
		m21.endMove(Orientation.RIGHT);
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
		m4.startDuck();
		for (int i = 0; i<15; i++)
			m4.advanceTime(0.1);
		assertTrue(m4.getSprites()[1] == m4.getCurrentSprite());
		m4.endDuck();
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
		m4.startMove(Orientation.RIGHT);
		m4.startJump();
		assertTrue(m4.getSprites()[4] == m4.getCurrentSprite());
		m4.endJump();
		m4.endMove(Orientation.RIGHT);
	}
	
	@Test
	public final void getCurrentSprite_Five() {
		m4.startMove(Orientation.LEFT);
		m4.startJump();
		assertTrue(m4.getSprites()[5] == m4.getCurrentSprite());
		m4.endJump();
		m4.endMove(Orientation.LEFT);
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
		m23.startDuck();
		m23.startMove(Orientation.LEFT);
		assertTrue(m23.getSprites()[7] == m23.getCurrentSprite());
		m23.endMove(Orientation.LEFT);
		m23.advanceTime(0.1);
		assertTrue(m23.getSprites()[7] == m23.getCurrentSprite());
		for (int i = 0; i < 10; i++) {
			m23.advanceTime(0.1);
		}
		assertTrue(m23.getSprites()[1] == m23.getCurrentSprite());
	}
	
	@Test
	public final void getCurrentSprite_Eight() {
		m22.startMove(Orientation.RIGHT);
		assertTrue(m22.getCurrentSprite() == m22.getSprites()[8]);
		for (int i = 9; i < 9+(m22.getSprites().length-10)/2; i++) {
			m22.advanceTime(0.076);
			assertTrue(m22.getSprites()[i] == m22.getCurrentSprite());
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
