package org.twbbs.pccprogram.scratchpp.syntax;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.twbbs.pccprogram.scratchpp.FontManager;

/**
 * A layout manager of a horizontal symbol.
 * 
 * @author johnchen902
 */
public class Layout {
	private interface Obj {
		int getWidth();

		int getHeight();

		default void layout(Rectangle rect) {
		}

		void paint(Graphics2D g, Rectangle rect);
	}

	private List<Obj> objs = new ArrayList<>();

	/**
	 * Add a gap of <code>2</code>.
	 * 
	 * @return this<code>this</code>
	 */
	public Layout addGap() {
		return addGap(2);
	}

	/**
	 * Add a gap of <code>pixel</code>.
	 * 
	 * @return <code>this</code>
	 */
	public Layout addGap(int pixel) {
		objs.add(new Obj() {
			@Override
			public int getWidth() {
				return pixel;
			}

			@Override
			public int getHeight() {
				return 0;
			}

			@Override
			public void paint(Graphics2D g, Rectangle rect) {
			}
		});
		return this;
	}

	/**
	 * Add a string.
	 * 
	 * @param str
	 *            the string
	 * @return <code>this</code>
	 */
	public Layout addString(String str) {
		Objects.requireNonNull(str);
		return drawString(() -> str);
	}

	/**
	 * Add a string.
	 * 
	 * @param sup
	 *            the supplier of the string
	 * @return <code>this</code>
	 */
	public Layout drawString(Supplier<String> sup) {
		Objects.requireNonNull(sup);
		objs.add(new Obj() {
			@Override
			public int getWidth() {
				return FontManager.getStringWidth(sup.get());
			}

			@Override
			public int getHeight() {
				return FontManager.getStringHeight(sup.get());
			}

			@Override
			public void paint(Graphics2D g, Rectangle rect) {
				g.setColor(Color.BLACK);
				g.setFont(FontManager.getFont());
				String str = sup.get();
				g.drawString(str, rect.x, rect.y + (rect.height - getHeight())
						/ 2 + g.getFontMetrics().getAscent());
			}
		});
		return this;
	}

	/**
	 * Add a expression.
	 * 
	 * @param sup
	 *            the supplier of the expression
	 * @return <code>this</code>
	 */
	public Layout addExpression(Supplier<Optional<Symbol>> sup) {
		objs.add(new Obj() {
			@Override
			public int getWidth() {
				return sup.get().map(Symbol::getWidth).orElse(32);
			}

			@Override
			public int getHeight() {
				return sup.get().map(Symbol::getHeight).orElse(16);
			}

			@Override
			public void layout(Rectangle rect) {
				Optional<Symbol> opt = sup.get();
				if (opt.isPresent()) {
					Symbol sym = opt.get();
					sym.setX(rect.x);
					sym.setY(rect.y + (rect.height - getHeight()) / 2);
				}
			}

			@Override
			public void paint(Graphics2D g, Rectangle rect) {
				Optional<Symbol> opt = sup.get();
				if (!opt.isPresent()) {
					g.setColor(Color.GRAY);
					g.drawRoundRect(rect.x, rect.y + (rect.height - 16) / 2,
							32, 16, 16, 16);
				}
			}
		});
		return this;
	}

	/**
	 * Get the width computed.
	 * 
	 * @return the width in pixels
	 */
	public int getWidth() {
		return objs.stream().mapToInt(Obj::getWidth).sum();
	}

	/**
	 * Get the height computed.
	 * 
	 * @return the height in pixels
	 */
	public int getHeight() {
		return objs.stream().mapToInt(Obj::getHeight).max().orElse(0) + 4;
	}

	/**
	 * Settle the inner symbols' position.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 */
	public void layout(int x, int y, int w, int h) {
		layout(new Rectangle(x, y, w, h));
	}

	/**
	 * Settle the inner symbols' position.
	 * 
	 * @param bound
	 *            the bound
	 */
	public void layout(Rectangle bound) {
		objs.forEach(o -> {
			o.layout(bound);
			bound.x += o.getWidth();
			bound.width -= o.getWidth();
		});
	}

	/**
	 * Paint on the graphics
	 * 
	 * @param g
	 *            the graphics
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 * @param w
	 *            the width
	 * @param h
	 *            the height
	 */
	public void paint(Graphics2D g, int x, int y, int w, int h) {
		paint(g, new Rectangle(x, y, w, h));
	}

	/**
	 * Paint on the graphics
	 * 
	 * @param g
	 *            the graphics
	 * @param bound
	 *            the bound
	 */
	public void paint(Graphics2D g, Rectangle bound) {
		objs.forEach(o -> {
			o.paint(g, bound);
			bound.x += o.getWidth();
			bound.width -= o.getWidth();
		});
	}
}
