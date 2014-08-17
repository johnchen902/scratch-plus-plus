package org.twbbs.pccprogram.scratchpp.syntax.expression;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.UsualArithmeticConversion;
import org.twbbs.pccprogram.scratchpp.object.primitive.UsualOperators;

/**
 * Denote a C++ binary add expression.
 * 
 * @author johnchen902
 */
public class AddExpression extends BinaryExpression {

	/**
	 * Create a binary add expression.
	 * 
	 * @param x
	 *            the X coordinate
	 * @param y
	 *            the Y coordinate
	 */
	public AddExpression(int x, int y) {
		super(x, y, "+");
	}

	@Override
	protected Type getType(Type lhs, Type rhs) {
		return UsualArithmeticConversion.getType(lhs, rhs);
	}

	@Override
	protected Value operate(Value lhs, Value rhs) {
		return UsualOperators.add(lhs, rhs);
	}
}
