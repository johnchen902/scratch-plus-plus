package org.twbbs.pccprogram.scratchpp.syntax.literal;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * The view of a C++ floating-literal.
 * 
 * @author johnchen902
 */
public class FloatingLiteral extends Literal<FloatingLiteralX> {

	/**
	 * Create a {@code FloatingLiteral} with the specified location and
	 * floating-literal.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the floating-literal
	 */
	public FloatingLiteral(int x, int y, FloatingLiteralX value) {
		super(x, y, value);
	}

	/**
	 * Create a {@code FloatingLiteral} with the specified location and use the
	 * floating-literal parsed from the specified {@code String}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code String} representation of the floating-literal
	 * @throws NumberFormatException
	 *             if the {@code String} cannot be parsed.
	 * @see FloatingLiteralX#valueOf(String)
	 */
	public FloatingLiteral(int x, int y, String value)
			throws NumberFormatException {
		super(x, y, FloatingLiteralX.valueOf(value));
	}

	/**
	 * Create a {@code FloatingLiteral} with the specified location and use the
	 * floating-literal parsed from the specified {@code double}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code double} representation of the floating-literal
	 * @see FloatingLiteralX#exactValueOf(double)
	 */
	public FloatingLiteral(int x, int y, double value) {
		super(x, y, FloatingLiteralX.exactValueOf(value));
	}

	/**
	 * Create a {@code FloatingLiteral} with the specified location and use the
	 * floating-literal parsed from the specified {@code float}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code float} representation of the floating-literal
	 * @see FloatingLiteralX#exactValueOf(float)
	 */
	public FloatingLiteral(int x, int y, float value) {
		super(x, y, FloatingLiteralX.exactValueOf(value));
	}

	/**
	 * Create a {@code FloatingLiteral} with the specified location and use the
	 * floating-literal parsed from the specified {@code double}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code double} representation of the floating-literal
	 * @see FloatingLiteralX#valueOf(double)
	 */
	public static FloatingLiteral valueOf(int x, int y, double value) {
		return new FloatingLiteral(x, y, FloatingLiteralX.valueOf(value));
	}

	/**
	 * Create a {@code FloatingLiteral} with the specified location and use the
	 * floating-literal parsed from the specified {@code float}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code float} representation of the floating-literal
	 * @see FloatingLiteralX#valueOf(float)
	 */
	public static FloatingLiteral valueOf(int x, int y, float value) {
		return new FloatingLiteral(x, y, FloatingLiteralX.valueOf(value));
	}

	@Override
	public Type getType(RuntimeEnvironment env) {
		return value.isFloat() ? Float.TYPE : Double.TYPE;
	}

	@Override
	public Value evaluate(RuntimeEnvironment env) {
		if (value.isFloat())
			return new Float(value.getValue().floatValue());
		else
			return new Double(value.getValue().doubleValue());
	}
}
