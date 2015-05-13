package jumpingalien.part3.programs;

import java.util.List;
import java.util.Map;

import jumpingalien.program.*;
import jumpingalien.program.expression.Addition;
import jumpingalien.program.expression.And;
import jumpingalien.program.expression.Constant;
import jumpingalien.program.expression.Division;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.expression.GetHeight;
import jumpingalien.program.expression.GetHitpoints;
import jumpingalien.program.expression.GetTile;
import jumpingalien.program.expression.GetWidth;
import jumpingalien.program.expression.IsAir;
import jumpingalien.program.expression.IsMagma;
import jumpingalien.program.expression.IsPassable;
import jumpingalien.program.expression.IsTerrain;
import jumpingalien.program.expression.IsWater;
import jumpingalien.program.expression.LessThan;
import jumpingalien.program.expression.LessThanOrEqualTo;
import jumpingalien.program.expression.Multiplication;
import jumpingalien.program.expression.Not;
import jumpingalien.program.expression.Or;
import jumpingalien.program.expression.Random;
import jumpingalien.program.expression.ReadVariable;
import jumpingalien.program.expression.SearchObj;
import jumpingalien.program.expression.SquareRoot;
import jumpingalien.program.expression.StartDuck;
import jumpingalien.program.expression.StartJump;
import jumpingalien.program.expression.StartRun;
import jumpingalien.program.expression.StopDuck;
import jumpingalien.program.expression.StopJump;
import jumpingalien.program.expression.StopRun;
import jumpingalien.program.expression.Substraction;
import jumpingalien.program.program.Program;
import jumpingalien.program.statement.Assignment;
import jumpingalien.program.statement.Break;
import jumpingalien.program.statement.Foreach;
import jumpingalien.program.statement.Statement;
import jumpingalien.program.statement.Wait;
import jumpingalien.program.statement.While;
import jumpingalien.program.type.Type;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Type, Program> {

	@Override
	public Expression createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		return new ReadVariable(sourceLocation,variableName,variableType);
	}

	@Override
	public Expression createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new Constant<Double>(sourceLocation,value);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new Constant<Boolean>(sourceLocation,true);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new Constant<Boolean>(sourceLocation,false);
	}

	@Override
	public Expression createNull(SourceLocation sourceLocation) {
		return new Constant<Object>(sourceLocation,null);
	}

	@Override
	public Expression createSelf(SourceLocation sourceLocation) {
		return new Constant<Object>(sourceLocation,this);
	}

	@Override
	public Expression createDirectionConstant(jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new Constant<Direction>(sourceLocation,value);
	}

	@Override
	public Expression createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Addition(sourceLocation,left,right);
	}

	@Override
	public Expression createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Substraction(sourceLocation,left,right);
	}

	@Override
	public Expression createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(sourceLocation,left,right);
	}

	@Override
	public Expression createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(sourceLocation,left,right);
	}

	@Override
	public Expression createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new SquareRoot(sourceLocation,expr);
	}

	@Override
	public Expression createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new Random(sourceLocation,maxValue);
	}

	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new And(sourceLocation,left,right);
	}

	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Or(sourceLocation,left,right);
	}

	@Override
	public Expression createNot(Expression expr, SourceLocation sourceLocation) {
		return new Not(sourceLocation,expr);
	}

	@Override
	public Expression createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new LessThan(sourceLocation,left,right);
	}

	@Override
	public Expression createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new LessThanOrEqualTo(sourceLocation,left,right);
	}

	@Override
	public Expression createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetX(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetY(Expression expr, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetWidth(Expression expr,
			SourceLocation sourceLocation) {
		return new GetWidth(sourceLocation,expr);
	}

	@Override
	public Expression createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHeight(sourceLocation,expr);
	}

	@Override
	public Expression createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHitpoints(sourceLocation,expr);
	}

	@Override
	public Expression createGetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		return new GetTile(sourceLocation,x,y);
	}

	@Override
	public Expression createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		return new SearchObj(sourceLocation,direction);
	}

	@Override
	public Expression createIsMazub(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		return new IsTerrain(sourceLocation,expr);
	}

	@Override
	public Expression createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPassable(sourceLocation,expr);
	}

	@Override
	public Expression createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		return new IsWater(sourceLocation,expr);
	}

	@Override
	public Expression createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMagma(sourceLocation,expr);
	}

	@Override
	public Expression createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new IsAir(sourceLocation,expr);
	}

	@Override
	public Expression createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		return new Assignment(sourceLocation, variableName,variableType,value);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new While(sourceLocation,condition,body);
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new Foreach(sourceLocation,variableName,variableKind,where,sort,sortDirection,body);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StartRun(sourceLocation,direction);
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StopRun(sourceLocation,direction);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJump(sourceLocation);
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new StopJump(sourceLocation);
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new StartDuck(sourceLocation);
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new StopDuck(sourceLocation);
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		return new Wait(sourceLocation,duration);
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Wait(sourceLocation,new Constant<Double>(sourceLocation,0.001));
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getDoubleType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getBoolType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getGameObjectType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type getDirectionType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}
	
}
