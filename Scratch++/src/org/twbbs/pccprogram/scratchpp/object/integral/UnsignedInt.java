package org.twbbs.pccprogram.scratchpp.object.integral;

import java.math.BigDecimal;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * A C++ <code>unsigned</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class UnsignedInt extends UnsignedIntLongBase<UnsignedInt> {

	private static class UnsignedIntType extends ArithmeticType {
		private UnsignedIntType() {
			super("unsigned int");
		}

		public UnsignedInt of(ArithmeticValue v) {
			if (v instanceof Double) {
				return new UnsignedInt(new BigDecimal(((Double) v).getValue())
						.toBigInteger().intValue());
			} else if (v instanceof Float) {
				return new UnsignedInt(new BigDecimal(((Float) v).getValue())
						.toBigInteger().intValue());
			} else if (v instanceof SignedIntLongBase<?>)
				return new UnsignedInt(((SignedIntLongBase<?>) v).getValue());
			else if (v instanceof SignedLongLong)
				return new UnsignedInt((int) ((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedInt)
				return (UnsignedInt) v;
			else if (v instanceof UnsignedLong)
				return new UnsignedInt(((UnsignedLong) v).getValue());
			else if (v instanceof UnsignedLongLong) {
				return new UnsignedInt((int) ((UnsignedLongLong) v).getValue());
			}
			throw new AssertionError();
		}
	}

	/**
	 * The type of an <code>unsigned</code>.
	 */
	public static final ArithmeticType TYPE = new UnsignedIntType();

	/**
	 * Create an <code>unsigned</code> with value <code>0u</code>.
	 */
	public UnsignedInt() {
		this(0);
	}

	/**
	 * Create an <code>unsigned</code> with specific value.
	 */
	public UnsignedInt(int value) {
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
		if (!(obj instanceof UnsignedInt))
			return false;
		return getValue() == ((UnsignedInt) obj).getValue();
	}
}
