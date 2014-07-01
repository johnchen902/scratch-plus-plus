package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>long double</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class LongDouble extends Value {
	private static class LongDoubleType extends Type {
		public LongDoubleType() {
			super("long double");
		}
	}

	/**
	 * The type of a <code>long double</code>.
	 */
	public static final Type TYPE = new LongDoubleType();

	private double value;

	/**
	 * Create a <code>long double</code> with default value.
	 */
	public LongDouble() {
		this(0.0);
	}

	/**
	 * Create a <code>long double</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public LongDouble(double value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public double getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LongDouble))
			return false;
		LongDouble other = (LongDouble) obj;
		return Double.doubleToLongBits(value) == Double
				.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}
}
