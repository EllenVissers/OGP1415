package jumpingalien.program.tests;

import static org.junit.Assert.*;
import jumpingalien.common.sprites.JumpingAlienSprites;
import jumpingalien.model.AllObjects;
import jumpingalien.model.Mazub;
import jumpingalien.program.expression.BinaryExpression;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.UnaryExpression;
import jumpingalien.program.type.*;
import jumpingalien.part3.programs.SourceLocation;
import java.util.HashMap;

import java.util.function.Function;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionTests {

	private static SourceLocation loc;
	private static Constant<Double> d1,d2,d3,d4;
	private static Constant<Boolean> b1,b2;
	private static UnaryExpression<Double, Double> e1;
	private static UnaryExpression<Boolean, Boolean> e2;
	private static Mazub o1;
	private static ObjectType t1;
	private static BinaryExpression<Double, Double, Double> e4,e5;
	private static BinaryExpression<Double, Double, Boolean> e6,e7;
	private static BinaryExpression<Boolean, Boolean, Boolean> e10;
	private static HashMap<String, Type> globals;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loc = new SourceLocation(5,3);
		
		d1 = new Constant<Double>(loc,5.2);
		d2 = new Constant<Double>(loc,9.0);
		d3 = new Constant<Double>(loc,3.6);
		d4 = new Constant<Double>(loc,0.9);
		
		b1 = new Constant<Boolean>(loc,true);
		b2 = new Constant<Boolean>(loc,false);
		
//		o1 = new Mazub(623, 412, JumpingAlienSprites.ALIEN_SPRITESET);
//		t1 = new ObjectType(o1);
//		e1 = 
		
		globals = new HashMap<String,Type>();
		
		e1 = new UnaryExpression<Double,Double>(loc,d2,t->Math.sqrt(t));
		e2 = new UnaryExpression<Boolean,Boolean>(loc,b1,t->!t);
//		e3 = new UnaryExpression<AllObjects,Double>(loc,o1,t->t.getXPosition());
		e4 = new BinaryExpression<Double,Double,Double>(loc,d1,d2,(a,b)->(a+b));
		e5 = new BinaryExpression<Double,Double,Double>(loc,d3,d4,(a,b)->a*b);
		e6 = new BinaryExpression<Double,Double,Boolean>(loc,d3,d2,(a,b)->(a<b));
		e7 = new BinaryExpression<Double,Double,Boolean>(loc,d1,d4,(a,b)->(a>=b));
//		e8 =
//		e9 =
		e10 =  new BinaryExpression<Boolean,Boolean,Boolean>(loc,e6,e7,(a,b)->(a&&b));
		
		
	}

	@Test
	public void UnaryExpression_SingleCase(){
		assertEquals(e1.evaluate(globals),new Double(3.0));
		assertEquals(e2.evaluate(globals),new Boolean(false));
	}
	
	@Test
	public void BinaryExpression_SingleCase(){
		assertEquals(e4.evaluate(globals),new Double(14.2));
		assertEquals(e5.evaluate(globals),new Double(3.24));
		assertEquals(e6.evaluate(globals),new Boolean(true));
		assertEquals(e7.evaluate(globals),new Boolean(true));
		assertEquals(e10.evaluate(globals),new Boolean(true));
	}

}
