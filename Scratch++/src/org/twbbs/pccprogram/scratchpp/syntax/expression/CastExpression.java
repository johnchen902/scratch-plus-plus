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
import org.twbbs.pccprogram.scratchpp.object.primitive.PrimitiveConversion;
import org.twbbs.pccprogram.scratchpp.syntax.Layout;
import org.twbbs.pccprogram.scratchpp.syntax.PrimitiveType;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;

/**
 * Denote a C-style cast expression.
 * 
 * @author johnchen902
 */
public class CastExpression extends Symbol implements Expression {

	private boolean hasType;
	private boolean hasExpr;

	private Layout layout;

	/**
	 * Create a C-style cast.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 */
	public CastExpression(int x, int y) {
		super(x, y, DIMENSION_IGNORE, DIMENSION_IGNORE, EXPRESSION_COLOR);
		putLayout();
	}

	private void putLayout() {
		layout = new Layout().addGap().addString("(")
				.addExpression(() -> Optional.ofNullable(getTypeSymbol()))
				.addGap().addString(")").addGap()
				.addExpression(() -> Optional.ofNullable(getExprSymbol()))
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

	private Symbol getTypeSymbol() {
		if (hasType)
			return getInner(0);
		else
			return null;
	}

	private Symbol getExprSymbol() {
		if (hasExpr)
			return getInner(hasType ? 1 : 0);
		else
			return null;
	}

	@Override
	public boolean isDroppable(Symbol s, Point p) {
		Objects.requireNonNull(s);
		Objects.requireNonNull(p);

		if (getShape().contains(p)) {
			if (s instanceof PrimitiveType && !hasType)
				return true;
			if (s instanceof Expression && !hasExpr)
				return true;
		}
		return false;
	}

	@Override
	public void add(Symbol inner, Point p) throws IllegalStateException {
		Objects.requireNonNull(inner);
		Objects.requireNonNull(p);

		if (!isDroppable(inner, p))
			throw new IllegalStateException("cannot drop here");
		if (inner instanceof PrimitiveType && !hasType) {
			add(inner, 0);
			hasType = true;
		} else if (inner instanceof Expression && !hasExpr) {
			add(inner, hasType ? 1 : 0);
			hasExpr = true;
		}
	}

	@Override
	public void remove(Symbol inner) {
		int i = getInners().indexOf(inner);
		super.remove(inner);
		if (i == 0 && hasType)
			hasType = false;
		else if (i != -1 && hasExpr)
			hasExpr = false;
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
		if (!hasType)
			throw new CompileException("no type in cast expression");
		if (!hasExpr)
			throw new CompileException("no expression in cast expression");
		return ((PrimitiveType) getTypeSymbol()).getType();
	}

	@Override
	public Value evaluate(RuntimeEnvironment env) {
		if (!hasType)
			throw new CompileException("no type in cast expression");
		if (!hasExpr)
			throw new CompileException("no expression in cast expression");
		Type type = getType(env);
		Value val = ((Expression) getExprSymbol()).evaluate(env);
		if (PrimitiveConversion.isPrimitiveType(type)
				&& PrimitiveConversion.isPrimitiveType(val.getType())) {
			return PrimitiveConversion.convert(type, val);
		}
		throw new CompileException("cannot cast " + val + " to " + type);
	}

	@Override
	protected Shape computeShape() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		return new RoundRectangle2D.Double(x, y, w, h, h, h);
	}

	@Override
	public CastExpression clone() {
		CastExpression ce = (CastExpression) super.clone();
		ce.putLayout();
		return ce;
	}
}
