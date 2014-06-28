package org.twbbs.pccprogram.scratchpp.object.integral;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * A C++ <code>long long</code>, 64-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedLongLong extends ArithmeticValue {

	private static class SignedLongLongType extends ArithmeticType {
		public SignedLongLongType() {
			super("long long int");
		}

		@Override
		public SignedLongLong of(ArithmeticValue v) {
			if (v instanceof Double)
				return new SignedLongLong((long) ((Double) v).getValue());
			else if (v instanceof Float)
				return new SignedLongLong((long) ((Float) v).getValue());
			else if (v instanceof SignedIntLongBase<?>)
				return new SignedLongLong(((SignedIntLongBase<?>) v).getValue());
			else if (v instanceof SignedLongLong)
				return (SignedLongLong) v;
			else if (v instanceof UnsignedIntLongBase<?>)
				return new SignedLongLong(
						((UnsignedIntLongBase<?>) v).getValue() & 0xFFFFFFFFL);
			else if (v instanceof UnsignedLongLong)
				return new SignedLongLong(((UnsignedLongLong) v).getValue());
			throw new AssertionError();
		}
	}

	/**
	 * The type of a <code>long long</code>.
	 */
	public static final ArithmeticType TYPE = new SignedLongLongType();

	private long value;

	/**
	 * Create a <code>long long</code> with value <code>0LL</code>.
	 */
	public SignedLongLong() {
		this(0);
	}

	/**
	 * Create a <code>long long</code> with specific value.
	 */
	public SignedLongLong(long value) {
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
		if (!(obj instanceof SignedLongLong))
			return false;
		return value == ((SignedLongLong) obj).value;
	}

	@Override
	public String toString() {
		return Long.toString(value);
	}
}
