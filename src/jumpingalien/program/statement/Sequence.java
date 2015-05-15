package jumpingalien.program.statement;

import java.util.List;
import jumpingalien.part3.programs.SourceLocation;

public class Sequence extends Statement {

	public Sequence(SourceLocation loc,List<Statement> statements) {
		super(loc);
		this.statements = statements;
	}
	
	private List<Statement> statements;
	
	public List<Statement> getStatements() {
		return this.statements;
	}
	
	@Override
	public void evaluate() {
		getStatements().forEach(s->s.evaluate());
//		for (Statement s : getStatements())
//			s.evaluate();
	}

}
