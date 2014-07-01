package org.twbbs.pccprogram.scratchpp.syntax.literal;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

/**
 * A utility to parse a backslash-escaped string.
 * 
 * @author johnchen902
 */
class EscapeReader {
	private PushbackReader in;
	private boolean singleQuoteDenied;
	private boolean doubleQuoteDenied;

	/**
	 * Create such a utility read from the specified string.
	 * 
	 * @param s
	 *            the string to read from
	 * @param singleQuoteDenied
	 *            <code>true</code> if single-quotes must be escaped;
	 *            <code>false</code> otherwise
	 * @param doubleQuoteDenied
	 *            <code>true</code> if double-quotes must be escaped;
	 *            <code>false</code> otherwise
	 */
	EscapeReader(String s, boolean singleQuoteDenied, boolean doubleQuoteDenied) {
		this.in = new PushbackReader(new StringReader(s));
		this.singleQuoteDenied = singleQuoteDenied;
		this.doubleQuoteDenied = doubleQuoteDenied;
	}

	/**
	 * Read the next character.
	 * 
	 * @return the <code>int</code> representation of the character read, or
	 *         <code>-1</code> if the end of the string is met.
	 * @throws IOException
	 *             if things cannot be parsed
	 */
	int read() throws IOException {
		int first = in.read();
		if (first == -1)
			return -1;
		if (first == '\n')
			throw new IOException("new line must be escaped");
		if (first == '\'' && singleQuoteDenied)
			throw new IOException("single quote must be escaped");
		if (first == '\"' && doubleQuoteDenied)
			throw new IOException("double quote must be escaped");
		if (first >= 256)
			throw new IOException("out of range of char");
		if (first != '\\')
			return first;

		// first == '\\'
		int second = in.read();
		switch (second) {
		case '\'':
			return '\'';
		case '\"':
			return '\"';
		case '\\':
			return '\\';
		case 'a':
			return '\007';
		case 'b':
			return '\b';
		case 'f':
			return '\f';
		case 'n':
			return '\n';
		case 'r':
			return '\r';
		case 't':
			return '\t';
		case 'v':
			return '\013';
		case 'x':
			throw new IOException(
					"hexadecimal escape sequence is not supported");
		case 'u':
		case 'U':
			throw new IOException("universal character name is not supported");
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
			int value = second - '0';
			int next;
			if ((next = in.read()) >= '0' && next <= '7') {
				value = value * 8 + next - '0';
				if ((next = in.read()) >= '0' && next <= '7')
					value = value * 8 + next - '0';
				else
					in.unread(value);
			} else {
				in.unread(value);
			}
			if (value >= 256)
				throw new IOException("out of range of char");
			else
				return value;
		case -1:
			throw new IOException("unexpected EOF");
		default:
			throw new IOException("unknown escape sequence \\" + second);
		}
	}
}
