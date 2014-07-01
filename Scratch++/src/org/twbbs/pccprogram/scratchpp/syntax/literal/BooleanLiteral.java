package org.twbbs.pccprogram.scratchpp.syntax.literal;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.Bool;

/**
 * Denote a C++ boolean-literal.
 * 
 * @author johnchen902
 */
public class BooleanLiteral extends Literal<Boolean> {

	/**
	 * Create a boolean-literal with the with the specified location and value.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the value of this boolean-literal
	 */
	public BooleanLiteral(int x, int y, boolean value) {
		super(x, y, value);
	}

	@Override
	public Type getType(RuntimeEnvironment env) {
		return Bool.TYPE;
	}

	@Override
	public Value evaluate(RuntimeEnvironment env) {
		return new Bool(value);
	}

	/**
	 * Create a {@code BooleanLiteral} with the specified location and use the
	 * boolean parsed from the specified {@code String}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param s
	 *            the string representation of this boolean-literal
	 * @return one with value <code>true</code> if <code>s</code> equals to
	 *         <code>"true"</code>; one with value <code>false</code> if
	 *         <code>s</code> equals to <code>"false"</code>
	 * @throws IllegalArgumentException
	 *             if <code>s</code> is neither <code>"true"</code> nor
	 *             <code>"false"</code>
	 */
	public static BooleanLiteral valueOf(int x, int y, String s) {
		if (s.equals("true"))
			return new BooleanLiteral(x, y, true);
		if (s.equals("false"))
			return new BooleanLiteral(x, y, false);
		throw new IllegalArgumentException("s is neither true nor false");
	}
}
