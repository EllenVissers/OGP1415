package jumpingalien.program.statement;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.program.expression.Expression;
import jumpingalien.program.type.*;
import jumpingalien.model.*;

import java.util.stream.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

/**
 * A statement that executes the given body 
 * with the given variable set to all objects of the given kind 
 * for which the where-expression evaluates to true, 
 * sorted by the result of the given sort expression in the given direction. 
 * The where- and sort-expressions are optional, and can be null;
 * */
@SuppressWarnings("all")
public class Foreach extends Statement {
	
	public Foreach(SourceLocation loc, String variableName, Kind variableKind,
			Expression where, Expression sort, SortDirection sortDirection,
			Statement body) {
		super(loc);
		this.forEachCounter = 0;
		this.name = variableName;
		this.kind = variableKind;
		this.where = where;
		this.sort = sort;
		this.direction = sortDirection;
		this.body = body;
	}

	private String name;
	private Kind kind;
	private Expression where;
	private Expression sort;
	private SortDirection direction;
	private Statement body;
	private int forEachCounter;
	
	private String getVariableName() {
		return this.name;
	}
	
	private Kind getVariableKind() {
		return this.kind;
	}
	
	private Expression getWhere() {
		return this.where;
	}
	
	private Expression getSort() {
		return this.sort;
	}
	
	private SortDirection getSortDirection() {
		return this.direction;
	}
	
	public Statement getBody() {
		return this.body;
	}
	
	private int getForEachCounter() {
		return this.forEachCounter;
	}
	
	private void setForEachCounter(int c) {
		this.forEachCounter = c;
	}
	
	@Override
	public void resetDone() {
		this.setDone(false);
		getBody().resetDone();
	}
	
	private void evalWhere(Type t, Expression exp, List<Type> l, Map<String,Type> globals) {
		Type old = globals.get("this");
		globals.put("this",t);
		if ((boolean) exp.evaluate(globals))
			l.add(t);
		globals.put("this", old);
	}
	
	private void evalSort(Type t, Expression exp, Map<Double,List<Type>> m, Map<String,Type> globals) {
		Type old = globals.get("this");
		globals.put("this",t);
		double key = (double) getSort().evaluate(globals);
		if (m.containsKey(key))
		{
			List<Type> value = m.get(key);
			value.add(t);
			m.put(key,value);
		}
		else
		{
			List<Type> l = new ArrayList<Type>();
			l.add(t);
			m.put(key, l);
		}
		globals.put("this", old);
	}
	
	private Map<Double,List<Type>> sort(Map<Double,List<Type>> unsorted, SortDirection dir) {
		if (dir == SortDirection.ASCENDING)
			return new TreeMap<Double,List<Type>>(unsorted);
		else
		{
			Map<Double,List<Type>> s;
			s =  new TreeMap<Double,List<Type>>(Collections.reverseOrder());
			s.putAll(unsorted);
			return s;
		}
	}
	
	private void checkKind(Type t, Kind k, ArrayList<Type> l) {
		if ((t.getValue() instanceof AllObjects) && (((AllObjects) t.getValue()).isKind(kind)))
			l.add(t);
	}
	
	@Override
	public double evaluate(Map<String,Type> globals) throws BreakException {
		double time = (double) globals.get("timer").getValue();
		ArrayList<Type> all = new ArrayList<Type>(globals.values());
		Kind kind = getVariableKind();
		final ArrayList<Type> newall = new ArrayList<Type>();
		all.forEach(t->checkKind(t,kind,newall));
		all = newall;
		if (getWhere() != null)
		{
			ArrayList<Type> where = new ArrayList<Type>();
			all.forEach(t->evalWhere(t,getWhere(),where,globals));
			all = where;
		}
		if (getSort() != null)
		{
			Type old = globals.get("this");
			Map<Double,List<Type>> unsorted = new HashMap<Double,List<Type>>();
			all.forEach(t->evalSort(t,getSort(),unsorted,globals));
			Map<Double,List<Type>> sorted = sort(unsorted,getSortDirection());
			ArrayList<Type> sortlist = new ArrayList<Type>();
			for (List<Type> l : sorted.values())
				for (Type t : l)
					sortlist.add(t);
			all = sortlist;
		}
		String name = getVariableName();
		Type old = globals.get(name);
		ArrayList<Type> sublist = new ArrayList(all.subList(getForEachCounter(), all.size()));
		for (Type t : sublist)
		{
			if (getBody().isDone())
				getBody().resetDone();
			try {
				globals.put(name, t);
				time = getBody().evaluate(globals);
				globals.put(name,old);
			} catch (BreakException exc) {
				time = exc.getTime();
				globals.put(name,old);
				if (time == 0)
					throw new BreakException(0);
			}
			try {
				time = checkTime(time);
				globals.put(name,old);
				globals.put("timer",new DoubleType(time));
			} catch (TerminateException exc) {
				globals.put(name,old);
				setForEachCounter(all.indexOf(t));
				globals.put("timer",new DoubleType());
				throw new BreakException(0);
			}
		}
		this.setDone(true);
		return time;
	}
}

