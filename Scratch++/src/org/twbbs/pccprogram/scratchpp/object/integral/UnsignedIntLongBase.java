package org.twbbs.pccprogram.scratchpp.object.integral;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;

/**
 * The common base of {@link UnsignedInt} and {@link UnsignedLong}.
 * 
 * @author johnchen902
 *
 * @param <T>
 *            {@code UnsignedInt} or {@code UnsignedLong}
 */
public abstract class UnsignedIntLongBase<T extends UnsignedIntLongBase<T>>
		extends ArithmeticValue {

	private final int value;

	protected UnsignedIntLongBase(ArithmeticType type, int value) {
		super(type);
		this.value = value;
	}

	/**
	 * Get its value
	 * 
	 * @return its value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public abstract boolean equals(Object rhs);

	@Override
	public String toString() {
		return Integer.toUnsignedString(value);
	}
}
