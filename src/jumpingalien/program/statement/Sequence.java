package jumpingalien.program.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jumpingalien.part3.programs.SourceLocation;

import java.util.Map;

import jumpingalien.program.type.DoubleType;
import jumpingalien.program.type.Type;

public class Sequence extends Statement {

	public Sequence(SourceLocation loc,List<Statement> statements) {
		super(loc);
		Stream<Statement> filteredStream = statements.stream().filter(s->(s!=null));
		ArrayList<Statement> filteredList = filteredStream.collect(Collectors.toCollection(ArrayList::new));
		this.statements = filteredList;
		this.SequenceCounter = 0;
	}
	
	private List<Statement> statements;
	private int SequenceCounter;
	
	public List<Statement> getStatements() {
		return this.statements;
	}
	
	public int getSequenceCounter() {
		return this.SequenceCounter;
	}
	
	public void setSequenceCounter(int c) {
		this.SequenceCounter = c;
	}

	public Statement getStatementAt(int i) throws IndexOutOfBoundsException{
		if((i<0 || i >= getStatements().size()))
			throw new IndexOutOfBoundsException();
		else
			return getStatements().get(i);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals, double time, int counter) {
		if (counter == getStatementCounter())
		{
			ArrayList<Statement> list = new ArrayList<Statement>(getStatements().subList(getSequenceCounter(), getStatements().size()));
			for (Statement s : list)
			{
				try {
					double timer = (double) globals.get("timer").getValue();
					globals.put("timer", new DoubleType(timer-0.001));
					time = checkTime(s.evaluate(globals,time,counter),s);
				} catch (BreakException exc) {
					time = exc.getTime();
				} catch (TerminateException exc) {
					setSequenceCounter(getStatements().indexOf(s));
				}
			}
			resetCounter();
			setSequenceCounter(0);
		}
		return time;
	}

}
