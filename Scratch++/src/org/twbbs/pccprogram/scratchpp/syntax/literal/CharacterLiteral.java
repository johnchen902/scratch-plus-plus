package org.twbbs.pccprogram.scratchpp.syntax.literal;

import org.twbbs.pccprogram.scratchpp.Interpreter.RuntimeEnvironment;
import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;
import org.twbbs.pccprogram.scratchpp.object.primitive.Char;

/**
 * The view of a C++ character-literal.
 * 
 * @author johnchen902
 */
public class CharacterLiteral extends Literal<CharacterLiteralX> {

	/**
	 * Create a {@code CharacterLiteral} with the specified location and
	 * character-literal.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the character-literal
	 */
	public CharacterLiteral(int x, int y, CharacterLiteralX value) {
		super(x, y, value);
	}

	/**
	 * Create a {@code CharacterLiteral} with the specified location and use the
	 * character-literal parsed from the specified {@code String}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code String} representation of the character-literal
	 * @throws NumberFormatException
	 *             if the {@code String} cannot be parsed.
	 */
	public CharacterLiteral(int x, int y, String value)
			throws NumberFormatException {
		super(x, y, CharacterLiteralX.parseCharacterLiteralX(value));
	}

	/**
	 * Create a {@code CharacterLiteral} with the specified location and use the
	 * character-literal parsed from the specified {@code char}.
	 * 
	 * @param x
	 *            the X coordination
	 * @param y
	 *            the Y coordination
	 * @param value
	 *            the {@code char} representation of the character-literal
	 * @throws IllegalArgumentException
	 *             if the {@code char} is out of the supported character set
	 */
	public CharacterLiteral(int x, int y, char value)
			throws IllegalArgumentException {
		super(x, y, CharacterLiteralX.valueOf(value));
	}

	@Override
	public Type getType(RuntimeEnvironment env) {
		return Char.TYPE;
	}

	@Override
	public Value evaluate(RuntimeEnvironment env) {
		return new Char((byte) value.getCharValue());
	}
}
