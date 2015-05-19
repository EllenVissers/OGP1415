package jumpingalien.program.program;

import java.util.HashMap;
import java.util.Map;
import jumpingalien.model.GameObject;
import jumpingalien.program.statement.Statement;
import jumpingalien.program.type.Type;
import jumpingalien.program.expression.Constant;

public class Program {

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.main = mainStatement;
		this.global = globalVariables;
		for (Map.Entry<String,Type> entry : globalVariables.entrySet()) {
		    variableValues.put(entry.getKey(), null);
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
	
	public static Map<String, Constant<?>> variableValues = new HashMap<String,Constant<?>>();
	
	public Map<String,Constant<?>> getVariableValues() {
		return variableValues;
	}
	
	public static GameObject gameObject;
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public boolean isWellFormed() {
		// TODO
		return true;
	}
	
	public static double timer = 0;
	
	public static void resetTimer() {
		
	}
	
	public void execute(double time) {
		timer = time;
		getMainStatement().evaluate();
//		Iterator<Statement> iter = getMainStatement().iterator();
//		while(time > 0){
//			if (iter.hasNext()){
//				Statement next = iter.next();
//				if(next != null){
//					time -= 0.001;
//					next.evaluate();
//				}
//			}
//			else {
//				iter = getMainStatement().iterator();
//				if (iter.hasNext()){
//					Statement next = iter.next();
//					if(next != null){
//						time -= 0.001;
//						next.evaluate();
//					}
//				}
//			}
//		}
	}

}
