package jumpingalien.program.statement;
import jumpingalien.part3.programs.SourceLocation;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Statement implements Iterable<Statement>{

	public Statement(SourceLocation location) {
		this.sourceLocation = location;
	}
	
	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public abstract void evaluate();

	@Override
	public Iterator<Statement> iterator() {
		return new Iterator<Statement>() {
			
			@Override
			public Statement next() {
				if (! this.hasNext()) {
					throw new NoSuchElementException();
				}
				Statement.this.Return();
				return Statement.this;
			}
			
			@Override
			public boolean hasNext() {
				return ! Statement.this.alreadyReturned();
			}
		};
	}
	
	private boolean returned;
	
	public boolean alreadyReturned() {
		return this.returned;
	}
	
	public void Return() {
		this.returned = true;
	}
}
