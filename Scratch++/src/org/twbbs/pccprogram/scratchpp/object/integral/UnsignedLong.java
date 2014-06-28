package org.twbbs.pccprogram.scratchpp.object.integral;

import java.math.BigDecimal;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * A C++ <code>unsigned long</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedLong extends UnsignedIntLongBase<UnsignedLong> {

	private static class UnsignedLongType extends ArithmeticType {
		public UnsignedLongType() {
			super("unsigned long int");
		}

		@Override
		public UnsignedLong of(ArithmeticValue v) {
			if (v instanceof Double) {
				return new UnsignedLong(new BigDecimal(((Double) v).getValue())
						.toBigInteger().intValue());
			} else if (v instanceof Float) {
				return new UnsignedLong(new BigDecimal(((Float) v).getValue())
						.toBigInteger().intValue());
			} else if (v instanceof SignedIntLongBase<?>)
				return new UnsignedLong(((SignedIntLongBase<?>) v).getValue());
			else if (v instanceof SignedLongLong)
				return new UnsignedLong((int) ((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedInt)
				return new UnsignedLong(((UnsignedLong) v).getValue());
			else if (v instanceof UnsignedLong)
				return (UnsignedLong) v;
			else if (v instanceof UnsignedLongLong) {
				return new UnsignedLong((int) ((UnsignedLongLong) v).getValue());
			}
			throw new AssertionError();
		}
	}

	/**
	 * The type of an <code>unsigned long</code>.
	 */
	public static final ArithmeticType TYPE = new UnsignedLongType();

	/**
	 * Create an <code>unsigned long</code> with value <code>0UL</code>.
	 */
	public UnsignedLong() {
		this(0);
	}

	/**
	 * Create an <code>unsigned long</code> with specific value.
	 */
	public UnsignedLong(int value) {
		super(TYPE, value);
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
		if (!(obj instanceof UnsignedLong))
			return false;
		return getValue() == ((UnsignedLong) obj).getValue();
	}
}
