package org.twbbs.pccprogram.scratchpp.syntax.literal;

import java.io.IOException;
import java.util.Objects;

/**
 * A model of a C++ character-literal.
 * 
 * @author johnchen902
 */
public class CharacterLiteralX {
	private final char charValue;
	private final String sequence;

	private CharacterLiteralX(char charValue, String sequence) {
		this.charValue = charValue;
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "'" + sequence + "'";
	}

	/**
	 * Get the character represented by this literal.
	 * 
	 * @return its character value
	 */
	public char getCharValue() {
		return charValue;
	}

	/**
	 * Get the character sequence used to represent this literal, excluding the
	 * quotes.
	 * 
	 * @return its string representation
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * Create a character literal representing the specified character
	 * 
	 * @param c
	 *            the character to be represented
	 * @return a character literal representing <code>c</code>
	 */
	public static CharacterLiteralX valueOf(char c) {
		return new CharacterLiteralX(c, EscapeWriter.toString(c));
	}

	/**
	 * Create a character literal with specified character sequence, excluding
	 * the quotes.
	 * 
	 * @param s
	 *            the character sequence
	 * @return the character literal represented by <code>s</code>
	 */
	public static CharacterLiteralX valueOf(String s) {
		Objects.requireNonNull(s);
		EscapeReader er = new EscapeReader(s, true, false);
		int c;
		try {
			c = er.read();
		} catch (IOException e) {
			throw new NumberFormatException(e.getMessage());
		}
		if (c == -1)
			throw new NumberFormatException("empty character");
		try {
			if (er.read() != -1)
				throw new NumberFormatException(
						"multicharacter literal is not supported");
		} catch (IOException e) {
			throw new NumberFormatException(e.getMessage()
					+ " and multicharacter literal is not supported");
		}
		return new CharacterLiteralX((char) c, s);
	}

	/**
	 * Create a character literal with specified character sequence, including
	 * the quotes.
	 * 
	 * @param s
	 *            the character sequence
	 * @return the character literal represented by <code>s</code>
	 */
	public static CharacterLiteralX parseCharacterLiteralX(String s) {
		if (s.startsWith("L"))
			throw new NumberFormatException(
					"wide-character literal is not supported");
		if (s.length() < 2 || s.charAt(0) != '\''
				|| s.charAt(s.length() - 1) != '\'')
			throw new NumberFormatException(
					"s must be surrounded by single-quotes");
		return valueOf(s.substring(1, s.length() - 1));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + charValue;
		result = prime * result + sequence.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CharacterLiteralX))
			return false;
		CharacterLiteralX other = (CharacterLiteralX) obj;
		if (charValue != other.charValue)
			return false;
		if (!sequence.equals(other.sequence))
			return false;
		return true;
	}
}
