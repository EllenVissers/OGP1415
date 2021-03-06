package jumpingalien.part3.programs;
import java.util.List;
import java.util.Map;
import jumpingalien.program.type.*;
import jumpingalien.program.expression.*;
import jumpingalien.program.program.Program;
import jumpingalien.program.statement.*;
import jumpingalien.model.*;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Type, Program> {

	/**
	 * An expression that evaluates to the value of the variable with the given
	 * name and declared type
	 */
	@Override
	public Expression createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		return new ReadVariable(sourceLocation,variableName,variableType);
	}

	/** An expression that evaluates to the given numeric value */
	@Override
	public Expression createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new Constant<Double>(sourceLocation,value);
	}

	/** An expression that evaluates to true */
	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new Constant<Boolean>(sourceLocation,true);
	}

	/** An expression that evaluates to false */
	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new Constant<Boolean>(sourceLocation,false);
	}

	/** An expression that evaluates to null */
	@Override
	public Expression createNull(SourceLocation sourceLocation) {
		return new Constant<Object>(sourceLocation,null);
	}

	/**
	 * An expression that evaluates to the game object that is executing the
	 * program
	 */
	@Override
	public Expression createSelf(SourceLocation sourceLocation) {
		return new Self(sourceLocation);
	}

	/** An expression that evaluates to the given direction */
	@Override
	public Expression createDirectionConstant(jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new Constant<Direction>(sourceLocation,value);
	}

	/** An expression that evaluates to the sum of the given expressions */
	@Override
	public Expression createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Double>(sourceLocation,left,right,(a,b)->(a+b));
	}

	/** An expression that evaluates to the difference of the given expressions */
	@Override
	public Expression createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Double>(sourceLocation,left,right,(a,b)->a-b);
	}

	/** An expression that evaluates to the product of the given expressions */
	@Override
	public Expression createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Double>(sourceLocation,left,right,(a,b)->a*b);
	}

	/** An expression that evaluates to the division of the given expressions */
	@Override
	public Expression createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Double>(sourceLocation,left,right,(a,b)->a/b);
	}

	/** An expression that evaluates to the square root of the given expressions */
	@Override
	public Expression createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new UnaryExpression<Double,Double>(sourceLocation,expr,t->Math.sqrt(t));
	}

	/**
	 * An expression that evaluates to a random value between 0 (inclusive) and
	 * the given maximum value (exclusive)
	 */
	@Override
	public Expression createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new UnaryExpression<Double,Double>(sourceLocation,maxValue,t->Math.random()*t);
	}

	/** An expression that evaluates to the conjunction of the given expressions */
	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Boolean,Boolean,Boolean>(sourceLocation,left,right,(a,b)->(a&&b));
	}

	/** An expression that evaluates to the disjunction of the given expressions */
	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Boolean,Boolean,Boolean>(sourceLocation,left,right,(a,b)->(a||b));
	}

	/** An expression that evaluates to the negation of the given expression */
	@Override
	public Expression createNot(Expression expr, SourceLocation sourceLocation) {
		return new UnaryExpression<Boolean,Boolean>(sourceLocation,expr,t->!t);
	}

	/**
	 * An expression that evaluates to true if the value of the left expression
	 * is less than the value of the right expression
	 */
	@Override
	public Expression createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Boolean>(sourceLocation,left,right,(a,b)->(a<b));
	}

	/**
	 * An expression that evaluates to true if the value of the left expression
	 * is less than or equal to the value of the right expression
	 */
	@Override
	public Expression createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Boolean>(sourceLocation,left,right,(a,b)->(a<=b));
	}

	/**
	 * An expression that evaluates to true if the value of the left expression
	 * is greater than the value of the right expression
	 */
	@Override
	public Expression createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Boolean>(sourceLocation,left,right,(a,b)->(a>b));
	}

	/**
	 * An expression that evaluates to true if the value of the left expression
	 * is greater than or equal to the value of the right expression
	 */
	@Override
	public Expression createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new BinaryExpression<Double,Double,Boolean>(sourceLocation,left,right,(a,b)->(a>=b));
	}

	/**
	 * An expression that evaluates to true if the value of the left expression
	 * equals the value of the right expression
	 */
	@Override
	public Expression createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Object,Object,Boolean>(sourceLocation,left,right,(a,b)->a==b);
	}

	/**
	 * An expression that evaluates to true if the value of the left expression
	 * does not equal the value of the right expression
	 */
	@Override
	public Expression createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new BinaryExpression<Object,Object,Boolean>(sourceLocation,left,right,(a,b)->(a!=b));
	}

	/**
	 * An expression that evaluates to the x-value of the object obtained from
	 * the given expression
	 */
	@Override
	public Expression createGetX(Expression expr, SourceLocation sourceLocation) {
		return new UnaryExpression<AllObjects,Double>(sourceLocation,expr,t->t.getXPosition());
	}

	/**
	 * An expression that evaluates to the y-value of the object obtained from
	 * the given expression
	 */
	@Override
	public Expression createGetY(Expression expr, SourceLocation sourceLocation) {
		return new UnaryExpression<AllObjects,Double>(sourceLocation,expr,t->t.getYPosition());
	}

	/**
	 * An expression that evaluates to the width of the object obtained from
	 * the given expression
	 */
	@Override
	public Expression createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<AllObjects,Double>(sourceLocation,expr,t->t.getWidth());
	}

	/**
	 * An expression that evaluates to the height of the object obtained from
	 * the given expression
	 */
	@Override
	public Expression createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<AllObjects,Double>(sourceLocation,expr,t->t.getHeight());
	}

	/**
	 * An expression that evaluates to the number of hitpoints of the object
	 * obtained from the given expression
	 */
	@Override
	public Expression createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Double>(sourceLocation,expr,t->(double)t.getHitPoints());
	}

	/**
	 * An expression that evaluates to the tile in which the pixel with
	 * coordinates (x, y) lies.
	 */
	@Override
	public Expression createGetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		return new GetTile(sourceLocation,x,y);
	}

	/**
	 * An expression that evaluates to the first object that is encountered in
	 * the given direction
	 */
	@Override
	public Expression createSearchObject(Expression direction, SourceLocation sourceLocation) {
		return new SearchObj(sourceLocation,direction);
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is a Mazub
	 */
	@Override
	public Expression createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->(t instanceof Mazub));
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is a Shark
	 */
	@Override
	public Expression createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->(t instanceof Shark));
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is a Slime
	 */
	@Override
	public Expression createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->(t instanceof Slime));
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is a Plant
	 */
	@Override
	public Expression createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->(t instanceof Plant));
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is dead
	 */
	@Override
	public Expression createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->t.isTerminated());
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is part of the terrain
	 */
	@Override
	public Expression createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<AllObjects,Boolean>(sourceLocation,expr,t->(t instanceof Tile));
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is passable
	 */
	@Override
	public Expression createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<Tile,Boolean>(sourceLocation,expr,t->t.isPassable());
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is water
	 */
	@Override
	public Expression createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<Tile,Boolean>(sourceLocation,expr,t->t.isWater());
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is magma
	 */
	@Override
	public Expression createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<Tile,Boolean>(sourceLocation,expr,t->t.isMagma());
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is air
	 */
	@Override
	public Expression createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new UnaryExpression<Tile,Boolean>(sourceLocation,expr,t->t.isAir());
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is moving
	 */
	@Override
	public Expression createIsMoving(Expression expr, Expression direction,SourceLocation sourceLocation) {
		return new BinaryExpression<GameObject,IProgramFactory.Direction,Boolean>(sourceLocation,expr,direction,(t,d)->t.isMoving(d));
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is ducking
	 */
	@Override
	public Expression createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->t.isDucking());
	}

	/**
	 * An expression that evaluates to true if the object obtained from the
	 * given expression is jumping
	 */
	@Override
	public Expression createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		return new UnaryExpression<GameObject,Boolean>(sourceLocation,expr,t->t.isJumping());
	}

	/**
	 * A statement that changes the value of the variable with the given name
	 * and declared type to the value obtained from the given expression
	 */
	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		return new Assignment(sourceLocation,variableName,variableType,value);
	}

	/**
	 * A statement that executes the given body while the given condition
	 * evaluates to true
	 */
	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new While(sourceLocation,condition,body);
	}

	/**
	 * A statement that executes the given body with the given variable set to
	 * all objects of the given kind for which the where-expression evaluates to
	 * true, sorted by the result of the given sort expression in the given
	 * direction. The where- and sort-expressions are optional, and can be null;
	 * */
	@Override
	public Statement createForEach(String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new Foreach(sourceLocation,variableName,variableKind,where,sort,sortDirection,body);
	}

	/** A statement that terminates the currently executing loop */
	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	/**
	 * A statement that executes the given ifBody if the condition evaluates to
	 * true, and the given elseBody otherwise.
	 * The elseBody is optional, and can be null.
	 */
	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		return new If(sourceLocation,condition,ifBody,elseBody);
	}

	/** A statement that prints the value of the given expression */
	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(sourceLocation,value);
	}

	/** A statement that makes the object executing the program start moving */
	@Override
	public Statement createStartRun(Expression direction,SourceLocation sourceLocation) {
		return new ActionStatement<GameObject,Void>(sourceLocation,direction,(t,d)->t.startMove(d));
	}

	/** A statement that makes the object executing the program stop moving */
	@Override
	public Statement createStopRun(Expression direction,SourceLocation sourceLocation) {
		return new ActionStatement<GameObject,Void>(sourceLocation,direction,(t,d)->t.endMove(d));
	}

	/** A statement that makes the object executing the program start jumping */
	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new ActionStatement<GameObject,Void>(sourceLocation,t->t.startJump());
	}

	/** A statement that makes the object executing the program stop jumping */
	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new ActionStatement<GameObject,Void>(sourceLocation,t->t.endJump());
	}

	/** A statement that makes the object executing the program start ducking */
	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new ActionStatement<GameObject,Void>(sourceLocation,t->t.startDuck());
	}

	/** A statement that makes the object executing the program stop ducking */
	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new ActionStatement<GameObject,Void>(sourceLocation,t->t.endDuck());
	}

	/**
	 * A statement that suspends the execution of the program for the given
	 * duration
	 */
	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		return new Wait(sourceLocation,duration); //nog doen
	}

	/** A statement that does nothing */
	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Wait(sourceLocation,new Constant<Double>(sourceLocation,0.001)); //nog doen
	}

	/** A statement that executes of a list of statements subsequently */
	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new Sequence(sourceLocation, statements);
	}

	/** The type of double values and variables */
	@Override
	public Type getDoubleType() {
		return new DoubleType();
	}

	/** The type of boolean values and variables */
	@Override
	public Type getBoolType() {
		return new BoolType();
	}

	/** The type of game object values and variables */
	@Override
	public Type getGameObjectType() {
		return new ObjectType();
	}

	/** The type of direction values and variables */
	@Override
	public Type getDirectionType() {
		return new DirectionType();
	}

	/**
	 * Create a program with the given main statement and variable declarations.
	 * The globalVariables map contains the type for each declared variable,
	 * with the name of the variable as the key.
	 */
	@Override
	public Program createProgram(Statement mainStatement, Map<String, Type> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}
	
}