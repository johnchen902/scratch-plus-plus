package org.twbbs.pccprogram.scratchpp.object.floating;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedIntLongBase;
import org.twbbs.pccprogram.scratchpp.object.integral.SignedLongLong;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedIntLongBase;
import org.twbbs.pccprogram.scratchpp.object.integral.UnsignedLongLong;

/**
 * A C++ <code>float</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class Float extends ArithmeticValue {

	private static class FloatType extends ArithmeticType {
		private FloatType() {
			super("float");
		}

		public Float of(ArithmeticValue v) {
			if (v instanceof Double)
				return new Float((float) ((Double) v).getValue());
			else if (v instanceof Float)
				return (Float) v;
			else if (v instanceof SignedIntLongBase<?>)
				return new Float(((SignedIntLongBase<?>) v).getValue());
			else if (v instanceof SignedLongLong)
				return new Float(((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedIntLongBase<?>) {
				int i = ((UnsignedIntLongBase<?>) v).getValue();
				return new Float(i >= 0 ? i : i + 0x1p32f);
			} else if (v instanceof UnsignedLongLong) {
				long i = ((UnsignedLongLong) v).getValue();
				return new Float(i >= 0 ? i : i + 0x1p64f);
			}
			throw new AssertionError();
		}
	}

	/**
	 * The type of a <code>float</code>.
	 */
	public static final ArithmeticType TYPE = new FloatType();

	private final float value;

	/**
	 * Create a <code>float</code> with value <code>0f</code>.
	 */
	public Float() {
		this(0);
	}

	/**
	 * Create a <code>float</code> with specific value.
	 */
	public Float(float value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 */
	public float getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return java.lang.Float.floatToIntBits(value);
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
		if (!(obj instanceof Float))
			return false;
		Float other = (Float) obj;
		if (java.lang.Float.floatToIntBits(value) != java.lang.Float
				.floatToIntBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return java.lang.Float.toString(value);
	}
}
