package org.twbbs.pccprogram.scratchpp.syntax.function;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Path2D;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.SignedInt;
import org.twbbs.pccprogram.scratchpp.syntax.Block;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.statement.Statement;

/**
 * The main function.
 * 
 * @author johnchen902
 */
public class MainFunction extends Block implements Function {

	/**
	 * Create a main function with the specified location.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 */
	public MainFunction(int x, int y) {
		super(x, y, 70, 20, 30, 20, 20, FUNCTION_COLOR);
	}

	@Override
	public void paint(Graphics2D g) {
		super.paint(g);

		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(Font.BOLD));

		g.drawString("main", getX() + 10, getY() + 20);
	}

	private static Shape createArc(double w, double h) {
		double r = h / 2 + w * w / (8 * h);
		double t1 = Math.toDegrees(Math.atan2(r - h, -w / 2));
		double t2 = Math.toDegrees(Math.atan2(r - h, +w / 2));
		Arc2D arc = new Arc2D.Double(w / 2 - r, 0, 2 * r, 2 * r, t1, t2 - t1,
				Arc2D.OPEN);
		return arc;
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
		int c1 = a1 / 3;
		int c2 = w * 2 / 3;

		Path2D.Double path = new Path2D.Double();
		// (0, c1)
		path.moveTo(0, c1);
		// (c2, c1)
		path.append(createArc(c2, c1), true);
		// (w, c1)
		path.lineTo(w - b, c1);
		path.lineTo(w, c1 + b);
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
	public Type[] getArgumentsType() {
		return new Type[0];
	}

	@Override
	public Type getReturnType() {
		return SignedInt.TYPE;
	}

	@Override
	public Value invoke(RuntimeEnvironment env, Value... args) {
		for (Symbol symbol : getInners()) {
			Statement s = (Statement) symbol;
			s.execute(env);
		}
		return new SignedInt();
	}
}
