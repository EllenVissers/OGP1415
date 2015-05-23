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
	
	private int getSequenceCounter() {
		return this.SequenceCounter;
	}
	
	private void setSequenceCounter(int c) {
		this.SequenceCounter = c;
	}

	protected Statement getStatementAt(int i) throws IndexOutOfBoundsException{
		if((i<0 || i >= getStatements().size()))
			throw new IndexOutOfBoundsException();
		else
			return getStatements().get(i);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		if (counter == getStatementCounter())
		{
			ArrayList<Statement> list = new ArrayList<Statement>(getStatements().subList(getSequenceCounter(), getStatements().size()));
			for (Statement s : list)
			{
				try {
					time = s.evaluate(globals,counter);
				} catch (BreakException exc) {
					time = exc.getTime();
				}
				try {
					time = checkTime(time,s);
					resetCounter();
					setSequenceCounter(0);
					globals.put("timer", new DoubleType(time));
				} catch (TerminateException exc) {
					setSequenceCounter(getStatements().indexOf(s));
					globals.put("timer", new DoubleType());
					throw new BreakException(0);
				}
			}
		}
		return time;
	}

}
