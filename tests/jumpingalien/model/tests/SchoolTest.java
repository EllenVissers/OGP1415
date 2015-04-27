package jumpingalien.model.tests;

import static org.junit.Assert.*;
import jumpingalien.part2.internal.Resources;

import java.util.ArrayList;

import jumpingalien.model.School;
import jumpingalien.model.Slime;
import jumpingalien.util.Sprite;

import org.junit.BeforeClass;
import org.junit.Test;

public class SchoolTest {

	private static ArrayList<Slime> a, b;
	private static School s1,s2,s3;
	private static Slime sl1,sl2,sl3;
	private static Sprite[] s;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s1 = new School();
		s2 = new School();
		s3 = new School();
		s = new Sprite[] { Resources.SLIME_SPRITE_LEFT, Resources.SLIME_SPRITE_RIGHT };
		sl1 = new Slime(20,10,s,s2);
		sl2 = new Slime(30,10,s,s3);
		sl3 = new Slime(15,56,s,s3);
		a = new ArrayList<Slime>();
		a.add(sl1);
		b = new ArrayList<Slime>();
		b.add(sl2);
		b.add(sl3);
	}
	
	@Test
	public void FirstConstructor_SingleCase() {
		assertEquals(a,s2.getSlimes());
		assertEquals(b,s3.getSlimes());
	}

	@Test
	public void SecondConstructor_SingleCase() {
		assertTrue(s1.getSlimes().size() == 0);
	}
	
	@Test
	public void getSlimes_SingleCase() {
		assertEquals(a,s2.getSlimes());
		assertEquals(b,s3.getSlimes());
		assertTrue(s1.getSlimes().size() == 0);
	}
}
