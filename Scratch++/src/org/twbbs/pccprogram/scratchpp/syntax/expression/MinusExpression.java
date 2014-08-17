package org.twbbs.pccprogram.scratchpp.syntax.expression;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.UsualArithmeticConversion;
import org.twbbs.pccprogram.scratchpp.object.primitive.UsualOperators;

/**
 * Denote a C++ binary minus expression.
 * 
 * @author johnchen902
 */
public class MinusExpression extends BinaryExpression {

	/**
	 * Create a binary minus expression.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 */
	public MinusExpression(int x, int y) {
		super(x, y, "-");
	}

	@Override
	protected Type getType(Type lhs, Type rhs) {
		return UsualArithmeticConversion.getType(lhs, rhs);
	}

	@Override
	protected Value operate(Value lhs, Value rhs) {
		return UsualOperators.minus(lhs, rhs);
	}
}
