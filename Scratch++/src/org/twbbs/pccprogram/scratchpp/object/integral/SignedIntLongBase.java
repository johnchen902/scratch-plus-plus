package org.twbbs.pccprogram.scratchpp.object.integral;

import org.twbbs.pccprogram.scratchpp.object.ArithmeticType;
import org.twbbs.pccprogram.scratchpp.object.ArithmeticValue;

/**
 * The common base of {@link SignedInt} and {@link SignedLong}.
 * 
 * @author johnchen902
 *
 * @param <T>
 *            {@code SignedInt} or {@code SignedLong}
 */
public abstract class SignedIntLongBase<T extends SignedIntLongBase<T>> extends
		ArithmeticValue {

	private final int value;

	protected SignedIntLongBase(ArithmeticType type, int value) {
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
		return Integer.toString(value);
	}
}
