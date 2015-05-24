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
	}
	
	private List<Statement> statements;
	
	public List<Statement> getStatements() {
		return this.statements;
	}
	
	public void resetDone() {
		this.setDone(false);
		for (Statement s : getStatements())
			s.resetDone();
	}

	protected Statement getStatementAt(int i) throws IndexOutOfBoundsException{
		if((i<0 || i >= getStatements().size()))
			throw new IndexOutOfBoundsException();
		else
			return getStatements().get(i);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		ArrayList<Statement> list = new ArrayList<Statement>();
		list.addAll(getStatements());
		for (Statement s : list)
		{
			if (! s.isDone())
			{
				try {
					time = s.evaluate(globals);
				} catch (BreakException exc) {
					time = exc.getTime();
					if (time == 0)
						throw new BreakException(0);
				} 
				try {
					time = checkTime(time);
					globals.put("timer",new DoubleType(time));
				} catch (TerminateException exc) {
					globals.put("timer",new DoubleType());
					throw new BreakException(0);
				}
			}
			s.setDone(true);
		}
		this.setDone(true);
		return time;
	}
	
//	@Override
//	public double evaluate(Map<String,Type> globals, int counter) throws BreakException {
//		double time = (double) globals.get("timer").getValue();
////		if (counter == getStatementCounter())
////		{
//			ArrayList<Statement> list = new ArrayList<Statement>(getStatements().subList(getSequenceCounter(), getStatements().size()));
//			for (Statement s : list)
//			{
//				if (! s.isDone())
//				{
//					try {
//						time = s.evaluate(globals,counter);
//					} catch (BreakException exc) {
//						time = exc.getTime();
//					}
//					try {
//						time = checkTime(time,s);
//						resetCounter(this);
//						setSequenceCounter(0);
//						//s.setDone(true);
//						globals.put("timer", new DoubleType(time));
//						if (globals.get("this").getValue() instanceof Plant)
//							System.out.println(globals.get("this").getValue() + " finished: " + s);
//					} catch (TerminateException exc) {
//						setSequenceCounter(getStatements().indexOf(s));
//						globals.put("timer", new DoubleType());
//						if (globals.get("this").getValue() instanceof Plant)
//							System.out.println(globals.get("this").getValue() + " not finished: " + s);
//						throw new BreakException(0);
//					}
//				}
//				s.setDone(true);
//			}
//			this.setDone(true);
////		}
//		return time;
//	}

}
