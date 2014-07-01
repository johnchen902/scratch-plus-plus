package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>double</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class CDouble extends Value {
	private static class CDoubleType extends Type {
		public CDoubleType() {
			super("double");
		}
	}

	/**
	 * The type of a <code>double</code>.
	 */
	public static final Type TYPE = new CDoubleType();

	private double value;

	/**
	 * Create a <code>double</code> with default value.
	 */
	public CDouble() {
		this(0.0);
	}

	/**
	 * Create a <code>double</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public CDouble(double value) {
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
		if (!(obj instanceof CDouble))
			return false;
		CDouble other = (CDouble) obj;
		return Double.doubleToLongBits(value) == Double
				.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return Double.toString(value);
	}
}
