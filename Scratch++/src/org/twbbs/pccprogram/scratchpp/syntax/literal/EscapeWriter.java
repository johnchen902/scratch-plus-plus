package org.twbbs.pccprogram.scratchpp.syntax.literal;


/**
 * A utility to write a backslash-escaped string.
 * 
 * @author johnchen902
 */
public class EscapeWriter {
	private EscapeWriter() {
	}

	/**
	 * Turn a character to a string.
	 * 
	 * @param c
	 *            the character
	 * @return the <code>string</code> representation of the character
	 * @throws IllegalArgumentException
	 *             if <code>c</code> is out of the range of supported character
	 *             set
	 */
	public static String toString(char c) {
		if (c >= 256)
			throw new IllegalArgumentException("out of range of char");
		switch (c) {
		case '\'':
			return "\\\'";
		case '\"':
			return "\\\"";
		case '\\':
			return "\\\\";
		case '\007':
			return "\\a";
		case '\b':
			return "\\b";
		case '\f':
			return "\\f";
		case '\n':
			return "\\n";
		case '\r':
			return "\\r";
		case '\t':
			return "\\t";
		case '\013':
			return "\\v";
		default:
			if (c >= ' ' && c <= '~')
				return Character.toString(c);
			else
				return "\\" + Integer.toOctalString(c);
		}
	}
}
