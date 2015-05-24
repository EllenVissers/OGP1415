package jumpingalien.program.program;

import java.util.Map;

import jumpingalien.model.AllObjects;
import jumpingalien.program.statement.Break;
import jumpingalien.program.statement.BreakException;
import jumpingalien.program.statement.Statement;
import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.ObjectType;
import jumpingalien.program.type.Type;

public class Program {

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.main = mainStatement;
		this.global = globalVariables;
		this.counter = 0;
	}
	
	public AllObjects getGameObject() {
		return this.gameObject;
	}
	
	public void setGameObject(AllObjects obj){
		this.gameObject = obj;
	}

	private Statement main;
	private Map<String,Type> global;
	private int counter;
	private AllObjects gameObject;
	
	public Statement getMainStatement() {
		return this.main;
	}
	
	public Map<String,Type> getGlobalVariables() {
		return this.global;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public void setCounter(int c) {
		this.counter = c;
	}
	
	public void addVariable(String key, Type value) {
		getGlobalVariables().put(key,value);
	}
	
	public boolean isWellFormed = true;
	
	public void setWellFormed(boolean bool){
		this.isWellFormed = bool;
	}
	
	public boolean isWellFormed() {
		return this.isWellFormed;
	}
	
	public void execute(Map<String,Type> globals, double time) {
		globals.put("this", new ObjectType(getGameObject()));
		globals.put("timer", new DoubleType(time));
		if (getMainStatement() instanceof Break)
			setWellFormed(false);
		while (((DoubleType)globals.get("timer")).getValue() > 0)
		{
			try {
				time = getMainStatement().evaluate(globals,getCounter());
				if (getCounter() == 0)
					setCounter(1);
				else
					setCounter(0);
				globals.put("timer",new DoubleType(time));
			} catch (BreakException exc) {
				globals.put("timer",new DoubleType());
			}
		}
	}

}
