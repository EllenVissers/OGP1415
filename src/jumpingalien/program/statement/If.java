package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.Constant;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class If extends Statement {

	public If(SourceLocation loc, Expression condition, Statement ifBody, Statement elseBody) {
		super(loc);
		this.condition = condition;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}
	
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;
	private boolean result;
	
	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
	
	public Expression getCondition() {
		return this.condition;
	}
	
	public Statement getIfBody() {
		return this.ifBody;
	}
	
	public Statement getElseBody() {
		return this.elseBody;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evaluate() {
		if (((Constant<Boolean>) getCondition()).evaluate())
			getIfBody().evaluate();
		else
			getElseBody().evaluate();
	}
	
	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>() {
			
			private final Iterator<Statement> ifStatementIterator = getIfBody().iterator();
			
			private final Iterator<Statement> elseStatementIterator = getElseBody().iterator();
			
			public Iterator<Statement> getIfStatementIterator() {
				return this.ifStatementIterator;
			}
			
			public Iterator<Statement> getElseStatementIterator() {
				return this.elseStatementIterator;
			}
			
			@Override
			public Statement next() {
				if (! this.hasNext()) {
					throw new NoSuchElementException();
				}
				if (! If.this.alreadyReturned()){
					If.this.Return();
					return If.this;
				}
				if (If.this.getResult()) {
					return this.getIfStatementIterator().next();
				}
				else {
					return this.getElseStatementIterator().next();
				}
				
			}
			
			@Override
			public boolean hasNext() {
				if (If.this.getResult())
					return this.getIfStatementIterator().hasNext();
				else
					return this.getElseStatementIterator().hasNext();
			}
		};
	}

}
