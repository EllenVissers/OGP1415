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
	}
	
	public boolean checkWellFormed(Statement main) {
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
	
	public boolean containsActionStatement(Statement s) {
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
	
	public boolean containsBreak(Statement s) {
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
	
	public AllObjects getGameObject() {
		return this.gameObject;
	}
	
	public void setGameObject(AllObjects obj){
		this.gameObject = obj;
	}

	private Statement main;
	private Map<String,Type> global;
	private AllObjects gameObject;
	
	public Statement getMainStatement() {
		return this.main;
	}
	
	public Map<String,Type> getGlobalVariables() {
		return this.global;
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
