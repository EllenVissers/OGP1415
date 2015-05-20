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
	}
	
	private Statement main;
	private Map<String,Type> global;
	
	public Statement getMainStatement() {
		return this.main;
	}
	
	public Map<String,Type> getGlobalVariables() {
		return this.global;
	}
	
	public void addVariable(String key, Type value) {
		getGlobalVariables().put(key,value);
	}
	
	public boolean isWellFormed() {
		// TODO
		return true;
	}
	
	public void execute(Map<String,Type> globals, double time) {
		getMainStatement().evaluate(globals,time);
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
