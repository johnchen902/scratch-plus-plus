package org.twbbs.pccprogram.scratchpp.object.integral;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * A C++ <code>int</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedInt extends SignedIntLongBase<SignedInt> {

	private static class SignedIntType extends ArithmeticType {
		public SignedIntType() {
			super("int");
		}

		@Override
		public SignedInt of(ArithmeticValue v) {
			if (v instanceof Double)
				return new SignedInt((int) ((Double) v).getValue());
			else if (v instanceof Float)
				return new SignedInt((int) ((Float) v).getValue());
			else if (v instanceof SignedInt)
				return (SignedInt) v;
			else if (v instanceof SignedLong)
				return new SignedInt(((SignedLong) v).getValue());
			else if (v instanceof SignedLongLong)
				return new SignedInt((int) ((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedIntLongBase<?>)
				return new SignedInt(((UnsignedIntLongBase<?>) v).getValue());
			else if (v instanceof UnsignedLongLong)
				return new SignedInt((int) ((UnsignedLongLong) v).getValue());
			throw new AssertionError();
		}
	}

	/**
	 * The type of an <code>int</code>.
	 */
	public static final ArithmeticType TYPE = new SignedIntType();

	/**
	 * Create an <code>int</code> with value <code>0</code>.
	 */
	public SignedInt() {
		this(0);
	}

	/**
	 * Create an <code>int</code> with specific value.
	 */
	public SignedInt(int value) {
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
		if (!(obj instanceof SignedInt))
			return false;
		return getValue() == ((SignedInt) obj).getValue();
	}
}
