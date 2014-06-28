package org.twbbs.pccprogram.scratchpp.syntax.literal;

import java.math.BigDecimal;

/**
 * A model of a C++ floating-literal.
 * 
 * @author johnchen902
 */
public class FloatingLiteralX {
	private final BigDecimal value;
	private final boolean isFloat;

	private FloatingLiteralX(BigDecimal value, boolean isFloat) {
		this.value = value;
		this.isFloat = isFloat;
	}

	/**
	 * Get the value of the floating-literal
	 * 
	 * @return the value of the floating-literal
	 */
	public BigDecimal getValue() {
		return value;
	}

	/**
	 * Check if the literal is of type <code>float</code>
	 * 
	 * @return <code>true</code> if the literal is a <code>float</code>;
	 *         <code>false</code> otherwise
	 */
	public boolean isFloat() {
		return isFloat;
	}

	@Override
	public String toString() {
		String s = value.toString();
		if (isFloat)
			s += "f";
		return s;
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
		result = prime * result + (isFloat ? 1231 : 1237);
		result = prime * result + value.hashCode();
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
		if (!(obj instanceof FloatingLiteralX))
			return false;
		FloatingLiteralX other = (FloatingLiteralX) obj;
		if (isFloat != other.isFloat)
			return false;
		if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * Translates a <code>double</code> into a <code>FloatingLiteralX</code>
	 * which is the exact decimal representation of the <code>double</code>'s
	 * binary floating-point value.
	 * 
	 * @param x
	 *            <code>double</code> value to be converted to
	 *            <code>FloatingLiteralX</code>
	 * @see BigDecimal#BigDecimal(double)
	 */
	public static FloatingLiteralX exactValueOf(double x) {
		return new FloatingLiteralX(new BigDecimal(x), false);
	}

	/**
	 * Translates a <code>float</code> into a <code>FloatingLiteralX</code>
	 * which is the exact decimal representation of the <code>float</code>'s
	 * binary floating-point value.
	 * 
	 * @param x
	 *            <code>float</code> value to be converted to
	 *            <code>FloatingLiteralX</code>
	 * @see BigDecimal#BigDecimal(double)
	 */
	public static FloatingLiteralX exactValueOf(float x) {
		return new FloatingLiteralX(new BigDecimal(x), true);
	}

	/**
	 * Translates a <code>double</code> into a <code>FloatingLiteralX</code>,
	 * using the <code>double</code>'s canonical string representation provided
	 * by the {@link Double#toString(double)} method.
	 * 
	 * @param x
	 *            <code>double</code> value to be converted to
	 *            <code>FloatingLiteralX</code>
	 * @see BigDecimal#valueOf(double)
	 */
	public static FloatingLiteralX valueOf(double x) {
		return new FloatingLiteralX(BigDecimal.valueOf(x), false);
	}

	/**
	 * Translates a <code>float</code> into a <code>FloatingLiteralX</code>,
	 * using the <code>float</code>'s canonical string representation provided
	 * by the {@link Float#toString(float)} method.
	 * 
	 * @param x
	 *            <code>float</code> value to be converted to
	 *            <code>FloatingLiteralX</code>
	 */
	public static FloatingLiteralX valueOf(float x) {
		return new FloatingLiteralX(new BigDecimal(Float.toString(x)), true);
	}

	/**
	 * Translates the string representation of a <code>FloatingLiteralX</code>
	 * into a <code>FloatingLiteralX</code>.
	 * 
	 * @param x
	 *            String representation of <code>FloatingLiteralX</code>
	 * @throws NumberFormatException
	 *             if <code>x</code> is not a valid representation of a
	 *             <code>FloatingLiteralX</code>.
	 * @see BigDecimal#BigDecimal(String)
	 */
	public static FloatingLiteralX valueOf(String x)
			throws NumberFormatException {
		if (x.endsWith("l") || x.endsWith("L"))
			throw new NumberFormatException("long double is unsupported");
		boolean isFloat = false;
		if (x.endsWith("f") || x.endsWith("F")) {
			isFloat = true;
			x = x.substring(0, x.length() - 1);
		}
		return new FloatingLiteralX(new BigDecimal(x), isFloat);
	}
}
