package jumpingalien.program.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import jumpingalien.part3.programs.SourceLocation;
import java.util.Map;
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

	public Statement getStatementAt(int i) throws IndexOutOfBoundsException{
		if((i<0 || i >= getStatements().size()))
			throw new IndexOutOfBoundsException();
		else
			return getStatements().get(i);
	}
	
	@Override
	public void evaluate(Map<String,Type> globals, double time) {
		for (Statement s : getStatements())
		{
			try {
				s.evaluate(globals,time);
			} catch (BreakException exc) {
			}
		}
	}

}
