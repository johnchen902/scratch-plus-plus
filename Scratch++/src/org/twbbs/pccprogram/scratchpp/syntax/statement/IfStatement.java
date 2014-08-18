package org.twbbs.pccprogram.scratchpp.syntax.statement;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;

import org.twbbs.pccprogram.scratchpp.CompileException;
import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.Bool;
import org.twbbs.pccprogram.scratchpp.object.primitive.PrimitiveConversion;
import org.twbbs.pccprogram.scratchpp.syntax.Block;
import org.twbbs.pccprogram.scratchpp.syntax.Layout;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.expression.Expression;

/**
 * An <code>if</code> statement.
 * 
 * @author johnchen902
 */
public class IfStatement extends Block implements Statement {

	private boolean hasCondition;

	private Layout layout;

	/**
	 * Create an <code>if</code> statement with the specified location.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 */
	public IfStatement(int x, int y) {
		super(x, y, DIMENSION_IGNORE, 20, DIMENSION_IGNORE, 20, 20,
				STATEMENT_COLOR);
		putLayout();
	}

	private void putLayout() {
		layout = new Layout().addGap().addString("if").addGap()
				.addExpression(() -> Optional.ofNullable(getCondition()))
				.addGap();
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		layout.paint(g, getX(), getY(), getWidth(), getHeightTop());
	}

	@Override
	public int getWidth() {
		return layout.getWidth();
	}

	@Override
	public int getHeightTop() {
		return layout.getHeight();
	}

	@Override
	protected void validate() {
		boolean valid = isValid();
		super.validate();
		if (!valid) {
			layout.layout(getX(), getY(), getWidth(), getHeightTop());
		}
	}

	private boolean inConditionBounds(Point p) {
		return new Rectangle(getX(), getY(), getWidth(), getHeightTop())
				.contains(p);
	}

	@Override
	public void add(Symbol s, Point p) {
		if (s instanceof Expression && !hasCondition && inConditionBounds(p)) {
			super.add(s, 0);
			hasCondition = true;
		} else {
			super.add(s, p);
		}
	}

	@Override
	public void remove(Symbol inner) {
		boolean conditionRemoved = inner == getCondition();
		super.remove(inner);
		if (conditionRemoved)
			hasCondition = false;
	}

	private Symbol getCondition() {
		return hasCondition ? getInner(0) : null;
	}

	@Override
	protected int getBlockStart() {
		return hasCondition ? 1 : 0;
	}

	@Override
	public boolean isDroppable(Symbol s, Point p) {
		if (super.isDroppable(s, p))
			return true;
		if (s instanceof Expression && !hasCondition && inConditionBounds(p))
			return true;
		return false;
	}

	@Override
	public void execute(RuntimeEnvironment env) {
		if (!hasCondition)
			throw new CompileException("no condition");
		Expression exp = (Expression) getCondition();
		Value val = exp.evaluate(env);
		val = PrimitiveConversion.convert(Bool.TYPE, val);
		if (((Bool) val).getValue())
			super.execute(env);
	}

	@Override
	public Symbol clone() {
		IfStatement ifStatement = (IfStatement) super.clone();
		ifStatement.putLayout();
		return ifStatement;
	}
}
