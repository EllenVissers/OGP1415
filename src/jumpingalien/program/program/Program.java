package jumpingalien.program.program;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.program.statement.Statement;
import jumpingalien.program.type.Type;
import jumpingalien.program.expression.Constant;

public class Program {

	public Program(Statement mainStatement,
			Map<String, Type> globalVariables) {
		this.main = mainStatement;
		this.global = globalVariables;
		for (Map.Entry<String,Type> entry : globalVariables.entrySet()) {
		    globalVariableValues.put(entry.getKey(), null);
		}
	}
	
	private Statement main;
	private Map<String,Type> global;
	
	public Statement getMainStatement() {
		return this.main;
	}
	
	public Map<String,Type> getGlobalVariables() {
		return this.global;
	}
	
	public static Map<String, Constant<?>> globalVariableValues = new HashMap<String,Constant<?>>();
	
	public Map<String,Constant<?>> getGlobalVariableValues() {
		return globalVariableValues;
	}

}
