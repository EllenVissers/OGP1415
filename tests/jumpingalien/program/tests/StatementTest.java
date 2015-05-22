package jumpingalien.program.tests;

import static org.junit.Assert.*;
import jumpingalien.model.*;
import jumpingalien.part2.internal.Resources;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.*;
import jumpingalien.program.statement.*;
import jumpingalien.program.type.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class StatementTest {

	private static double delta = 1e-3;
	private static Statement s1, s2, s3, s4, s5, s6, s7, s8, s9;
	private static SourceLocation sl;
	private static DoubleType dt;
	private static Shark sh1, sh2, sh3, sh4;
	private static Plant p1, p2;
	private static Map<String,Type> globals = new HashMap<String,Type>();
	private static ArrayList<Statement> list;
	private static Statement st1, st2;
	private static World w1;

	@BeforeClass
	public static void setUpBeforeClass() {
		w1 = new World(50,40,25,500,175,35,21);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 40; j++) {
				w1.setFeatureAt(i, j, 2);
				w1.setFeatureAt(i,j,2);
			}
		}
		sl = new SourceLocation(6,8);
		dt = new DoubleType();
		sh1 = new Shark(15,17,new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT });
		sh2 = new Shark(45,17,new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT });
		sh3 = new Shark(25,17,new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT });
		sh4 = new Shark(48,17,new Sprite[] { Resources.SHARK_SPRITE_LEFT, Resources.SHARK_SPRITE_RIGHT });
		p1 = new Plant(49,17,new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT });
		p2 = new Plant(27,17,new Sprite[] { Resources.PLANT_SPRITE_LEFT, Resources.PLANT_SPRITE_RIGHT });
		w1.addShark(sh4);
		w1.addShark(sh1);
		w1.addShark(sh2);
		w1.addShark(sh3);
		w1.addPlant(p1);
		w1.addPlant(p2);
		globals.put("sh1",new ObjectType(sh1));
		globals.put("sh2",new ObjectType(sh2));
		globals.put("sh3",new ObjectType(sh3));
		globals.put("this",new ObjectType(sh4));
		globals.put("p1",new ObjectType(p1));
		globals.put("p2",new ObjectType(p2));
		globals.put("timer",new DoubleType((double)114000));
		globals.put("name", new ObjectType(p2));
		list = new ArrayList<Statement>();
		st1 = new Print(sl,new Constant<String>(sl,"statement1"));
		st2 = new Print(sl,new Constant<String>(sl,"statement2"));
		list.add(st1);
		list.add(st2);
		p1.startMove(Orientation.RIGHT);
		
		s1 = new Assignment(sl,"test",dt,new Constant<Double>(sl,(double)7));
		// double test = 7;
		s2 = new Break(sl);
		// break;
		s3 = new Foreach(sl, "name", Kind.SHARK,
				(Expression)new BinaryExpression<Double,Double,Boolean>(sl,new UnaryExpression<AllObjects,Double>(sl,new Constant<AllObjects>(sl,(AllObjects) globals.get("this").getValue()),t->t.getXPosition()),new Constant<Double>(sl,(double)35),(a,b)->(a<b)), 
				(Expression)new UnaryExpression<AllObjects,Double>(sl,new Constant<AllObjects>(sl,(AllObjects) globals.get("this").getValue()),t->t.getXPosition()), 
				SortDirection.ASCENDING,
				(Statement) new Print(sl,new Constant<AllObjects>(sl,((ObjectType)globals.get("name")).getValue())));
		// foreach shark where xpos < 45 sorted by xpos (ascending) do print(this)
		s4 = new If(sl, (Expression)new UnaryExpression<AllObjects,Boolean>(sl,new Constant<AllObjects>(sl,sh2),t->(t instanceof Shark)),
				(Statement) new Print(sl,new Constant<String>(sl,"ifBody")), 
				(Statement) new Print(sl,new Constant<String>(sl,"elseBody")));
		// if (sh2 instanceof Shark) do (print(ifBody) else (print(elseBody))
		s5 = new Print(sl,new Constant<String>(sl,"print"));
		// System.out.println("print");
		s6 = new Sequence(sl,list);
		// For each statement in list do evaluate();
		s7 = new Wait(sl,new Constant<Double>(sl,8.0));
		// Wait for 8s
		sh3.endMove(Orientation.RIGHT);
		sh3.endMove(Orientation.LEFT);
		globals.put("this", new ObjectType(sh3));
		s8 = new While(sl,(Expression)new BinaryExpression<Double,Double,Boolean>(sl,new UnaryExpression<AllObjects,Double>(sl,new Constant<AllObjects>(sl,sh3),t->t.getXAcc()),new Constant<Double>(sl,1.0),(a,b)->(a<b)),
				(Statement)new ActionStatement<GameObject,Void>(sl,new Constant<Direction>(sl,Direction.RIGHT),(t,d)->t.startMove(d)));
		// While (sh3.getXAcc()<1) do this.startMove(right);
		globals.put("this", new ObjectType(sh4));
		s9 = new ActionStatement<GameObject,Void>(sl,new Constant<Direction>(sl,Direction.RIGHT),(t,d)->t.startMove(d));
		// this.startMove(Right);
	}
	
	@Test
	public void Constructor_LegalCase() {
		assertTrue(s5.getSourceLocation() == sl);
		assertTrue(((Print)s5).getExpression().evaluate(globals) == new Constant<String>(sl,"print").evaluate(globals));
	}
	
	@Test
	public void evaluateTest() {
		try {
			s1.evaluate(globals,0);
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating Assignment statement");
		}
		assertTrue(Util.fuzzyEquals(((DoubleType)globals.get("test")).getValue(),7,delta));
		System.out.println("--> Assignment OK");
		System.out.println("-------------------------------------------------------------------------");
		
		try {
			s2.evaluate(globals,0); // This should throw a BreakException
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating Break statement");
			System.out.println("time left: " + exc.getTime());
		}
		System.out.println("--> If a BreakException was thrown and there is still time left: Break OK");
		System.out.println("--------------------------------------------------------------------------");
		
		try {
			s3.evaluate(globals,0); //
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating Foreach statement");
		}
		System.out.println("--> ... : Foreach OK");
		System.out.println("--------------------------------------------------------------------------");
		
		try {
			s4.evaluate(globals,0); 
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating If statement");
		}
		System.out.println("--> If only 'ifBody' was printed : If OK");
		System.out.println("--------------------------------------------------------------------------");
		
		try {
			s5.evaluate(globals,0); // This should print "print"
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating Print statement");
		}
		System.out.println("--> If 'print' was printed: Print OK");
		System.out.println("--------------------------------------------------------------------------");
		
		try {
			s6.evaluate(globals,0); // This should print "statement1" and "statement2"
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating Sequence statement");
		}
		System.out.println("--> If both 'statement1' and 'statement2' were printed: Sequence OK");
		System.out.println("--------------------------------------------------------------------------");
		
		System.out.println(globals.get("timer").getValue());
		try {
			s7.evaluate(globals,0); 
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating Wait statement");
		}
		System.out.println(globals.get("timer").getValue()); // This should be 8 seconds less
		System.out.println("--> If the difference is 8 seconds: Wait OK");
		System.out.println("--------------------------------------------------------------------------");
		
		sh3.endMove(Orientation.RIGHT);
		sh3.endMove(Orientation.LEFT);
		globals.put("this", new ObjectType(sh3));
		assertTrue(Util.fuzzyEquals(sh3.getXAcc(),0,delta));
		try {
			s8.evaluate(globals,0); 
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating While statement");
			System.out.println(exc.getTime());
		}
		assertFalse(Util.fuzzyEquals(10*sh3.getXAcc(), 0,delta));
		System.out.println("--> While OK");
		System.out.println("--------------------------------------------------------------------------");
		
		globals.put("this", new ObjectType(sh4));
		globals.put("timer",new DoubleType((double)4581));
		try {
			s9.evaluate(globals,0); 
		} catch (BreakException exc) {
			System.out.println("BreakException thrown by evaluating ActionStatement");
		}
		assertFalse(Util.fuzzyEquals(((GameObject)globals.get("this").getValue()).getXAcc(),0,delta));
		System.out.println("--> ActionStatement OK");
		System.out.println("--------------------------------------------------------------------------");
	}

}
