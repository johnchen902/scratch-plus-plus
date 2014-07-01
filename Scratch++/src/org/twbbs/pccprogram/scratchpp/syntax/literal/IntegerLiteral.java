package org.twbbs.pccprogram.scratchpp.syntax.literal;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.SignedInt;
import org.twbbs.pccprogram.scratchpp.object.primitive.SignedLong;
import org.twbbs.pccprogram.scratchpp.object.primitive.SignedLongLong;
import org.twbbs.pccprogram.scratchpp.object.primitive.UnsignedInt;
import org.twbbs.pccprogram.scratchpp.object.primitive.UnsignedLong;
import org.twbbs.pccprogram.scratchpp.object.primitive.UnsignedLongLong;

/**
 * The view of a C++ integer-literal, including the sign.
 * 
 * @author johnchen902
 */
public class IntegerLiteral extends Literal<IntegerLiteralX> {

	/**
	 * Create a {@code IntegerLiteral} with the specified location and
	 * integer-literal.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the integer-literal
	 */
	public IntegerLiteral(int x, int y, IntegerLiteralX value) {
		super(x, y, value);
	}

	/**
	 * Create a {@code IntegerLiteral} with the specified location and use the
	 * integer-literal parsed from the specified {@code String}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code String} representation of the integer-literal
	 * @throws NumberFormatException
	 *             if the {@code String} cannot be parsed.
	 */
	public IntegerLiteral(int x, int y, String value)
			throws NumberFormatException {
		this(x, y, IntegerLiteralX.parseIntegerLiteralX(value));
	}

	/**
	 * Create a {@code IntegerLiteral} with the specified location and use the
	 * integer-literal parsed from the specified {@code int}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code int} representation of the integer-literal
	 */
	public IntegerLiteral(int x, int y, int value) {
		this(x, y, IntegerLiteralX.valueOf(value));
	}

	/**
	 * Create a {@code IntegerLiteral} with the specified location and use the
	 * integer-literal parsed from the specified {@code long}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code long} representation of the integer-literal
	 */
	public IntegerLiteral(int x, int y, long value) {
		this(x, y, IntegerLiteralX.valueOf(value));
	}

	@Override
	public Type getType(RuntimeEnvironment env) {
		if (!value.isUnsigned()) {
			switch (value.getType()) {
			case INT:
				return SignedInt.TYPE;
			case LONG:
				return SignedLong.TYPE;
			case LONGLONG:
				return SignedLongLong.TYPE;
			}
		} else {
			switch (value.getType()) {
			case INT:
				return UnsignedInt.TYPE;
			case LONG:
				return UnsignedLong.TYPE;
			case LONGLONG:
				return UnsignedLongLong.TYPE;
			}
		}
		throw new RuntimeException("impossible");
	}

	@Override
	public Value evaluate(RuntimeEnvironment env) {
		if (!value.isUnsigned()) {
			switch (value.getType()) {
			case INT:
				return new SignedInt((int) value.getValue());
			case LONG:
				return new SignedLong((int) value.getValue());
			case LONGLONG:
				return new SignedLongLong(value.getValue());
			}
		} else {
			switch (value.getType()) {
			case INT:
				return new UnsignedInt((int) value.getValue());
			case LONG:
				return new UnsignedLong((int) value.getValue());
			case LONGLONG:
				return new UnsignedLongLong(value.getValue());
			}
		}
		throw new RuntimeException("impossible");
	}
}
