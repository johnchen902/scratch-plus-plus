package org.twbbs.pccprogram.scratchpp.syntax;

import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.List;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.syntax.statement.Statement;

/**
 * A simple block shape.
 * 
 * @author johnchen902
 */
public class Block extends Symbol implements Statement {
	private int heightTop, widthLeft, heightBottom, heightEmpty;

	/**
	 * Create a simple block shape with the specified boundary.
	 */
	public Block(int x, int y, int width, Paint paint) {
		this(x, y, width, 20, 20, 20, 20, paint);
	}

	/**
	 * Create a simple block shape with the specified boundary and arm size.
	 * 
	 * @param heightTop
	 *            the height of the top arm
	 * @param widthLeft
	 *            the width of the left arm
	 * @param heightBottom
	 *            the height of the bottom arm
	 */
	public Block(int x, int y, int width, int heightEmpty, int heightTop,
			int widthLeft, int heightBottom, Paint paint) {
		super(x, y, width, DIMENSION_IGNORE, paint);
		this.heightEmpty = heightEmpty;
		this.heightTop = heightTop;
		this.widthLeft = widthLeft;
		this.heightBottom = heightBottom;
	}

	@Override
	protected void validate() {
		boolean valid = isValid();
		super.validate();
		if (!valid) {
			int yy = getY() + getHeightTop();
			for (Symbol inner : getInners().subList(getBlockStart(),
					getBlockEnd())) {
				inner.setX(getX() + getWidthLeft());
				inner.setY(yy);
				yy += inner.getHeight();
			}
		}
	}

	@Override
	public int getHeight() {
		List<Symbol> inners = getInners();
		if (inners.subList(getBlockStart(), getBlockEnd()).isEmpty())
			return getHeightEmpty() + getHeightTop() + getHeightBottom();
		else
			return inners.subList(getBlockStart(), getBlockEnd()).stream()
					.mapToInt(Symbol::getHeight).sum()
					+ getHeightTop() + getHeightBottom();
	}

	/**
	 * Get the height of empty area.
	 */
	public int getHeightEmpty() {
		return heightEmpty;
	}

	/**
	 * Get the height of the top arm.
	 */
	public int getHeightTop() {
		return heightTop;
	}

	/**
	 * Get the width of the left arm.
	 */
	public int getWidthLeft() {
		return widthLeft;
	}

	/**
	 * Get the height of the bottom arm.
	 */
	public int getHeightBottom() {
		return heightBottom;
	}

	@Override
	public void add(Symbol inner, Point p) {
		int id;
		for (id = getBlockStart(); id < getInnersCount() && id < getBlockEnd(); id++)
			if (getInner(id).getY() + getInner(id).getHeight() / 2 >= p.y)
				break;
		add(inner, id);
	}

	/**
	 * Get the start (including) of the range of the inner symbols that should
	 * be contained in the block.
	 * 
	 * @return {@code 0} unless overridden
	 */
	protected int getBlockStart() {
		return 0;
	}

	/**
	 * Get the end (excluding) of the range of the inner symbols that should be
	 * contained in the block.
	 * 
	 * @return {@code getInnersCount()} unless overridden
	 * @see #getInnersCount()
	 */
	protected int getBlockEnd() {
		return getInnersCount();
	}

	@Override
	protected Shape computeShape() {
		int x = getX();
		int y = getY();
		int w = getWidth();
		int h = getHeight();
		int a1 = getHeightTop();
		int a2 = getWidthLeft();
		int a3 = getHeightBottom();
		final int b = 3;

		Path2D.Double path = new Path2D.Double();
		// (0, 0)
		path.moveTo(0, b);
		path.lineTo(b, 0);
		// (w, 0)
		path.lineTo(w - b, 0);
		path.lineTo(w, b);
		// (w, a1)
		path.lineTo(w, a1 - b);
		path.lineTo(w - b, a1);
		// (a2, a1)
		path.lineTo(a2 + b, a1);
		path.lineTo(a2, a1 + b);
		// (a2, h - a3)
		path.lineTo(a2, h - a3 - b);
		path.lineTo(a2 + b, h - a3);
		// (w, h - a3)
		path.lineTo(w - b, h - a3);
		path.lineTo(w, h - a3 + b);
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

	@Override
	public boolean isDroppable(Symbol s, Point p) {
		if (s instanceof Statement) {
			Rectangle rect = new Rectangle(getX(), getY() + getHeightTop() / 2,
					getWidth(), getHeight() - getHeightTop() / 2
							- getHeightBottom() / 2);
			return rect.contains(p);
		} else {
			return false;
		}
	}

	@Override
	public void execute(RuntimeEnvironment env) {
		for (int i = getBlockStart(); i < getBlockEnd(); i++)
			((Statement) getInner(i)).execute(env);
	}
}
