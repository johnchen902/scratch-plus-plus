package org.twbbs.pccprogram.scratchpp.syntax.literal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

import org.twbbs.pccprogram.scratchpp.FontManager;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.expression.Expression;

/**
 * Denote a C++ literal.
 * 
 * @author johnchen902
 *
 * @param <T>
 *            the underling object of this literal
 */
public abstract class Literal<T> extends Symbol implements Expression {

	protected final T value;

	/**
	 * Create a literal with the specified with the specified location and
	 * underling object.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the underling object
	 */
	protected Literal(int x, int y, T value) {
		super(x, y, (int) FontManager.getStringWidth(value.toString()) + 16,
				16, EXPRESSION_COLOR);
		this.value = Objects.requireNonNull(value);
	}

	/**
	 * Get the underling object.
	 * 
	 * @return the underling object
	 */
	public T getValue() {
		return value;
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(FontManager.getFont());
		g.drawString(value.toString(), getX() + 9, getY() + 12);
	}

	@Override
	protected Shape computeShape() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		return new RoundRectangle2D.Double(x, y, w, h, h, h);
	}
}
