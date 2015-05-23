package jumpingalien.program.tests;

import static org.junit.Assert.*;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Tile;
import jumpingalien.model.World;
import jumpingalien.program.expression.BinaryExpression;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.expression.UnaryExpression;
import jumpingalien.program.type.*;
import jumpingalien.part2.internal.Resources;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.util.Sprite;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionTests {

	private static SourceLocation loc;
	private static Constant<Double> d1,d2,d3,d4;
	private static Constant<Boolean> b1,b2;
	private static UnaryExpression<Double, Double> e1;
	private static UnaryExpression<Boolean, Boolean> e2;
	private static BinaryExpression<Double, Double, Double> e4,e5;
	private static BinaryExpression<Double, Double, Boolean> e6,e7;
	private static BinaryExpression<Boolean, Boolean, Boolean> e10;
	private static HashMap<String, Type> globals;
	private static Sprite[] s;
	private static Plant p1;
	private static UnaryExpression<AllObjects, Double> e3;
	private static ObjectType o1,o2;
	private static Constant<Object> c1,c2;
	private static UnaryExpression<AllObjects, Double> e8,e9;
	private static World w1;
	private static UnaryExpression<AllObjects, Boolean> e11,e12,e13;
	private static Tile t1;
	private static UnaryExpression<AllObjects, Boolean> e14;
	private static UnaryExpression<AllObjects, Boolean> e15;
	private static UnaryExpression<AllObjects, Boolean> e16;
	private static Constant<Direction> c3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loc = new SourceLocation(5,3);
		globals = new HashMap<String,Type>();
		
		d1 = new Constant<Double>(loc,5.2);
		d2 = new Constant<Double>(loc,9.0);
		d3 = new Constant<Double>(loc,3.6);
		d4 = new Constant<Double>(loc,0.9);
		
		b1 = new Constant<Boolean>(loc,true);
		b2 = new Constant<Boolean>(loc,false);
		
		s = new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT };
		
		w1 = new World(50,40,25,500,175,35,21);
		p1 = new Plant(0,0,s);
		t1 = new Tile(0,0,w1,2);
		
		w1.addPlant(p1);
		
		o1 = new ObjectType(p1);
		o2 = new ObjectType(t1);
		
		c1 = new Constant<Object>(loc,p1);
		c2 = new Constant<Object>(loc,t1);
		c3 = new Constant<Direction>(loc,IProgramFactory.Direction.RIGHT);
		
		globals.put("plant", o1);
		globals.put("tile", o2);
		
		e1 = new UnaryExpression<Double,Double>(loc,d2,t->Math.sqrt(t));
		e2 = new UnaryExpression<Boolean,Boolean>(loc,b1,t->!t);
		e3 = new UnaryExpression<AllObjects,Double>(loc,c1,t->t.getXPosition());
		e4 = new BinaryExpression<Double,Double,Double>(loc,d1,d2,(a,b)->(a+b));
		e5 = new BinaryExpression<Double,Double,Double>(loc,d3,d4,(a,b)->a*b);
		e6 = new BinaryExpression<Double,Double,Boolean>(loc,d3,d2,(a,b)->(a<b));
		e7 = new BinaryExpression<Double,Double,Boolean>(loc,d1,d4,(a,b)->(a>=b));
		e8 = new UnaryExpression<AllObjects,Double>(loc,c1,t->t.getWidth());
		e9 = new UnaryExpression<AllObjects,Double>(loc,c1,t->(double)t.getHitPoints());
		e10 = new BinaryExpression<Boolean,Boolean,Boolean>(loc,e6,e7,(a,b)->(a&&b));
		e11 = new UnaryExpression<AllObjects,Boolean>(loc,c1,t->(t instanceof Plant));
		e12 = new UnaryExpression<AllObjects,Boolean>(loc,c1,t->(t instanceof Mazub));
		e13 = new UnaryExpression<AllObjects,Boolean>(loc,c1,t->t.isTerminated());
		e14 = new UnaryExpression<AllObjects,Boolean>(loc,c2,t->t.isMagma());
		e15 = new UnaryExpression<AllObjects,Boolean>(loc,c2,t->t.isWater());
		e16 = new UnaryExpression<AllObjects,Boolean>(loc,c2,t->t.isPassable());
		
	}

	@Test
	public void UnaryExpressionTest(){
		assertEquals(e1.evaluate(globals),new Double(3.0));
		assertEquals(e2.evaluate(globals),new Boolean(false));
		assertEquals(e3.evaluate(globals),new Double(0.0));
		assertEquals(e8.evaluate(globals),new Double(54));
		assertEquals(e9.evaluate(globals),new Double(1));
		assertEquals(e11.evaluate(globals),new Boolean(true));
		assertEquals(e12.evaluate(globals),new Boolean(false));
		assertEquals(e13.evaluate(globals),new Boolean(false));
		assertEquals(e14.evaluate(globals),new Boolean(false));
		assertEquals(e15.evaluate(globals),new Boolean(true));
		assertEquals(e16.evaluate(globals),new Boolean(true));
	}
	
	@Test
	public void BinaryExpressionTest(){
		assertEquals(e4.evaluate(globals),new Double(14.2));
		assertEquals(e5.evaluate(globals),new Double(3.24));
		assertEquals(e6.evaluate(globals),new Boolean(true));
		assertEquals(e7.evaluate(globals),new Boolean(true));
		assertEquals(e10.evaluate(globals),new Boolean(true));
	}
	
	@Test
	public void ConstantTest(){
		assertEquals(c1.evaluate(globals),p1);
		assertEquals(c3.evaluate(globals),IProgramFactory.Direction.RIGHT);
		assertEquals(b2.evaluate(globals),false);
	}

	
	@Test
	public void readVariableTest(){
		assertEquals(globals.get("plant").getValue(),p1);
	}

}
