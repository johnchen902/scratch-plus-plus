package org.twbbs.pccprogram.scratchpp.object.integral;

import java.math.BigDecimal;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * A C++ <code>unsigned long long</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedLongLong extends ArithmeticValue {

	private static class UnsignedLongLongType extends ArithmeticType {
		public UnsignedLongLongType() {
			super("unsigned long long int");
		}

		@Override
		public UnsignedLongLong of(ArithmeticValue v) {
			if (v instanceof Double) {
				return new UnsignedLongLong(new BigDecimal(
						((Double) v).getValue()).toBigInteger().longValue());
			} else if (v instanceof Float) {
				return new UnsignedLongLong(new BigDecimal(
						((Float) v).getValue()).toBigInteger().longValue());
			} else if (v instanceof SignedIntLongBase<?>)
				return new UnsignedLongLong(
						((SignedIntLongBase<?>) v).getValue() & 0xFFFFFFFFL);
			else if (v instanceof SignedLongLong)
				return new UnsignedLongLong(((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedIntLongBase)
				return new UnsignedLongLong(
						((UnsignedLong) v).getValue() & 0xFFFFFFFFL);
			else if (v instanceof UnsignedLongLong) {
				return (UnsignedLongLong) v;
			}
			throw new AssertionError();
		}
	}

	/**
	 * The type of an <code>unsigned long long</code>.
	 */
	public static final ArithmeticType TYPE = new UnsignedLongLongType();

	private long value;

	/**
	 * Create an <code>unsigned long long</code> with value <code>0ULL</code>.
	 */
	public UnsignedLongLong() {
		this(0);
	}

	/**
	 * Create an <code>unsigned long long</code> with specific value.
	 */
	public UnsignedLongLong(long value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 */
	public long getValue() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) (value ^ (value >>> 32));
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
		if (!(obj instanceof UnsignedLongLong))
			return false;
		return value == ((UnsignedLongLong) obj).value;
	}

	@Override
	public String toString() {
		return Long.toUnsignedString(value);
	}
}
