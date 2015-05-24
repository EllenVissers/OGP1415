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
	
	@Override
	public void resetDone() {
		this.setDone(false);
		for (Statement s : getStatements())
			s.resetDone();
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
				s.setDone(true);
			}
		}
		this.setDone(true);
		return time;
	}
}
