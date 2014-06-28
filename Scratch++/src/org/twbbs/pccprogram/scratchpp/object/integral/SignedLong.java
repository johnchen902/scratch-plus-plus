package org.twbbs.pccprogram.scratchpp.object.integral;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;
import org.twbbs.pccprogram.scratchpp.object.floating.Double;
import org.twbbs.pccprogram.scratchpp.object.floating.Float;

/**
 * A C++ <code>long</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedLong extends SignedIntLongBase<SignedLong> {

	private static class SignedLongType extends ArithmeticType {
		public SignedLongType() {
			super("long int");
		}

		@Override
		public SignedLong of(ArithmeticValue v) {
			if (v instanceof Double)
				return new SignedLong((int) ((Double) v).getValue());
			else if (v instanceof Float)
				return new SignedLong((int) ((Float) v).getValue());
			else if (v instanceof SignedInt)
				return new SignedLong(((SignedInt) v).getValue());
			else if (v instanceof SignedLong)
				return (SignedLong) v;
			else if (v instanceof SignedLongLong)
				return new SignedLong((int) ((SignedLongLong) v).getValue());
			else if (v instanceof UnsignedIntLongBase<?>)
				return new SignedLong(((UnsignedIntLongBase<?>) v).getValue());
			else if (v instanceof UnsignedLongLong)
				return new SignedLong((int) ((UnsignedLongLong) v).getValue());
			throw new AssertionError();
		}
	}

	/**
	 * The type of a <code>long</code>.
	 */
	public static final ArithmeticType TYPE = new SignedLongType();

	/**
	 * Create a <code>long</code> with value <code>0L</code>.
	 */
	public SignedLong() {
		this(0);
	}

	/**
	 * Create a <code>long</code> with specific value.
	 */
	public SignedLong(int value) {
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
		if (!(obj instanceof SignedLong))
			return false;
		return getValue() == ((SignedLong) obj).getValue();
	}
}
