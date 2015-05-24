package jumpingalien.program.program;
import java.util.List;
import java.util.Map;
import jumpingalien.program.statement.*;
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
		if (! checkWellFormed(main))
			setWellFormed(false);
		else
			setWellFormed(true);
		setGameObject(null);
	}
	
	private Statement main;
	private Map<String,Type> global;
	private AllObjects gameObject;
	private boolean isWellFormed;
	
	private boolean checkWellFormed(Statement main) {
		if (main instanceof Foreach)
		{
			Statement body = ((Foreach)main).getBody();
			if (body instanceof Break)
				return false;
			return (! containsActionStatement(body));
		}
		if ((! (main instanceof Foreach)) && (! (main instanceof While)))
			return (! containsBreak(main));
		return true;
	}
	
	private boolean containsActionStatement(Statement s) {
		if (s instanceof If)
		{
			Statement ifBody = ((If)s).getIfBody();
			Statement elseBody = ((If)s).getElseBody();
			return (containsActionStatement(ifBody) || containsActionStatement(elseBody));
		}
		else if (s instanceof While)
		{
			Statement body = ((While)s).getBody();
			return (containsActionStatement(body));
		}
		else if (s instanceof Sequence)
		{
			List<Statement> l = ((Sequence) s).getStatements();
			for (Statement ls : l)
				if (containsActionStatement(ls))
					return true;
			return false;
		}
		else if (s instanceof ActionStatement)
			return true;
		return false;
	}
	
	private boolean containsBreak(Statement s) {
		if (s instanceof Break)
			return true;
		if (s instanceof If)
		{
			Statement ifBody = ((If)s).getIfBody();
			Statement elseBody = ((If)s).getElseBody();
			return (containsBreak(ifBody) || containsBreak(elseBody));
		}
		if (s instanceof Sequence)
		{
			List<Statement> l = ((Sequence) s).getStatements();
			for (Statement ls : l)
				if (containsBreak(ls))
					return true;
			return false;
		}
		return false;
	}
	
	private AllObjects getGameObject() {
		return this.gameObject;
	}
	
	public void setGameObject(AllObjects obj){
		this.gameObject = obj;
	}
	
	public Statement getMainStatement() {
		return this.main;
	}
	
	public Map<String,Type> getGlobalVariables() {
		return this.global;
	}
	
	public void addVariable(String key, Type value) {
		getGlobalVariables().put(key,value);
	}
	
	private void setWellFormed(boolean bool){
		this.isWellFormed = bool;
	}
	
	public boolean isWellFormed() {
		return this.isWellFormed;
	}
	
	public void execute(Map<String,Type> globals, double time) {
		globals.put("this", new ObjectType(getGameObject()));
		globals.put("timer", new DoubleType(time));
		while (((DoubleType)globals.get("timer")).getValue() > 0)
		{
			if (getMainStatement().isDone())
				getMainStatement().resetDone();
			try {
				time = getMainStatement().evaluate(globals);
			} catch (BreakException exc) {
				globals.put("timer",new DoubleType());
			}
		}
	}

}
