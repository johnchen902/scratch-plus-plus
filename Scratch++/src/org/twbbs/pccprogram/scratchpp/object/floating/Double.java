package org.twbbs.pccprogram.scratchpp.object.floating;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedIntLongBase;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedLongLong;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedIntLongBase;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedLongLong;

/**
 * A C++ <code>double</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class Double extends ArithmeticValue {

	private static class DoubleType extends ArithmeticType {
		private DoubleType() {
			super("double");
		}

		public Double of(ArithmeticValue v) {
			if (v instanceof Double)
				return (Double) v;
			else if (v instanceof Float)
				return new Double(((Float) v).getValue());
			else if (v instanceof SignedIntLongBase<?>)
				return new Double(((SignedIntLongBase<?>) v).getValue());
			else if (v instanceof SignedLongLong)
				return new Double(((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedIntLongBase<?>) {
				int i = ((UnsignedIntLongBase<?>) v).getValue();
				return new Double(i >= 0 ? i : i + 0x1p32);
			} else if (v instanceof UnsignedLongLong) {
				long i = ((UnsignedLongLong) v).getValue();
				return new Double(i >= 0 ? i : i + 0x1p64);
			}
			throw new AssertionError();
		}
	}

	/**
	 * The type of a <code>double</code>.
	 */
	public static final ArithmeticType TYPE = new DoubleType();

	private final double value;

	/**
	 * Create a <code>double</code> with value <code>0.0</code>.
	 */
	public Double() {
		this(0);
	}

	/**
	 * Create a <code>double</code> with specific value.
	 */
	public Double(double value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 */
	public double getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		long temp = java.lang.Double.doubleToLongBits(value);
		return (int) (temp ^ (temp >>> 32));
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
		if (!(obj instanceof Double))
			return false;
		Double other = (Double) obj;
		if (java.lang.Double.doubleToLongBits(value) != java.lang.Double
				.doubleToLongBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return java.lang.Double.toString(value);
	}
}
