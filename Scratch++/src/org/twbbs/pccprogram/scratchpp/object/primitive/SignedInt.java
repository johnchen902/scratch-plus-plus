package org.twbbs.pccprogram.scratchpp.object.primitive;

import org.twbbs.pccprogram.scratchpp.object.Type;
import org.twbbs.pccprogram.scratchpp.object.Value;

/**
 * A C++ <code>int</code>, 32-bit in this implementation.
 * 
 * @author johnchen902
 */
public class SignedInt extends Value {
	private static class SignedIntType extends Type {
		public SignedIntType() {
			super("int");
		}
	}

	/**
	 * The type of a <code>int</code>.
	 */
	public static final Type TYPE = new SignedIntType();

	private int value;

	/**
	 * Create a <code>int</code> with default value.
	 */
	public SignedInt() {
		this(0);
	}

	/**
	 * Create a <code>int</code> with specific value.
	 * 
	 * @param value
	 *            the specific value
	 */
	public SignedInt(int value) {
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
		if (!(obj instanceof SignedInt))
			return false;
		SignedInt other = (SignedInt) obj;
		return value == other.value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
