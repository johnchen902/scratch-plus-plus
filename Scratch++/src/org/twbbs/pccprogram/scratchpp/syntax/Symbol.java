package org.twbbs.pccprogram.scratchpp.syntax;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A symbol in the syntax of the C++ programming language.
 * 
 * @author johnchen902
 */
public abstract class Symbol implements Cloneable {

	/**
	 * Indicate that the getter of the width or the height is overridden and the
	 * argument passed to the constructor is trivial. No purpose other than a
	 * more understandable code as the arguments are required.
	 */
	protected static final int DIMENSION_IGNORE = 1;

	private int x, y, width, height;
	private Paint paint;

	private boolean valid;
	private Shape shape;

	private Symbol parent;
	private List<Symbol> inners;

	/**
	 * Create a symbol with the specified location, size and paint.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param width
	 *            the width
	 * @param height
	 *            the height
	 * @param paint
	 *            the {@code Paint} used to color this symbol
	 */
	public Symbol(int x, int y, int width, int height, Paint paint) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("non-positive dimension");
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.paint = Objects.requireNonNull(paint, "paint");
		this.inners = new ArrayList<>();
	}

	/**
	 * Get the X coordinate of the left of this symbol
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the X coordinate of the left of this symbol
	 * 
	 * @param x
	 *            the X coordinate
	 */
	public void setX(int x) {
		this.x = x;
		invalidate(false);
	}

	/**
	 * Get the Y coordinate of the top of this symbol
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the Y coordinate of the top of this symbol
	 * 
	 * @param y
	 *            the Y coordinate
	 */
	public void setY(int y) {
		this.y = y;
		invalidate(false);
	}

	/**
	 * Get the width of this symbol
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of this symbol
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get the {@link Paint} used to color this symbol
	 */
	public Paint getPaint() {
		return paint;
	}

	/**
	 * Set the {@link Paint} used to color this symbol
	 * 
	 * @param paint
	 *            the {@link Paint}
	 */
	public void setPaint(Paint paint) {
		this.paint = Objects.requireNonNull(paint);
	}

	/**
	 * Get the inner symbols contained in this symbol.
	 * 
	 * @return a list of the inner symbols
	 */
	public List<Symbol> getInners() {
		return Collections.unmodifiableList(inners);
	}

	/**
	 * Get the inner symbol contained in this symbol at the specified index.
	 * 
	 * @param the
	 *            specified index
	 * @return the inner symbol at {@code index}
	 */
	public Symbol getInner(int index) {
		return inners.get(index);
	}

	/**
	 * Get the number of inner symbols contained in this symbol.
	 * 
	 * @return the number of inner symbols
	 */
	public int getInnersCount() {
		return inners.size();
	}

	/**
	 * Add an symbol to this symbol at the specified location.
	 * 
	 * @param inner
	 *            the symbol to add
	 * @param p
	 *            the location to add at
	 * @throws NullPointerException
	 *             if {@code inner} or {@code p} is <code>null</code>
	 * @throws IllegalStateException
	 *             if is<code>isDroppable</code> is false
	 * @throws IllegalArgumentException
	 *             if {@code inner} already has a parent
	 * @throws IllegalArgumentException
	 *             if {@code inner} is an ancestor of this symbol
	 */
	public void add(Symbol inner, Point p) throws IllegalStateException {
		Objects.requireNonNull(inner, "the symbol to add");
		Objects.requireNonNull(p, "the location");

		if (!isDroppable(inner, p))
			throw new IllegalStateException("cannot drop here");
		add(inner, inners.size());
	}

	/**
	 * Add an symbol to this symbol at the specified index
	 * 
	 * @param inner
	 *            the symbol to add
	 * @param i
	 *            the index to add at
	 * @throws NullPointerException
	 *             if {@code inner} is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if {@code inner} already has a parent
	 * @throws IllegalArgumentException
	 *             if {@code inner} is an ancestor of this symbol
	 */
	protected void add(Symbol inner, int i) {
		Objects.requireNonNull(inner, "the symbol to add");

		if (inner.parent != null)
			throw new IllegalArgumentException("the symbol has parent");

		for (Symbol sb = this; sb != null; sb = sb.parent)
			if (sb == inner)
				throw new IllegalArgumentException(
						"the symbol is an ancestor of this symbol");

		inners.add(i, inner);
		inner.parent = this;

		invalidate(true);
	}

	/**
	 * Remove an symbol from to this symbol.
	 * 
	 * @param inner
	 *            the symbol to remove
	 * @throws NullPointerException
	 *             if {@code inner} is <code>null</code>
	 * @throws IllegalArgumentException
	 *             if {@code inner} is not this symbol's children
	 */
	public void remove(Symbol inner) {
		Objects.requireNonNull(inner, "the symbol to remove");
		if (!inners.contains(inner))
			throw new IllegalArgumentException(
					"the symbol is not this symbol's children");

		inner.parent = null;
		inners.remove(inner);

		invalidate(true);
	}

	/**
	 * Paint the symbol on a {@code Graphics2D}.
	 * 
	 * @param g
	 *            the graphics to be painted on
	 */
	public void paint(Graphics2D g) {
		validate();
		assert isValid();

		g.setPaint(getPaint());
		g.fill(getShape());
		g.setColor(Color.GRAY);
		g.draw(getShape());
		inners.forEach(i -> i.paint(g));
	}

	/**
	 * Get the shape of the symbol.
	 * 
	 * @return the shape of the symbol
	 */
	public Shape getShape() {
		validate();
		return shape;
	}

	/**
	 * Get the union of the shapes of this symbol and its children's.
	 * 
	 * @return the union
	 */
	public Shape getAllShape() {
		validate();
		if (inners.isEmpty()) {
			return shape;
		} else {
			Area a = new Area(shape);
			inners.forEach(i -> a.add(new Area(i.getAllShape())));
			return a;
		}
	}

	/**
	 * Get the parent of this symbol.
	 * 
	 * @return the parent
	 */
	public Symbol getParent() {
		return parent;
	}

	/**
	 * Check whether the symbol's properties are valid.
	 * 
	 * @return <code>true</code> if valid, <code>false</code> otherwise
	 */
	protected boolean isValid() {
		return valid;
	}

	/**
	 * Invalidate the symbol's properties.
	 * 
	 * @param invalidateParent
	 *            whether its parent need to be invalidated
	 */
	protected void invalidate(boolean invalidateParent) {
		if (valid) {
			valid = false;
			shape = null;
			if (invalidateParent && parent != null)
				parent.invalidate(true);
		}
	}

	/**
	 * Validate the symbol's properties.
	 */
	protected void validate() {
		if (!valid) {
			valid = true;
			shape = computeShape();
		}
	}

	/**
	 * Compute the shape of the symbol.
	 * 
	 * @return the shape of the symbol
	 */
	protected Shape computeShape() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		final int b = 3;

		Path2D.Double path = new Path2D.Double();
		// (0, 0)
		path.moveTo(0, b);
		path.lineTo(b, 0);
		// (w, 0)
		path.lineTo(w - b, 0);
		path.lineTo(w, b);
		// (w, h)
		path.lineTo(w, h - b);
		path.lineTo(w - b, h);
		// (0, h)
		path.lineTo(b, h);
		path.lineTo(0, h - b);

		path.closePath();
		return AffineTransform.getTranslateInstance(x, y)
				.createTransformedShape(path);
	}

	/**
	 * Return whether a symbol can be dropped at the location.
	 * 
	 * @param s
	 *            the symbol to be added
	 * @param p
	 *            the location to be added at
	 * @return always <code>false</code> unless overridden
	 * @throws NullPointerException
	 *             if {@code s} or {@code p} is <code>null</code>
	 */
	public boolean isDroppable(Symbol s, Point p) {
		Objects.requireNonNull(s);
		Objects.requireNonNull(p);
		return false;
	}

	/**
	 * Return a deep clone of this symbol.
	 * 
	 * @return a deep clone
	 */
	@Override
	public Symbol clone() {
		try {
			Symbol clone = (Symbol) super.clone();
			clone.parent = null;
			clone.inners = new ArrayList<>();
			for (Symbol inner : inners) {
				Symbol innerClone = inner.clone();
				innerClone.parent = clone;
				clone.inners.add(innerClone);
				innerClone.invalidate(false);
			}
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new Error("WTF", e);
		}
	}
}
