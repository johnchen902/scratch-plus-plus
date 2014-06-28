package org.twbbs.pccprogram.scratchpp.syntax.statement;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Optional;

import org.twbbs.pccprogram.scratchpp.CompileException;
import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.syntax.Layout;
import org.twbbs.pccprogram.scratchpp.syntax.Symbol;
import org.twbbs.pccprogram.scratchpp.syntax.expression.Expression;

/**
 * A magical statement the prints out the specified argument with no restriction
 * of the type, which does not exist in the C++ programming language.
 * 
 * @author johnchen902
 */
public class MagicPrint extends Symbol implements Statement {

	private Layout layout;

	/**
	 * Create such a magical statement with the specified location.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 */
	public MagicPrint(int x, int y) {
		super(x, y, DIMENSION_IGNORE, DIMENSION_IGNORE, STATEMENT_COLOR);
		putLayout();
	}

	private void putLayout() {
		layout = new Layout()
				.addGap()
				.addString("print")
				.addExpression(
						() -> getInnersCount() == 0 ? Optional.empty()
								: Optional.of(getInner(0))).addGap();
	}

	@Override
	public int getWidth() {
		return layout.getWidth();
	}

	@Override
	public int getHeight() {
		return layout.getHeight();
	}

	@Override
	public boolean isDroppable(Symbol s, Point p) {
		return s instanceof Expression && getShape().contains(p)
				&& getInnersCount() == 0;
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
	public void paint(Graphics2D g) {
		super.paint(g);
		layout.paint(g, getX(), getY(), getWidth(), getHeight());
	}

	@Override
	public void execute(RuntimeEnvironment env) {
		if (getInnersCount() > 0) {
			env.write(((Expression) getInner(0)).evaluate(env)
					+ System.lineSeparator());
		} else {
			throw new CompileException("no expression in print statement");
		}
	}

	@Override
	public MagicPrint clone() {
		MagicPrint mp = (MagicPrint) super.clone();
		mp.putLayout();
		return mp;
	}
}
