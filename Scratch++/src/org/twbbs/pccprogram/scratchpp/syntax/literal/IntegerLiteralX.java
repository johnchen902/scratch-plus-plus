package org.twbbs.pccprogram.scratchpp.syntax.literal;

import java.util.Objects;

/**
 * A model of a C++ integer-literal, including the sign.
 * 
 * @author johnchen902
 */
public class IntegerLiteralX {

	/**
	 * Denote the base of an integer-literal.
	 * 
	 * @author johnchen902
	 */
	public enum Base {
		/**
		 * Base 10.
		 */
		DECIMAL(10),
		/**
		 * Base 8
		 */
		OCTAL(8),
		/**
		 * Base 16
		 */
		HEXADECIMAL(16);

		private final int base;

		private Base(int base) {
			this.base = base;
		}

		/**
		 * Get the numeric value of the base.
		 * 
		 * @return <code>10</code>, <code>8</code> or <code>16</code>
		 */
		public int getBase() {
			return base;
		}
	}

	/**
	 * Denote the type of an integer-literal.
	 * 
	 * @author johnchen902
	 */
	public enum Type {
		/**
		 * <code>int</code>
		 */
		INT,
		/**
		 * <code>long</code>
		 */
		LONG,
		/**
		 * <code>long long</code>
		 */
		LONGLONG
	}

	private final Base base;
	private final Type type;
	private final long value;
	private final boolean unsigned;

	/**
	 * Create a integer-literal with the specified properties.
	 * 
	 * @param base
	 *            the base of the integer-literal
	 * @param type
	 *            the type of the integer-literal
	 * @param value
	 *            the value of the integer-literal
	 * @param unsigned
	 *            <code>true</code> if unsigned; <code>false</code> if signed
	 */
	public IntegerLiteralX(Base base, Type type, long value, boolean unsigned) {
		this.base = Objects.requireNonNull(base);
		this.type = Objects.requireNonNull(type);
		this.value = value;
		this.unsigned = unsigned;
		if (value == 0 && base == Base.DECIMAL)
			throw new IllegalArgumentException("0 cannot be a decimal integer");
		if (type == Type.INT || type == Type.LONG) {
			if (unsigned) {
				if (value < 0 || value > 1L << 32)
					throw new IllegalArgumentException(
							"value is out of range of unsigned " + type);
			} else {
				if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE)
					throw new IllegalArgumentException(
							"value is out of range of " + type);
			}
		}
	}

	/**
	 * @return the base of the integer-literal
	 */
	public Base getBase() {
		return base;
	}

	/**
	 * @return the type of the integer-literal
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the value of the integer-literal
	 */
	public long getValue() {
		return value;
	}

	/**
	 * Get whether the integer-literal is unsigned
	 * 
	 * @return <code>true</code> if unsigned; <code>false</code> if signed
	 */
	public boolean isUnsigned() {
		return unsigned;
	}

	/**
	 * Return an {@code IntegerLiteralX} representing the specified {@code int}
	 * 
	 * @param x
	 *            the specified {@code int}
	 */
	public static IntegerLiteralX valueOf(int x) {
		if (x == 0)
			return new IntegerLiteralX(Base.OCTAL, Type.INT, x, false);
		else
			return new IntegerLiteralX(Base.DECIMAL, Type.INT, x, false);
	}

	/**
	 * Return an {@code IntegerLiteralX} representing the specified {@code long}
	 * 
	 * @param x
	 *            the specified {@code long}
	 */
	public static IntegerLiteralX valueOf(long x) {
		if (x == 0)
			return new IntegerLiteralX(Base.OCTAL, Type.LONGLONG, x, false);
		else
			return new IntegerLiteralX(Base.DECIMAL, Type.LONGLONG, x, false);
	}

	/**
	 * Parses the string as a {@code IntegerLiteralX}, as defined in the C++
	 * programming language.
	 * 
	 * @param s
	 *            the {@code String} containing the {@code IntegerLiteralX}
	 *            representation to be parsed.
	 * @return the {@code IntegerLiteralX} represented by the string argument.
	 * @throws NumberFormatException
	 *             if the string does not contain a parsable
	 *             {@code IntegerLiteralX}.
	 */
	public static IntegerLiteralX parseIntegerLiteralX(String s)
			throws NumberFormatException {
		final String raw = Objects.requireNonNull(s);

		if (s.isEmpty())
			throw new NumberFormatException("Empty string");

		boolean unsigned = false;
		Type type = Type.INT;
		if (s.endsWith("u") || s.endsWith("U")) {
			unsigned = true;
			s = s.substring(0, s.length() - 1);
			if (s.endsWith("ll") || s.endsWith("LL")) {
				type = Type.LONGLONG;
				s = s.substring(0, s.length() - 2);
			} else if (s.endsWith("l") || s.endsWith("L")) {
				type = Type.LONG;
				s = s.substring(0, s.length() - 1);
			}
		} else if (s.endsWith("l") || s.endsWith("L")) {
			if (s.endsWith("ll") || s.endsWith("LL")) {
				type = Type.LONGLONG;
				s = s.substring(0, s.length() - 2);
			} else {
				type = Type.LONG;
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith("u") || s.endsWith("U")) {
				unsigned = true;
				s = s.substring(0, s.length() - 1);
			}
		}

		Base base = Base.DECIMAL;
		if (s.startsWith("0x")) {
			base = Base.HEXADECIMAL;
			s = s.substring(2);
		} else if (s.startsWith("0")) {
			base = Base.OCTAL;
			if (s.length() > 1)
				s = s.substring(1);
		}

		long value;
		try {
			if (unsigned)
				value = Long.parseUnsignedLong(s, base.getBase());
			else
				value = Long.parseLong(s, base.getBase());
		} catch (NumberFormatException e) {
			if (s.equals(raw))
				throw new NumberFormatException("Cannot parse \"" + s + "\"");
			else
				throw new NumberFormatException("Cannot parse \"" + s
						+ "\" in \"" + raw + "\"");
		}

		if (type == Type.INT || type == Type.LONG) {
			long min, max;
			if (unsigned) {
				min = 0;
				max = (1L << 32) - 1;
			} else {
				min = Integer.MIN_VALUE;
				max = Integer.MAX_VALUE;
			}
			if (value < min || value > max)
				throw new NumberFormatException("\"" + raw
						+ "\" is out of range");
		}
		return new IntegerLiteralX(base, type, value, unsigned);
	}

	/**
	 * Return the string representation of the {@code IntegerLiteralX}
	 */
	@Override
	public String toString() {
		String str = "";
		if (unsigned) {
			switch (base) {
			case DECIMAL:
				str = Long.toUnsignedString(value);
				break;
			case OCTAL:
				if (value == 0)
					str = "0";
				else
					str = "0" + Long.toOctalString(value);
				break;
			case HEXADECIMAL:
				str = "0x" + Long.toHexString(value).toUpperCase();
				break;
			}
		} else {
			switch (base) {
			case DECIMAL:
				str = Long.toString(value);
				break;
			case OCTAL:
				if (value == 0)
					str = "0";
				else
					str = "0" + Long.toString(value, 8);
				break;
			case HEXADECIMAL:
				str = "0x" + Long.toString(value, 16).toUpperCase();
				break;
			}
		}
		if (unsigned)
			str += "U";
		switch (type) {
		case LONG:
			str += "L";
			break;
		case LONGLONG:
			str += "LL";
			break;
		default: // do nothing
			break;
		}
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + base.hashCode();
		result = prime * result + type.hashCode();
		result = prime * result + (unsigned ? 1231 : 1237);
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IntegerLiteralX))
			return false;
		IntegerLiteralX other = (IntegerLiteralX) obj;
		if (base != other.base)
			return false;
		if (type != other.type)
			return false;
		if (unsigned != other.unsigned)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}
