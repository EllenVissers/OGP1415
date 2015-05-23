package jumpingalien.program.tests;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.program.Program;
import jumpingalien.program.statement.Print;
import jumpingalien.program.type.Type;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramTest {

	private static SourceLocation loc;
	private static Print s1;
	private static Program p1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		loc = new SourceLocation(5,3);
		Map<String,Type> globals = new HashMap<String,Type>();
		
		s1 = new Print(loc,new Constant<Double>(loc,2.9));
		p1 = new Program(s1,globals);
		
	}
	
	@Test
	public void Constructor(){
		
	}

	@Test
	public void isWellFormed_CorrectCase(){
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> result = facade.parse(
				"while true do"
  						+"start_run right;"
  						+"wait (0.5 - 0.001);"
  						+"stop_run right;"
  						+"start_run left;"
  						+"wait (0.5 - 0.001);"
  						+"stop_run left;"
				+"done");
		assumeTrue(result.isSuccess());
		assertTrue(facade.isWellFormed((Program) result.getResult()));
		
	}
	
	@Test
	public void isWellFormed_FalseCaseBreakInWhile(){
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> result = facade.parse(
				"boolean b := true"
				+"while true do"
						+"if b then"
  						+"start_run right;"
  						+"break"
  						+"done");
		assumeTrue(result.isSuccess());
		assertFalse(facade.isWellFormed((Program) result.getResult()));
		
	}
	
	@Test
	public void isWellFormed_FalseCaseBreakInForEach(){
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> result = facade.parse(
				"object o := null"
  				+"foreach(any,o)"
  						+"break"
  				+"done");
		assumeTrue(result.isSuccess());
		assertFalse(facade.isWellFormed((Program) result.getResult()));
		
	}
	
	@Test
	public void isWellFormed_FalseCaseActionInForEach(){
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> result = facade.parse(
				"object o := null"
  				+"foreach(any,o)"
  					+"start_run right;"
  					+"wait (0.5 - 0.001);"
  					+"stop_run right;"
  				+"done");
		assumeTrue(result.isSuccess());
		assertFalse(facade.isWellFormed((Program) result.getResult()));
		
	}

}
