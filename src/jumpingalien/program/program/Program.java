package jumpingalien.program.program;

import java.util.Map;

import jumpingalien.program.statement.Statement;
import jumpingalien.program.type.Type;

public class Program {

	public Program(Statement mainStatement,
			Map<String, Type> globalVariables) {
		this.main = mainStatement;
		this.global = globalVariables;
	}
	
	private Statement main;
	private Map<String,Type> global;
	
	public Statement getMainStatement() {
		return this.main;
	}
	
	public Map<String,Type> getGlobalVariables() {
		return this.global;
	}

}
