package org.twbbs.pccprogram.scratchpp.syntax.expression;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;
import java.util.Optional;

import org.twbbs.pccprogram.scratchpp.CompileException;
import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.syntax.Layout;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;

/**
 * Denote a binary expression.
 * 
 * @author johnchen902
 */
public abstract class BinaryExpression extends Symbol implements Expression {

	private boolean hasLeftHandSide;
	private boolean hasRightHandSide;

	private Layout layout;
	private final String operator;

	/**
	 * Create a binary expression.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param operator
	 *            the string representation of this operator
	 */
	protected BinaryExpression(int x, int y, String operator) {
		super(x, y, DIMENSION_IGNORE, DIMENSION_IGNORE, EXPRESSION_COLOR);
		this.operator = Objects.requireNonNull(operator);
		putLayout();
	}

	private void putLayout() {
		layout = new Layout().addGap()
				.addExpression(() -> Optional.ofNullable(getLeftHandSide()))
				.addGap().addString(operator).addGap()
				.addExpression(() -> Optional.ofNullable(getRightHandSide()))
				.addGap();
	}

	@Override
	public int getWidth() {
		return layout.getWidth();
	}

	@Override
	public int getHeight() {
		return layout.getHeight();
	}

	private Symbol getLeftHandSide() {
		if (hasLeftHandSide)
			return getInner(0);
		else
			return null;
	}

	private Symbol getRightHandSide() {
		if (hasRightHandSide)
			return getInner(hasLeftHandSide ? 1 : 0);
		else
			return null;
	}

	private int getMid() {
		return getX() + getWidth() / 2;
	}

	@Override
	public boolean isDroppable(Symbol s, Point p) {
		Objects.requireNonNull(s);
		Objects.requireNonNull(p);

		return s instanceof Expression && getShape().contains(p)
				&& (!hasLeftHandSide || !hasRightHandSide);
	}

	private boolean goesLeft(Point p) {
		if (hasLeftHandSide)
			return false;
		if (hasRightHandSide)
			return true;
		return p.x < getMid();
	}

	@Override
	public void add(Symbol inner, Point p) throws IllegalStateException {
		Objects.requireNonNull(inner);
		Objects.requireNonNull(p);

		if (!isDroppable(inner, p))
			throw new IllegalStateException("cannot drop here");

		if (goesLeft(p)) {
			assert !hasLeftHandSide;
			add(inner, 0);
			hasLeftHandSide = true;
		} else {
			assert !hasRightHandSide;
			add(inner, hasLeftHandSide ? 1 : 0);
			hasRightHandSide = true;
		}
	}

	@Override
	public void remove(Symbol inner) {
		int i = getInners().indexOf(inner);
		super.remove(inner);
		if (i == 0 && hasLeftHandSide)
			hasLeftHandSide = false;
		else if (i != -1 && hasRightHandSide)
			hasRightHandSide = false;
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		layout.paint(g, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	protected void validate() {
		boolean valid = isValid();
		super.validate();
		if (!valid) {
			layout.layout(getX(), getY(), getWidth(), getHeight());
		}
	}

	@Override
	public Type getType(RuntimeEnvironment env) {
		if (!hasLeftHandSide)
			throw new CompileException("no left hand side");
		if (!hasRightHandSide)
			throw new CompileException("no right hand side");
		Expression lhsexp = (Expression) getLeftHandSide();
		Expression rhsexp = (Expression) getRightHandSide();
		Type lhs = lhsexp.getType(env);
		Type rhs = rhsexp.getType(env);
		return getType(lhs, rhs);
	}

	/**
	 * Deduce the type of this expression from its left-hand-side operand and
	 * right-hand-side operand.
	 * 
	 * @param lhs
	 *            the type of the left-hand-side operand
	 * @param rhs
	 *            the type of the right-hand-side operand
	 * @return the type of this expression
	 */
	protected abstract Type getType(Type lhs, Type rhs);

	@Override
	public Value evaluate(RuntimeEnvironment env) {
		if (!hasLeftHandSide)
			throw new CompileException("no left hand side");
		if (!hasRightHandSide)
			throw new CompileException("no right hand side");
		Expression lhsexp = (Expression) getLeftHandSide();
		Expression rhsexp = (Expression) getRightHandSide();
		Value lhs = lhsexp.evaluate(env);
		Value rhs = rhsexp.evaluate(env);
		return operate(lhs, rhs);
	}

	/**
	 * Compute the value of this expression from its left-hand-side operand and
	 * right-hand-side operand.
	 * 
	 * @param lhs
	 *            the value of the left-hand-side operand
	 * @param rhs
	 *            the value of the right-hand-side operand
	 * @return the value of this expression
	 */
	protected abstract Value operate(Value lhs, Value rhs);

	@Override
	protected Shape computeShape() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		return new RoundRectangle2D.Double(x, y, w, h, h, h);
	}

	@Override
	public BinaryExpression clone() {
		BinaryExpression ce = (BinaryExpression) super.clone();
		ce.putLayout();
		return ce;
	}
}
