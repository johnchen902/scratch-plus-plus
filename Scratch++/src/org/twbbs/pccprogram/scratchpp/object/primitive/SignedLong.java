package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>long int</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedLong extends Value {
	private static class SignedLongType extends Type {
		public SignedLongType() {
			super("long int");
		}
	}

	/**
	 * The type of a <code>long int</code>.
	 */
	public static final Type TYPE = new SignedLongType();

	private int value;

	/**
	 * Create a <code>long int</code> with default value.
	 */
	public SignedLong() {
		this(0);
	}

	/**
	 * Create a <code>long int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public SignedLong(int value) {
		super(TYPE);
		this.value = value;
	}

	/**
	 * Get its value.
	 * 
	 * @return its value
	 */
	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SignedLong))
			return false;
		SignedLong other = (SignedLong) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
