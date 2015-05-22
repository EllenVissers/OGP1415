package jumpingalien.program.tests;

import static org.junit.Assert.*;
import static org.junit.Assume.*;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.program.program.Program;

import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
